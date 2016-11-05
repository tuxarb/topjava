package ru.javawebinar.topjava.model;

import javax.persistence.*;

@MappedSuperclass
@Access(value = AccessType.FIELD)
/*@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)*/
public class BaseEntity {
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq",  allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
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
        return getId() == null;
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
        return getId()!= null ? getId() : 0;
    }
}
