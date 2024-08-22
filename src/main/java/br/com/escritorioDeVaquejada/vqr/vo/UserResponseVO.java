package br.com.escritorioDeVaquejada.vqr.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class UserResponseVO implements Serializable {
    @Serial
    static private final long serialVersionUID = 1L;
    private UUID id;
    private String username;
    private String cpf;
    private List<String> permissions;

    public UserResponseVO() {
    }

    public UserResponseVO(UUID id, String username, String cpf, List<String> permissions) {
        this.id = id;
        this.username = username;
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

}
