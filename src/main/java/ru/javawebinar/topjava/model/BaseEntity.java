package ru.javawebinar.topjava.model;

public class BaseEntity {
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }
}
