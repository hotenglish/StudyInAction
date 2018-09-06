package com.juvenxu.mvnbook.account.service;

import com.juvenxu.mvnbook.account.exception.AccountServiceException;

public interface AccountService {

    String generateCaptchaKey() throws AccountServiceException;

    byte[] generateCaptchaImage(String captchaKey) throws AccountServiceException;

    void signUp(SignUpRequest signUpRequest) throws  AccountServiceException;

    void activate(String activationNumber) throws AccountServiceException;

    void login(String id, String password) throws AccountServiceException;

}