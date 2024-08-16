package br.com.escritorioDeVaquejada.vqr.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class AuthenticationVo implements Serializable {
    @Serial
    static private final long serialVersionUID = 1L;
    private String login;
    private String password;

    public AuthenticationVo(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthenticationVo() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        AuthenticationVo that = (AuthenticationVo) object;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
