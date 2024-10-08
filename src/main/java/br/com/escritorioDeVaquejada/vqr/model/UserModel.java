package br.com.escritorioDeVaquejada.vqr.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class UserModel implements Serializable, UserDetails {
    @Serial
    private static final long serialVersionUID=1L;
    @Id @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "user_id")
    private UUID id;
    @Column(unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(unique = true)
    private String cpf;
    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;
    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;
    @Column(name = "enabled")
    private Boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<PermissionModel> permissions;
    @OneToMany(mappedBy = "user")
    private List<TicketModel> ticketList;

    public UserModel(
            UUID id,
            String username,
            String password,
            String cpf,
            Boolean accountNonExpired,
            Boolean accountNonLocked,
            Boolean credentialsNonExpired,
            Boolean enabled,
            List<PermissionModel> permissions,
            List<TicketModel> ticketList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cpf = cpf;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.permissions = permissions;
        this.ticketList = ticketList;
    }

    public UserModel() {
    }

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        return permissions.stream().map(PermissionModel::getDescription).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    public void setPermissions(List<PermissionModel> permissions) {
        this.permissions = permissions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<TicketModel> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketModel> ticketList) {
        this.ticketList = ticketList;
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
        UserModel userModel = (UserModel) object;
        return Objects.equals(id, userModel.id) && Objects.equals(username, userModel.username) && Objects.equals(password, userModel.password) && Objects.equals(cpf, userModel.cpf) && Objects.equals(accountNonExpired, userModel.accountNonExpired) && Objects.equals(accountNonLocked, userModel.accountNonLocked) && Objects.equals(credentialsNonExpired, userModel.credentialsNonExpired) && Objects.equals(enabled, userModel.enabled) && Objects.equals(permissions, userModel.permissions) && Objects.equals(ticketList, userModel.ticketList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, cpf, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, permissions, ticketList);
    }
}
