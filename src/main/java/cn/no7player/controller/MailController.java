package cn.no7player.controller;

import cn.no7player.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangcy on 2018/3/15
 */

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @ResponseBody
    @RequestMapping("/send")
    public void send(){
        mailService.sendSimpleMail("2901491651@qq.com","test smpt","this is content");
        System.out.println("send success");
    }
}