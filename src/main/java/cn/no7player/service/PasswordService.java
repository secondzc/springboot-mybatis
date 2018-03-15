package cn.no7player.service;

import cn.no7player.model.User;

/**
 * Created by zhangcy on 2018/3/15
 */
public interface PasswordService {
    void sendResetMail(User user);
    void resetPassword(String authCode);
    void resetPassword(Integer userId);
    void test();
}
