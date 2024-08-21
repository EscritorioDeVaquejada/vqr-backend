package br.com.escritorioDeVaquejada.vqr.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserRegistrationVO implements Serializable {
    @Serial
    static private final long serialVersionUID = 1L;
    private UUID id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotNull @Pattern(regexp = "^([0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2})$")
    private String cpf;
    private List<String> permissions;

    public UserRegistrationVO() {
    }

    public UserRegistrationVO(UUID id, String username, String password, String cpf, List<String> permissions) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cpf = cpf;
        this.permissions = permissions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserRegistrationVO userRegistrationVO = (UserRegistrationVO) object;
        return Objects.equals(id, userRegistrationVO.id) && Objects.equals(username, userRegistrationVO.username) && Objects.equals(password, userRegistrationVO.password) && Objects.equals(cpf, userRegistrationVO.cpf) && Objects.equals(permissions, userRegistrationVO.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, cpf, permissions);
    }
}
