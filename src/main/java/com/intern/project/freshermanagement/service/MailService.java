package com.intern.project.freshermanagement.service;

public interface MailService {
    /**
     * Send an email active new user
     *
     * @param email email of new user
     */
    void sendActiveUserMail(String email);

    /**
     * Send an email reset password
     *
     * @param email email of user
     * @param password new password
     */
    void sendResetPassword(String email, String password);
}
