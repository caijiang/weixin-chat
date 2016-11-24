package me.jiangcai.chat;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by luffy on 2016/11/23.
 *
 * @author luffy luffy.ja at gmail.com
 */
@Configuration
@Import({DSConfig.class})
@ComponentScan("me.jiangcai.chat.test")
@ImportResource("classpath:/datasource_local.xml")
class TestConfig {
}
