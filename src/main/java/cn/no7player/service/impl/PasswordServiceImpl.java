package cn.no7player.service.impl;

import cn.no7player.model.User;
import cn.no7player.service.MailService;
import cn.no7player.service.PasswordService;
import cn.no7player.util.SecurityUtil;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangcy on 2018/3/15
 */
@Service
public class PasswordServiceImpl implements PasswordService{

    private static final ExecutorService removeExpireExecutor = Executors.newSingleThreadExecutor();

    static {
        //定时删除超时未获取到结果的key
        removeExpireExecutor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        for (Map.Entry<Integer,Pair<String,Long>> entry :authMap.entrySet()) {
                            boolean isExpire = isExpired(entry.getValue());
                            if (isExpire) {
                                authMap.remove(entry.getKey());
                            }
                            //每分钟检查一次
                            Thread.sleep(60 * 1000);
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 判断更改密码请求是否过期
     */
    public static Boolean isExpired(Pair<String,Long> pair){
        if(null == pair){
            return true;
        }
        Long now = System.currentTimeMillis();
        //过期时间：15分钟
        Long timeout = (long) 15 * 60 * 1000;
        Long submitTime = pair.getValue();
        if(now > submitTime + timeout){
            return true;
        }
        return false;
    }
    /*
    map key = userId
    pair key = authCode   pair value=申请重置时间
     */
    private static  final Map<Integer,Pair<String,Long>> authMap = new HashMap<Integer,Pair<String,Long>>();

    @Autowired
    private MailService mailService;

    @Override
    public void sendResetMail(User user) {
        Integer userId = user.getId();
        Long submitTime = System.currentTimeMillis();
        String authCode = SecurityUtil.encryptDes(userId + ";" + user.getName());
        authMap.put(userId,new Pair<String,Long>(authCode,submitTime));
        String url = "localhost:8080/password/reset?autoCode=" + authCode;
        mailService.sendSimpleMail(user.getEmail(),"密码重置","点击链接重置密码： "+url);
    }

    @Override
    public void resetPassword(String authCode) {
        String decryptStr = SecurityUtil.decryptDes(authCode);
        Integer userId = Integer.parseInt(decryptStr.split(";")[0]);
        resetPassword(userId);
    }

    @Override
    public void resetPassword(Integer userId) {
        // TODO: 2018/3/15
    }

    @Override
    public void test() {
        System.out.println(authMap.size());
        authMap.put(3,new Pair<String,Long>("2222wasfd",System.currentTimeMillis()));
        System.out.println(authMap.size());
        try {
            Thread.sleep(20*60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(authMap.size());
    }
}
