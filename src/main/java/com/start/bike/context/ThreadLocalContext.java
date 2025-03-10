package com.start.bike.context;

public class ThreadLocalContext {
    private static final ThreadLocal<String> lastExecutedSql = new ThreadLocal<>();

    public static void setLastExecutedSql(String sql) {
        lastExecutedSql.set(sql);
    }

    public static String getLastExecutedSql() {
        return lastExecutedSql.get();
    }

    public static void clearLastExecutedSql() {
        lastExecutedSql.remove();
    }
}