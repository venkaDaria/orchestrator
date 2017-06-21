package com.globallogic.orchestrator.dao;

public final class SeparatorHolder {
    private static LocaleSeparator SEPARATOR = LocaleSeparator.SEMICOLON;
    private static LocaleSeparator SEPARATOR_DATABASE = LocaleSeparator.SEMICOLON;

    public static String getSeparatorString() {
        return SEPARATOR.toString();
    }

    public static String getSeparatorDatabaseString() {
        return SEPARATOR_DATABASE.toString();
    }

    public static void setSeparator(LocaleSeparator separator) {
        SEPARATOR = separator;
    }
}
