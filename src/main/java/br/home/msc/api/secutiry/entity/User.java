package br.home.msc.api.secutiry.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "users")
@Data
@JsonIgnoreProperties(value = {
        "authorities",
        "credentialsNonExpired",
        "enabled",
        "password",
        "username",
        "accountNonLocked",
        "accountNonExpired",
        "credentialsNonExpired",
        "senha"
})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    private String nome;
    private String login;
    private String senha;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    private User(Long id, String nome, String login, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Admin:  " + encoder.encode("admin"));
        System.out.println("User:  " + encoder.encode("user"));

        BCryptPasswordEncoder decode = new BCryptPasswordEncoder();
        Boolean res = decode.matches("admin", encoder.encode("admin"));

        System.out.println("res = " + res);


    }

    public static User of(Long id, String nome, String login, String email, String senha) {
        return new User(id, nome, login, email, senha);
    }

    @Deprecated
    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public List<String> getRoles() {
       return roles.stream().map(Role::getNome)
                .collect(Collectors.toList());
    }

}
