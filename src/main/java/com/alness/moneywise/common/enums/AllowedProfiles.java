package com.alness.moneywise.common.enums;

public enum AllowedProfiles {
    ADMIN("Administrator", 1), EMPLOYEE("Employee", 2), USER("User", 3);

    private String name;
    private Integer priority;

    AllowedProfiles(String name, Integer priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
