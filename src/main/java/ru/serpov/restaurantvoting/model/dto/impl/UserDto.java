package ru.serpov.restaurantvoting.model.dto.impl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.CollectionUtils;
import ru.serpov.restaurantvoting.model.HasIdAndEmail;
import ru.serpov.restaurantvoting.model.Role;
import ru.serpov.restaurantvoting.model.dto.NamedDto;

import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends NamedDto implements HasIdAndEmail {

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    private boolean enabled;

    @NotNull
    private Date registered;

    private Set<Role> roles;

    @Builder
    public UserDto(Integer id, String name, String email, String password, Date registered, boolean enabled, Collection<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.enabled = enabled;
        setRoles(roles);
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }
}
