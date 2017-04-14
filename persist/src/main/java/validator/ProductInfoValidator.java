package validator;

import dao.ToolsDAO;
import entity.catalog.Tools;
import models.ToolsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "modelId", "NotEmpty.productForm.modelId");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typeId", "NotEmpty.productForm.typeId");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cost", "NotEmpty.productForm.cost");

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
