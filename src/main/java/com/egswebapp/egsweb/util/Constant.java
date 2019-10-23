package com.egswebapp.egsweb.util;

public enum Constant {
    USER_PACKAGE("user"),
    PAGE_PACKAGE("page"),
    POST_PACKAGE("post");

    private String packageName;

    Constant(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }
}
