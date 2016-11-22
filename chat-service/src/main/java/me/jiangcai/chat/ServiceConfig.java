package me.jiangcai.chat;

import me.jiangcai.wx.WeixinSpringConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by luffy on 2016/11/23.
 *
 * @author luffy luffy.ja at gmail.com
 */
@Configuration
@Import(WeixinSpringConfig.class)
@ComponentScan(value = {"me.jiangcai.chat.service"})
@EnableJpaRepositories(basePackages = "me.jiangcai.chat.repository")
//@EnablePluginRegistries()
public class ServiceConfig {
}
