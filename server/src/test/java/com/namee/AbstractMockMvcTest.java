package com.namee;

import com.namee.core.spring.configuration.ApplicationConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by namee on 2016. 6. 21..
 */
@ActiveProfiles(profiles = "local")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfiguration.class})
@WebAppConfiguration
//@TransactionConfiguration(defaultRollback=true)
//@Transactional
public abstract class AbstractMockMvcTest {

    @Autowired
    protected WebApplicationContext wac;
//    @Autowired
//    protected NameeUserDetailService nameeUserDetailService;
    protected static final String CHARSET = "UTF-8";

    protected void printResult(MvcResult result) throws Exception {
        System.out.println("\n\n\n");
        System.out.println(result.getResponse().getContentAsString());
        System.out.println("\n\n\n");
    }
}