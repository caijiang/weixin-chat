package me.jiangcai.chat.service;

import me.jiangcai.wx.MessageReply;
import me.jiangcai.wx.message.Message;
import me.jiangcai.wx.model.PublicAccount;
import org.springframework.stereotype.Service;

/**
 * Created by luffy on 2016/11/23.
 *
 * @author luffy luffy.ja at gmail.com
 */
@Service
public class ChatCore implements MessageReply {
    @Override
    public boolean focus(PublicAccount publicAccount, Message message) {
        return true;
    }

    @Override
    public Message reply(PublicAccount publicAccount, Message message) {
        return null;
    }
}
