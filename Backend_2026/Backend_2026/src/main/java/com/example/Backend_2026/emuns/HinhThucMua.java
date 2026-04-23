package com.example.Backend_2026.emuns;

public enum HinhThucMua {
    OFFLINE,
    ONLINE;

    public static HinhThucMua fromString(String value) {
        if (value == null) return null;

        try {
            return HinhThucMua.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Hình thức mua không hợp lệ: " + value);
        }
    }
}
