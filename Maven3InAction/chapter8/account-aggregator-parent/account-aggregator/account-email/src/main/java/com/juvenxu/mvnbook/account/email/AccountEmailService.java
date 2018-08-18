package com.juvenxu.mvnbook.account.email;

import com.juvenxu.mvnbook.exception.AccountEmailException;

public interface AccountEmailService {

    void sendMail(String to,String subject, String htmlText) throws AccountEmailException;
}
