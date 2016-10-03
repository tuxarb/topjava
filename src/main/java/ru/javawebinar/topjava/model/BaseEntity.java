package ru.javawebinar.topjava.model;

import javax.persistence.*;

@MappedSuperclass
@Access(value = AccessType.FIELD)
public class BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    protected BaseEntity(Integer id) {
        this.id = id;
    }

    protected BaseEntity() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id : 0;
    }
}
