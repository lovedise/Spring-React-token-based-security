package com.namee.api.service.impl;

import com.google.common.collect.Lists;
import com.namee.api.model.SSO;
import com.namee.api.repository.SSORepository;
import com.namee.api.service.SSOService;
import com.namee.api.support.exception.DuplicateCompanyCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by namee on 2016. 6. 14..
 */
@Service
public class SSOServiceImpl implements SSOService{
    @Autowired
    SSORepository ssoRepository;

    @Override
    public SSO findOne(String companyCode) {
        return ssoRepository.findByCompanyCode(companyCode);
    }

    @Override
    public List<SSO> getAllSSO() {
        return Lists.newArrayList(ssoRepository.findAll());
    }

    @Override
    public SSO create(SSO sso) throws DuplicateCompanyCodeException{
        if(ssoRepository.findByCompanyCode(sso.getCompanyCode()) != null) {
            throw new DuplicateCompanyCodeException();
        }
        return ssoRepository.save(sso);
    }
}
