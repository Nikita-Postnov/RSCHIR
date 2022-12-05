package com.example.cursov_p.entity;

import lombok.*;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User implements IEntity, UserDetails {

    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id = null;

    @NonNull
    private String name;

    @NonNull
    private Role role;

    @NonNull
    private String password; // hash

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    { return Collections.singletonList(new SimpleGrantedAuthority(role.mkRole())); }

    @NonNull
    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return name; }

    @Override
    public Map<String, Object> toMap() {
        val map = new HashMap<String, Object>();
        map.put("id", getId());
        map.put("name", getName());
        map.put("role", getRole().ROLE);
        map.put("password", getPassword());
        return map;
    }

    @Nullable
    public static IEntity fromMap(Map<String, Object> map) {
        if (map.size() != 4) return null;

        val role = (Integer) map.get("role");
        if (role != 0 && role != 1)
            throw new IllegalArgumentException();

        return new User(
            (Integer) map.get("id"),
            (String) map.get("name"),
            role == 0 ? Role.USER : Role.ADMIN,
            (String) map.get("password")
        );
    }

    public User setPasswordCloning(String password)
    { return new User(getId(), getName(), getRole(), password); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @RequiredArgsConstructor
    public enum Role {
        USER (0),
        ADMIN(1);

        public final Integer ROLE;

        public String mkRole() { return String.format("%s%d", "role", ROLE); }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId())
            && getName().equals(user.getName())
            && getRole() == user.getRole()
            && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() { return Objects.hash(getId(), getName(), getRole(), getPassword()); }

    @Override
    public String toString() { return String.format(
        "User(%d %s %s %s)",
        getId(), getName(), getRole(), getPassword()
    ); }
}
