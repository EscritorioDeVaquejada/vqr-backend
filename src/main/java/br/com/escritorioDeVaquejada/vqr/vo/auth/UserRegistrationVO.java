package br.com.escritorioDeVaquejada.vqr.vo.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Schema(description = "Value Object representing the information required to create a new " +
        "user account.")
public class UserRegistrationVO implements Serializable {
    @Serial
    static private final long serialVersionUID = 1L;
    @NotBlank
    @Schema(description = "The unique username chosen by the user for login purposes.",
            example = "john_doe_92")
    private String username;
    //todo verificar eficiência do pattern para geração de senhas seguras
    @NotBlank @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_*]).{6,}$")
    @Schema(
            description = "The password chosen by the user. It must be at least 6 characters " +
                    "long and contain at least one digit, one lowercase letter, one uppercase letter," +
                    " and one special character (e.g., @, #, $, etc.).",
            example = "Passw0rd@"
    )
    private String password;
    @NotBlank @Pattern(regexp = "^([0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2})$")
    @Schema(
            description = "The CPF (Cadastro de Pessoas Físicas) number of the user, a unique " +
                    "identifier used in Brazil. It must be formatted as XXX.XXX.XXX-XX.",
            example = "123.456.789-00"
    )
    private String cpf;

    public UserRegistrationVO() {
    }

    public UserRegistrationVO(String username, String password, String cpf) {
        this.username = username;
        this.password = password;
        this.cpf = cpf;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserRegistrationVO that = (UserRegistrationVO) object;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, cpf);
    }
}
