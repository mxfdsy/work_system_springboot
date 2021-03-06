package com.coding.commons.companyValidate;


import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

public class EntityValidator {

    public static <T> ValidateResult validateEntity(T domain){
        return validate(domain);
    }
    
    /**
     * @AUTHOR: 榴莲
     * @DESCRIPTION: 用于校验存在List属性的POJO
     * @DATE: CREATE AT 10:39 2018/7/19
     * @MODIFIED_BY: 
     */
    public static ValidateResult validateEntityWithList(Object obj, Class<?>... groups) throws IllegalAccessException {
        ValidateResult validateResult = new ValidateResult();
        if (obj instanceof Collection) {
            Collection objList = (Collection) obj;
            if (!CollectionUtils.isEmpty(objList)) {
                for (Object e : objList) {
                    validateResult = validateEntityWithList(e, groups);
                    if (validateResult.hasError())
                        break;
                }
            }
        }
        else if (!ObjectUtils.isEmpty(obj)) {
            validateResult = validate(obj, groups);
            if (validateResult.hasError())
                return validateResult;

            Field[] fields = obj.getClass().getDeclaredFields();
            if (fields.length != 0) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (Collection.class.isAssignableFrom(field.getType())) {
                        validateResult = validateEntityWithList((Collection) field.get(obj), groups);
                    }
                    if (validateResult.hasError())
                        break;
                }
            }
        }
        return validateResult;
    }


    public static <T> ValidateResult validate(T domain, Class<?>... groups) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return convert(validator.<Object>validate(domain, groups), new ValidateResult());
    }

    public static <T> ValidateResult validate(T domain, String property, Class<?>... groups) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return convert(validator.<Object>validateProperty(domain, property, groups), new ValidateResult());
    }

    private static <T> ValidateResult convert(Set<ConstraintViolation<Object>> vr, ValidateResult r) {
        vr.forEach((cv) -> r.addErrorMessage(cv.getMessage()));
        return r;
    }

}
