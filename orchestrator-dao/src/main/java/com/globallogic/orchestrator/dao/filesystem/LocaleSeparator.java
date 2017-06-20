package com.globallogic.orchestrator.dao.filesystem;

public enum LocaleSeparator {
    SEMICOLON, COMMA;

    @Override
    public String toString() {
        return this == SEMICOLON ? ";" : ",";
    }
}
