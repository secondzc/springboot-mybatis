package cn.no7player.controller;

import cn.no7player.mail.MailQueue;
import cn.no7player.service.MailService;
import cn.no7player.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangcy on 2018/3/15
 */

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailQueue mailQueue;
    @Autowired
    private PasswordService passwordService;

    @ResponseBody
    @RequestMapping("/send")
    public String send(){
        //mailService.sendSimpleMail("2901491651@qq.com","test smpt","this is content");

        try {
            mailQueue.put("2901491651@qq.com","test smpt","this is content");
            mailQueue.put("2901491651@qq.com","test smpt2","this is content2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "already send";
    }

    /*
    更改、重置密码
     */
    @ResponseBody
    @RequestMapping("/updatePass")
    public void updatePass(){
        passwordService.test();
    }

}
