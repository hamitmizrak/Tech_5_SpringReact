package com.hamitmizrak.tech5.rolles;

// Enum Rolles
public enum ERolles {
    SUPER_ADMIN(1, "super_admin"), ADMIN(2, "admin"), WRITER(3, "writer"), USER(4, "user");

    //  Variable
    private final Integer key;
    private final String value;

    // Constructor (Parametreli)
    // private constructor olursa instance izin verilmez
    private ERolles(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    // GETTER AND SETTER
    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
