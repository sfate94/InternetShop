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


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toolsId", "NotEmpty.productForm.code");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "modelId", "NotEmpty.productForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typeId", "NotEmpty.productForm.price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cost", "NotEmpty.productForm.price");

        String code = toolsInfo.gettoolsId();
        if (code != null && code.length() > 0) {
            if (code.matches("\\s+")) {
                errors.rejectValue("code", "Pattern.productForm.code");
            } else if(toolsInfo.isnewTools()) {
                Tools tools = toolsDAO.findTools(code);
                if (tools != null) {
                    errors.rejectValue("code", "Duplicate.productForm.code");
                }
            }
        }
    }

}
