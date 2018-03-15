package cn.no7player.service;

/**
 * Created by zhangcy on 2018/3/15
 */
public interface MailService {
    void sendSimpleMail(String to, String subject, String content);
}
