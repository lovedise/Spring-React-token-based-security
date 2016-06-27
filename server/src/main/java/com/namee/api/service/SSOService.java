package com.namee.api.service;

import com.namee.api.model.SSO;
import com.namee.api.support.exception.DuplicateCompanyCodeException;

import java.util.List;

/**
 * Created by namee on 2016. 6. 14..
 */

public interface SSOService {
    SSO findOne(String companyCode);
    List<SSO> getAllSSO() throws DuplicateCompanyCodeException;

    SSO create(SSO sso);
}
