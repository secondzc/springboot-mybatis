package cn.no7player.config;

import cn.no7player.mail.MailQueue;
import cn.no7player.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by zhangcy on 2018/4/13
 */
@Configuration
public class MailQueueConfig {
    @Autowired
    private JavaMailSender mailSender;
    @Bean
    public MailQueue MailQueue(){
        MailQueue mailQueue = new MailQueue(3,3);
        mailQueue.setMailSender(mailSender);
        return mailQueue;
    }
}
