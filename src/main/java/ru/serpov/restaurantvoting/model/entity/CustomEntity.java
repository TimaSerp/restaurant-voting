package ru.serpov.restaurantvoting.model.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.ProxyUtils;
import ru.serpov.restaurantvoting.model.HasId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class CustomEntity implements HasId {

    @Id
    @GeneratedValue
    protected Integer id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(ProxyUtils.getUserClass(o))) {
            return false;
        }
        CustomEntity that = (CustomEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
}
