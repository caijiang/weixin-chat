package me.jiangcai.chat.entity;

import lombok.Getter;
import lombok.Setter;

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
@Setter
@Getter
@Entity
public class WeixinAccount {

    // 基本
    @Id
    @Column(length = 50)
    private String appID;
    @Column(length = 50)
    private String appSecret;
    // 接口
    /**
     * 微信接口配置信息:URL,为了提高效率,规定该URL必须以斜杠结尾!
     */
    @Column(length = 200)
    private String interfaceURL;
    @Column(length = 200)
    private String interfaceToken;
    // 句柄信息
    private String accessToken;
    @Column(columnDefinition = "datetime")
    private LocalDateTime timeToExpire;
    //
    /**
     * jsapi_ticket
     */
    private String javascriptTicket;
    @Column(columnDefinition = "datetime")
    private LocalDateTime javascriptTimeToExpire;

    /**
     * 管理者的openId
     */
    @Column(length = 50)
    private String ownerOpenId;


}
