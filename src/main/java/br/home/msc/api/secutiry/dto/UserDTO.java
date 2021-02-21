package br.home.msc.api.secutiry.dto;

import br.home.msc.api.secutiry.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class UserDTO {
    private String login;
    private String nome;
    private String email;

    // token jwt
    private String token;

    private UserDTO(User user, String token) {
        this.login = user.getLogin();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.token = token;
    }

    public static UserDTO create(User user, String token) {
        return of(user, token);
    }

    public static UserDTO of(User user, String token) {
        return new UserDTO(user, token);
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }


}
