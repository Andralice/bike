package com.start.bike.util;

import java.util.regex.Pattern;

/**
 * 字符串校验工具类
 *
 */
public class StringValidator {

    // 正则表达式：必须包含数字和字母，且仅包含数字字母组合
    private static final String ALPHANUMERIC_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]+$";

    // 预编译正则表达式提升性能
    private static final Pattern pattern = Pattern.compile(ALPHANUMERIC_PATTERN);

    /**
     * 校验字符串是否同时包含数字和字母，且不含其他字符
     * @param input 待校验字符串
     * @return true-符合规则，false-不符合
     */
    public static boolean isAlphanumeric(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return pattern.matcher(input).matches();
    }

    /**
     * 带最小长度校验的版本
     * @param input 待校验字符串
     * @param minLength 最小长度要求
     * @return true-符合规则，false-不符合
     */
    public static boolean isAlphanumeric(String input, int minLength) {
        return isAlphanumeric(input) && input.length() >= minLength;
    }

    /**
     * 带长度范围校验的版本
     * @param input 待校验字符串
     * @param minLength 最小长度
     * @param maxLength 最大长度
     * @return true-符合规则，false-不符合
     */
    public static boolean isAlphanumeric(String input, int minLength, int maxLength) {
        return isAlphanumeric(input) &&
                input.length() >= minLength &&
                input.length() <= maxLength;
    }
}