package com.namee.api.repository;

import com.namee.api.model.SSO;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by namee on 2016. 6. 14..
 */
public interface SSORepository extends CrudRepository<SSO, Long> {
    SSO findByCompanyCode(String companyCode);
}
