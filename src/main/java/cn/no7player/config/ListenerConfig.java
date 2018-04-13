package cn.no7player.config;

import cn.no7player.listeners.ApplicationStartListener;
import cn.no7player.listeners.SimpleApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhangcy on 2018/4/13
 */
@Configuration
public class ListenerConfig {
    @Bean
    public ApplicationStartListener ApplicationStartListener(){
        return new ApplicationStartListener();
    }
    @Bean
    public SimpleApplicationListener simpleApplicationListener(){
        return new SimpleApplicationListener();
    }
}
