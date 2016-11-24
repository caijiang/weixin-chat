package me.jiangcai.chat;

import me.jiangcai.chat.repository.WeixinAccountRepository;
import me.jiangcai.lib.test.SpringWebTest;
import me.jiangcai.wx.PublicAccountSupplier;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private WeixinAccountRepository weixinAccountRepository;
    @Autowired
    private PublicAccountSupplier publicAccount;

    @Test
    public void go() throws Exception {
//        weixinAccountRepository.deleteAll();
//        if (weixinAccountRepository.count()==0){
//            WeixinAccount weixinAccount = new WeixinAccount();
//            weixinAccount.setAppID("wx59b0162cdf0967af");
//            weixinAccount.setAppSecret("ffcf655fce7c4175bbddae7b594c4e27");
//            weixinAccount.setInterfaceURL("http://localhost/weixin/");
//            weixinAccount.setInterfaceToken("jiangcai");
//            weixinAccountRepository.save(weixinAccount);
//        }
//
//        mockMvc.perform(get("/weixin?signature=ae15868cea60b76f710c410890aff1b86b43ff2d&echostr=6464031700169973663&timestamp=1479958291&nonce=824599550"))
//                .andDo(print());
    }

}