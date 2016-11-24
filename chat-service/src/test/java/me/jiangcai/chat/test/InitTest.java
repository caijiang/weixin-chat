package me.jiangcai.chat.test;

import me.jiangcai.chat.entity.WeixinAccount;
import me.jiangcai.chat.repository.WeixinAccountRepository;
import me.jiangcai.wx.protocol.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author CJ
 */
@Component
public class InitTest {
    @Autowired
    private WeixinAccountRepository weixinAccountRepository;

    @PostConstruct
    public void init() {
        if (weixinAccountRepository.findOne(Protocol.VirtualAppID) == null) {
            WeixinAccount weixinAccount = new WeixinAccount();
            weixinAccount.setAppID(Protocol.VirtualAppID);
            weixinAccount.setOwnerOpenId(UUID.randomUUID().toString());
            weixinAccountRepository.save(weixinAccount);
        }
    }

}
