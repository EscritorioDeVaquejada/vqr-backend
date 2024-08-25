package br.com.escritorioDeVaquejada.vqr.vo.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Schema(description = "Value Object representing the credentials required for user authentication.")
public class AccountCredentialsVO implements Serializable {
    @Serial
    static private final long serialVersionUID = 1L;
    @NotBlank
    @Schema(
            description = "The username of the user attempting to log in.",
            example = "john_doe_92"
    )
    private String username;
    @NotBlank
    @Schema(
            description = "The password associated with the user's account.",
            example = "Passw0rd@"
    )
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
