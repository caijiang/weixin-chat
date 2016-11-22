package me.jiangcai.chat;

import me.jiangcai.lib.test.SpringWebTest;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by luffy on 2016/11/23.
 *
 * @author luffy luffy.ja at gmail.com
 */
@WebAppConfiguration
@ContextConfiguration(classes = {ServiceConfig.class, TestConfig.class})
public class ServiceConfigTest extends SpringWebTest {

    @Test
    public void go() {
        System.out.println(1);
    }

}