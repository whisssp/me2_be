package com.me2.util;

import java.util.HashMap;
import java.util.Map;

public class CloudUtil {
    public static Map<String, String> toMapOption(String keyOption, String optionValue) {
        Map<String, String> configOption = new HashMap<>();
        configOption.put(keyOption, optionValue);
        return configOption;
    }
}