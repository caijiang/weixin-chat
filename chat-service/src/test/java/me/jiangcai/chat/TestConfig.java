package me.jiangcai.chat;

import me.jiangcai.wx.test.WeixinTestConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by luffy on 2016/11/23.
 *
 * @author luffy luffy.ja at gmail.com
 */
@Configuration
@Import({WeixinTestConfig.class, DSConfig.class})
@ImportResource("classpath:/datasource_local.xml")
class TestConfig {
}
