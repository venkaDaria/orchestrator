package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.filesystem.LocaleSeparator;

public final class SeparatorHolder {
    private static LocaleSeparator SEPARATOR = LocaleSeparator.SEMICOLON;

    public static String getSeparatorString() {
        return SEPARATOR.toString();
    }

    public static void setSeparator(LocaleSeparator separator) {
        SEPARATOR = separator;
    }
}
