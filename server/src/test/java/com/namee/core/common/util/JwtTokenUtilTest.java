package com.namee.core.common.util;

import com.namee.NosUtilApplicationTests;
import com.namee.core.spring.security.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DevicePlatform;

/**
 * Created by namee on 2016. 6. 24..
 */
@Slf4j
public class JwtTokenUtilTest extends NosUtilApplicationTests{
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Before
    public void init() {
        jwtTokenUtil = new JwtTokenUtil();
    }

    @Test
    public void test_generateJWT() {
        JwtUser jwtUser = new JwtUser(0L, "lovedise", "", "", null, true, null);
        Device device = new Device() {
            @Override
            public boolean isNormal() {
                return true;
            }

            @Override
            public boolean isMobile() {
                return false;
            }

            @Override
            public boolean isTablet() {
                return false;
            }

            @Override
            public DevicePlatform getDevicePlatform() {
                return null;
            }
        };
        String token = jwtTokenUtil.generateToken(jwtUser, device);
        log.debug("token : {}", token);
    }
}