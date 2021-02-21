package br.home.msc.api.secutiry.jwt;

import lombok.Data;

@Data
class JwtLoginInput {
    private String username;
    private String password;
}