package me.jiangcai.chat.web.config;

import me.jiangcai.chat.ServiceConfig;
import me.jiangcai.wx.web.WeixinWebSpringConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by luffy on 2016/11/23.
 * 入口
 *
 * @author luffy luffy.ja at gmail.com
 */
@Configuration
@Import({ServiceConfig.class, WeixinWebSpringConfig.class})
@ComponentScan("me.jiangcai.wxtest.controller")
@EnableWebMvc
public class WebConfig {
}
