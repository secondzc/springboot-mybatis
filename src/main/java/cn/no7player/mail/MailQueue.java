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
 * 考虑不要做成无界的队列，设置一个上限，达到上限之后，写入mysql，当队列为空时，从mysql中读取邮件进行发送。不过这样会造成不公平，
 * 考虑可以选择，如果需要公平的话，就按顺序全部依次写入数据库，再从mysql中读出
 */
public class MailQueue {
    @Value("${mail.fromMail.addr}")
    private String from;
    private JavaMailSender mailSender;

    private  BlockingQueue<SimpleMailMessage> mailQueue=null;
    private  ExecutorService executorService=null;


    public MailQueue(int threadPoolSize, int queueSize) {
        //默认非公平
        mailQueue = new ArrayBlockingQueue<>(queueSize);
        executorService = Executors.newFixedThreadPool(threadPoolSize);
        start();
    }

    public MailQueue() {
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }




    private void start(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        SimpleMailMessage message = mailQueue.take();
                        System.out.println("take时间"+System.currentTimeMillis());
                        System.out.println("take 了一次");
                        //发送邮件
                        //其实这里线程池是没用的，因为mailSender是单例，只有一个，而且send方法很耗时（至少10几秒），所以实际上还是
                        //串行执行的send方法，要改用一个线程
                        mailSender.send(message);
                    }
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
            System.out.println("put时间"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            throw new InterruptedException(e.getMessage());
        }
    }

}
