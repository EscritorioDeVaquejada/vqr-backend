package br.com.escritorioDeVaquejada.vqr.vo.auth;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Schema(description = "Represents the token details returned upon successful authentication " +
        "of a user.")
public class TokenVO implements Serializable {
    @Serial
    static private final long serialVersionUID=1L;
    @Schema(description = "The username associated with the token.", example = "john_doe_92")
    private String username;
    @Schema(
            description = "A boolean flag indicating whether the user has been successfully " +
                    "authenticated.",
            example = "true"
    )
    private boolean authenticated;
    @Schema(
            description = "The timestamp indicating when the token was created.",
            example = "2024-08-24T14:35:00Z"
    )
    private Date created;
    @Schema(
            description = "The timestamp indicating when the token will expire and no longer " +
                    "be valid.",
            example = "2024-08-25T14:35:00Z"
    )
    private Date expiration;
    @Schema(
            description = "The JWT (JSON Web Token) used for authorizing requests. " +
                    "It grants access to the user's protected resources."
            //example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    )
    private String accessToken;
    @Schema(
            description = "The token used to obtain a new access token when the current one " +
                    "expires. It provides a way to maintain the user's session."
            //example = "dGhpc2lzdGhlcmVmcmVzaHRva2VuMTIz..."
    )
    private String refreshToken;

    public TokenVO(
            String username,
            boolean authenticated,
            Date created,
            Date expiration,
            String accessToken,
            String refreshToken) {
        this.username = username;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public TokenVO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TokenVO tokenVO = (TokenVO) object;
        return authenticated == tokenVO.authenticated && Objects.equals(username, tokenVO.username) && Objects.equals(created, tokenVO.created) && Objects.equals(expiration, tokenVO.expiration) && Objects.equals(accessToken, tokenVO.accessToken) && Objects.equals(refreshToken, tokenVO.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authenticated, created, expiration, accessToken, refreshToken);
    }
}
