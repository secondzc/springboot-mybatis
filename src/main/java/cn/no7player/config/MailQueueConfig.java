package cn.no7player.config;

import cn.no7player.mail.MailQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhangcy on 2018/4/13
 */
@Configuration
public class MailQueueConfig {
    @Bean
    public MailQueue MailQueue(){
        MailQueue mailQueue = new MailQueue();
        mailQueue.setQueueSize(10);
        mailQueue.setThreadPoolSize(10);
        return mailQueue;
    }
}
