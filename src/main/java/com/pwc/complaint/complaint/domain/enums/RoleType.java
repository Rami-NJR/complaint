package com.pwc.complaint.complaint.domain.enums;

import java.util.LinkedHashMap;

public class RoleType {

    private static final LinkedHashMap<String, RoleType> TYPES = new LinkedHashMap<>();

    public static final RoleType ADMIN = new RoleType("admin", "ROLE_ADMIN");
    public static final RoleType USER = new RoleType("user", "ROLE_USER");

    public static RoleType getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;
    private String DbRoleName;

    public RoleType() {
        //do nothing
    }

    public RoleType(String type, String DbRoleName) {
        this.DbRoleName = DbRoleName;
        this.setType(type);
    }

    public String getType() {
        return type;
    }

    public String DbRoleName() {
        return DbRoleName;
    }

    private void setType(final String type) {
        this.type = type;
        if (!TYPES.containsKey(type)) {
            TYPES.put(type, this);
        }
    }
}
