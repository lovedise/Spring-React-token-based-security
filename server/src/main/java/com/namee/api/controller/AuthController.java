package com.namee.api.controller;

import com.namee.api.dto.AuthDto;
import com.namee.api.model.common.ApiError;
import com.namee.api.model.common.ApiErrorCode;
import com.namee.api.model.common.ApiErrorType;
import com.namee.api.model.common.ApiResult;
import com.namee.core.common.util.JwtTokenUtil;
import com.namee.core.spring.security.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by namee on 2016. 6. 24..
 */
@RestController
@Slf4j
@RequestMapping(value = "/auth")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ApiResult<AuthDto> login(@RequestBody AuthDto.Request request, Device device) throws AuthenticationException {
        // Perform the security
        log.debug("request : {}", request);
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);

        return new ApiResult<>(new AuthDto(token));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ApiResult<?> refreshAndGetToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if(jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshToken = jwtTokenUtil.refreshToken(token);
            return new ApiResult<>(new AuthDto(refreshToken));
        } else {
            return new ApiResult<>(new ApiError(ApiErrorType.UNKNOWN, ApiErrorCode.UNKNOWN_SERVER_ERROR, "토근 갱실 실패"));
        }
    }
}
