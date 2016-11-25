package me.jiangcai.chat.model;

import lombok.Data;
import me.jiangcai.wx.message.Message;
import me.jiangcai.wx.model.PublicAccount;
import me.jiangcai.wx.protocol.Protocol;
import me.jiangcai.wx.protocol.exception.ProtocolException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author CJ
 */
@Data
public class Room {

    private static final Log log = LogFactory.getLog(Room.class);
    private final List<Chatter> chatterList = Collections.synchronizedList(new ArrayList<>());

    /**
     * 转发消息给除了发送者的其他所有人
     *
     * @param publicAccount 公众号
     * @param message       消息
     */
    public void forwardMessage(PublicAccount publicAccount, Message message) {
        String originFrom = message.getFrom();
        Protocol protocol = Protocol.forAccount(publicAccount);
        for (Chatter chatter : chatterList) {
            if (!chatter.getOpenId().equals(originFrom)) {
                Message newMessage = message.clone();
                newMessage.setTo(chatter.getOpenId());
                try {
                    protocol.send(newMessage);
                } catch (ProtocolException ex) {
                    log.debug("send failed", ex);
                }
            }
        }
    }
}
