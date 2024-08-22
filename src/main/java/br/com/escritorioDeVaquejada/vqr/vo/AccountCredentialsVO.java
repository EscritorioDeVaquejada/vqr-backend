package br.com.escritorioDeVaquejada.vqr.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class AccountCredentialsVO implements Serializable {
    @Serial
    static private final long serialVersionUID = 1L;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public AccountCredentialsVO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AccountCredentialsVO() {
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AccountCredentialsVO that = (AccountCredentialsVO) object;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
