package pl.dmuszynski.aquashop.service;

import javax.mail.MessagingException;

public interface IMailService {
    void sendMail(String to, String subject, String content, boolean isHtmlContent) throws MessagingException;
}
