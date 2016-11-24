package me.jiangcai.chat.service;

import me.jiangcai.chat.entity.WeixinAccount;
import me.jiangcai.chat.repository.WeixinAccountRepository;
import me.jiangcai.wx.PublicAccountSupplier;
import me.jiangcai.wx.TokenType;
import me.jiangcai.wx.model.PublicAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        return weixinAccountRepository.findAll().stream()
                .map(getWeixinAccountPublicAccountFunction())
                .collect(Collectors.toList());
    }

    private Function<WeixinAccount, PublicAccount> getWeixinAccountPublicAccountFunction() {
        return weixinAccount -> {
            if (weixinAccount == null)
                return null;
            final PublicAccount publicAccount = new PublicAccount() {
                @Override
                public PublicAccountSupplier getSupplier() {
                    return WeixinAccountService.this;
                }
            };
            publicAccount.setAppID(weixinAccount.getAppID());
            publicAccount.setAppSecret(weixinAccount.getAppSecret());
            publicAccount.setInterfaceToken(weixinAccount.getInterfaceToken());
            publicAccount.setInterfaceURL(weixinAccount.getInterfaceURL());
            publicAccount.setAccessToken(weixinAccount.getAccessToken());
            publicAccount.setTimeToExpire(weixinAccount.getTimeToExpire());
            publicAccount.setJavascriptTicket(weixinAccount.getJavascriptTicket());
            publicAccount.setJavascriptTimeToExpire(weixinAccount.getJavascriptTimeToExpire());
            return publicAccount;
        };
    }

    @Override
    public PublicAccount findByIdentifier(String identifier) {
        return getWeixinAccountPublicAccountFunction().apply(weixinAccountRepository.findOne(identifier));
    }

    @Override
    public void updateToken(PublicAccount account, TokenType type, String token, LocalDateTime timeToExpire) throws Throwable {
        WeixinAccount account1 = weixinAccountRepository.getOne(account.getAppID());
        if (type == TokenType.access) {
            account1.setAccessToken(token);
            account1.setTimeToExpire(timeToExpire);
        } else if (type == TokenType.javascript) {
            account1.setJavascriptTicket(token);
            account1.setJavascriptTimeToExpire(timeToExpire);
        }
        weixinAccountRepository.save(account1);
    }

    @Override
    public void getTokens(PublicAccount account) {

    }

    @Override
    public PublicAccount findByHost(String host) {
        return getWeixinAccountPublicAccountFunction().apply(weixinAccountRepository.findOne(host));
    }
}
