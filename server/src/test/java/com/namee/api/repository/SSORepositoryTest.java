package com.namee.api.repository;

import com.namee.NosUtilApplicationTests;
import com.namee.api.model.SSO;
import com.namee.api.model.common.ApiResult;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Created by namee on 2016. 6. 21..
 */
public class SSORepositoryTest extends NosUtilApplicationTests{
    private String apiEndPoint;

    @Before
    public void setUp() {
        apiEndPoint = "http://localhost:" + port + "/api/sso";
    }

    @Autowired
    private SSORepository ssoRepository;

    @Test
    public void testCRUD() {
        SSO sso = new SSO();
        sso.setCompanyCode("test");
        sso.setHashType("md5");

        sso = ssoRepository.save(sso);

        assertThat(sso, is(not(nullValue())));

        SSO sso2 = ssoRepository.findByCompanyCode(sso.getCompanyCode());
        assertThat(sso2.getHashType(), is(sso.getHashType()));


    }

    @Test
    public void realAdd() {
        SSO sso = new SSO();
        sso.setCompanyCode("test");
        sso.setHashType("md5");

        HttpEntity httpEntity = new HttpEntity(sso, null);

        ResponseEntity<ApiResult> result = restTemplate.exchange(apiEndPoint, HttpMethod.POST, httpEntity, ApiResult.class);

        Map data = (Map)result.getBody().getData();
        assertThat(data.get("hashType"), is(sso.getHashType()));
    }
}