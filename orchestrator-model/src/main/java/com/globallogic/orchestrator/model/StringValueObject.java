package com.globallogic.orchestrator.model;

import com.globallogic.orchestrator.exception.StringValueObjectValidationException;
import org.apache.commons.lang.StringUtils;

public abstract class StringValueObject extends ValueObject<String> {

    public StringValueObject(String value) {
        if (StringUtils.isBlank(value)) {
            throw new StringValueObjectValidationException();
        }
    }
}
