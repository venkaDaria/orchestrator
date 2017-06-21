package com.globallogic.orchestrator.dao;

public final class SeparatorHolder {
    private static LocaleSeparator SEPARATOR = LocaleSeparator.SEMICOLON;

    public static String getSeparatorString() {
        return SEPARATOR.toString();
    }

    public static void setSeparator(LocaleSeparator separator) {
        SEPARATOR = separator;
    }
}
