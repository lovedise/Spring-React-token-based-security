package com.namee.api.dto;

import lombok.Data;

/**
 * Created by namee on 2016. 6. 24..
 */
@Data
public class AuthDto {
    private String token;

    public AuthDto(String token) {
        this.token = token;
    }

    @Data
    public static class Request{
        private String username;
        private String password;
    }
}
