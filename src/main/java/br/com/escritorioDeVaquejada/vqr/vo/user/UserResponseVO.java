package br.com.escritorioDeVaquejada.vqr.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Schema(description = "Represents the data returned in HTTP responses for user-related operations. " +
        "This object is used to send user information in HTTP responses.")
public class UserResponseVO implements Serializable {
    @Serial
    static private final long serialVersionUID = 1L;
    @Schema(description = "Unique identifier of the User",
            example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "The unique username chosen by the user for login purposes.",
            example = "john_doe_92")
    private String username;
    @Schema(
            description = "The CPF (Cadastro de Pessoas Físicas) number of the user, a unique " +
                    "identifier used in Brazil.",
            example = "123.456.789-00"
    )
    private String cpf;
    @Schema(
            description = "A list of roles assigned to the user, defining their access level" +
                    "and permissions within the system.",
            example = "[\"ROLE_USER\", \"ROLE_ADMIN\", \"ROLE_MANAGER\"]"
    )
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
