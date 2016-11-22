package me.jiangcai.chat.entity;

import me.jiangcai.wx.PublicAccountSupplier;
import me.jiangcai.wx.model.PublicAccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by luffy on 2016/11/23.
 * 微信公众号
 *
 * @author luffy luffy.ja at gmail.com
 */
@Entity
public class WeixinAccount extends PublicAccount {
    @Override
    public PublicAccountSupplier getSupplier() {
        return null;
    }

    @Id
    @Column(length = 50)
    @Override
    public String getAppID() {
        return super.getAppID();
    }

    @Column(length = 50)
    @Override
    public String getAppSecret() {
        return super.getAppSecret();
    }

    @Column(length = 50)
    @Override
    public String getInterfaceURL() {
        return super.getInterfaceURL();
    }

    @Override
    public String getInterfaceToken() {
        return super.getInterfaceToken();
    }

    @Override
    public String getAccessToken() {
        return super.getAccessToken();
    }

    @Column(columnDefinition = "datetime")
    @Override
    public LocalDateTime getTimeToExpire() {
        return super.getTimeToExpire();
    }

    @Override
    public String getJavascriptTicket() {
        return super.getJavascriptTicket();
    }

    @Column(columnDefinition = "datetime")
    @Override
    public LocalDateTime getJavascriptTimeToExpire() {
        return super.getJavascriptTimeToExpire();
    }
}
