package com.globallogic.orchestrator.model.valueobject;

import com.globallogic.orchestrator.model.StringValueObject;

public final class Protocol extends StringValueObject {
    private final String value;

    public Protocol(final String value) {
        super(value);
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String asString() {
        return "Protocol [value=" + value + "]";
    }
}