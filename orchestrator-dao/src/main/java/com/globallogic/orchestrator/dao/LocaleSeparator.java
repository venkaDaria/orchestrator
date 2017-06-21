package com.globallogic.orchestrator.dao;

public enum LocaleSeparator {
    SEMICOLON, COMMA;

    @Override
    public String toString() {
        return this == SEMICOLON ? ";" : ",";
    }
}
