package com.namee.api.controller;

import com.namee.api.model.SSO;
import com.namee.api.model.common.ApiResult;
import com.namee.api.service.SSOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by namee on 2016. 6. 14..
 */
@RestController
@RequestMapping(value = "api/sso")
@Slf4j
public class SSOController {
    @Autowired
    private SSOService ssoService;

    @Autowired
    private SimpMessagingTemplate websocket;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResult<List<SSO>> SSOList() {
        List<SSO> allSSO = ssoService.getAllSSO();

        return new ApiResult<>(allSSO);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ApiResult<SSO> addSSO(@RequestBody SSO sso) {
        log.debug("====== sso1111 : " + sso.toString());
        SSO createSSO = ssoService.create(sso);
        return new ApiResult<>(createSSO);
    }

    @RequestMapping(value = "/{cd}", method = RequestMethod.GET)
    public ApiResult<SSO> findSSO(@PathVariable("cd") String cd){
        SSO findSSO = ssoService.findOne(cd);
        return new ApiResult<>(findSSO);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "index";
    }

//    @ExceptionHandler(value = {DuplicateCompanyCodeException.class})
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public ApiResult<ApiError> errorDuplicateCompnayCode(Exception e, HttpServletRequest request, HttpServletResponse response) {
//        return new ApiResult<>(new ApiError(ApiErrorType.UNKNOWN, ApiErrorCode.UNKNOWN_SERVER_ERROR, "중복 COMPANY_CD", e));
//    }
}
