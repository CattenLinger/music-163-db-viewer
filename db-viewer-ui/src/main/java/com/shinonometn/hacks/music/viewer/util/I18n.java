package com.shinonometn.hacks.music.viewer.util;

import com.shinonometn.hacks.music.viewer.utils.XMLResourceBundleControl;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class I18n {
    public static final ResourceBundle UI_MAIN_BUNDLE = ResourceBundle
            .getBundle("ui.language.uiMain", new Locale("zh","CN"),new XMLResourceBundleControl());

    public static String getString(String key) {
        try {
            return UI_MAIN_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return String.format("%%%s%%", key);
        }
    }
}
