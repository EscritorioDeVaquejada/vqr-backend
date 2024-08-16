package br.com.escritorioDeVaquejada.vqr.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "permissions")
public class PermissionModel implements Serializable, GrantedAuthority {
    @Serial
    static private final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.UUID) @Column(name="permission_id")
    private UUID id;
    private String description;
    @ManyToMany(mappedBy = "permissions")
    private List<UserModel> users;

    @Override
    public String getAuthority() {
        return this.description;
    }

    public PermissionModel(UUID id, String description, List<UserModel> users) {
        this.id = id;
        this.description = description;
        this.users = users;
    }

    public PermissionModel() {
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PermissionModel that = (PermissionModel) object;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, users);
    }
}
