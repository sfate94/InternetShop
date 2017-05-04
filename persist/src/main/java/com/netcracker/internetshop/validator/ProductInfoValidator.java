package com.netcracker.internetshop.validator;

import com.netcracker.internetshop.dao.ToolsDAO;
import com.netcracker.internetshop.entity.catalog.Tools;
import com.netcracker.internetshop.models.ToolsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("productInfoValidator")
public class ProductInfoValidator implements Validator {

    @Autowired
    private ToolsDAO toolsDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ToolsInfo.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ToolsInfo toolsInfo = (ToolsInfo) target;


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toolsId", "NotEmpty.productForm.toolsId");
       /* ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deviceModel.modelId", "NotEmpty.productForm.modelId");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typeToolsInfo.typeId", "NotEmpty.productForm.*//*typeId");*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cost", "NotEmpty.productForm.cost");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "length", "NotEmpty.productForm.length");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "height", "NotEmpty.productForm.height");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "NotEmpty.productForm.weight");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "power", "NotEmpty.productForm.power");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "speed", "NotEmpty.productForm.speed");


        String toolsId = toolsInfo.getToolsId();
        if (toolsId != null && toolsId.length() > 0) {
            if (toolsId.matches("\\s+")) {
                errors.rejectValue("toolsId", "Pattern.productForm.toolsId");
            } else if(toolsInfo.isnewTools()) {
                Tools tools = toolsDAO.findTools(toolsId);
                if (tools != null) {
                    errors.rejectValue("toolsId", "Duplicate.productForm.toolsId");
                }
            }
        }
    }

}
