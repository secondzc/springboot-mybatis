package cn.no7player.mail;

import cn.no7player.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by zhangcy on 2018/4/13
 */
public class MailQueue {
    @Value("${mail.fromMail.addr}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    private int threadPoolSize;
    private int queueSize;

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    //默认非公平
    private  final BlockingQueue<SimpleMailMessage> mailQueue = new ArrayBlockingQueue<SimpleMailMessage>(queueSize);
    private  final ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
    {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    SimpleMailMessage message = mailQueue.take();
                    //发送邮件
                    mailSender.send(message);
                } catch (InterruptedException e) {
                    //do nothing
                }
            }
        });
    }

    public void put(String to, String subject, String content) throws InterruptedException{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailQueue.put(message);
        } catch (InterruptedException e) {
            throw new InterruptedException(e.getMessage());
        }
    }

}
