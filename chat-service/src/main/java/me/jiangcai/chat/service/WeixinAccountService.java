package me.jiangcai.chat.service;

import me.jiangcai.chat.repository.WeixinAccountRepository;
import me.jiangcai.wx.PublicAccountSupplier;
import me.jiangcai.wx.TokenType;
import me.jiangcai.wx.model.PublicAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by luffy on 2016/11/23.
 *
 * @author luffy luffy.ja at gmail.com
 */
@Service
public class WeixinAccountService implements PublicAccountSupplier {

    @Autowired
    public WeixinAccountRepository weixinAccountRepository;

    @Override
    public List<? extends PublicAccount> getAccounts() {
        return weixinAccountRepository.findAll();
    }

    @Override
    public PublicAccount findByIdentifier(String identifier) {
        return null;
    }

    @Override
    public void updateToken(PublicAccount account, TokenType type, String token, LocalDateTime timeToExpire) throws Throwable {

    }

    @Override
    public void getTokens(PublicAccount account) {

    }

    @Override
    public PublicAccount findByHost(String host) {
        return null;
    }
}
