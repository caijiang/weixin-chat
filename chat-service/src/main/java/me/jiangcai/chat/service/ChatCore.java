package me.jiangcai.chat.service;

import me.jiangcai.chat.model.Room;
import me.jiangcai.wx.MessageReply;
import me.jiangcai.wx.message.EventMessage;
import me.jiangcai.wx.message.Message;
import me.jiangcai.wx.message.MessageType;
import me.jiangcai.wx.message.TextMessage;
import me.jiangcai.wx.message.support.WeixinEvent;
import me.jiangcai.wx.model.PublicAccount;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luffy on 2016/11/23.
 *
 * @author luffy luffy.ja at gmail.com
 */
@Service
public class ChatCore implements MessageReply {

    public static final int MAX_ROOT = 20;
    private final Map<String, List<Room>> allRooms = new HashMap<>();

    @Override
    public boolean focus(PublicAccount publicAccount, Message message) {
        if (message instanceof EventMessage) {
            EventMessage eventMessage = (EventMessage) message;
            if (eventMessage.getEvent() == WeixinEvent.CLICK) {
                try {
                    Object[] arg = ManageService.ROOM_SELECTOR.parse(eventMessage.getKey());
                    // 加入这个房间
                } catch (ParseException e) {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public Message reply(PublicAccount publicAccount, Message message) {
        if (message.getType() == MessageType.text) {
            TextMessage textMessage = new TextMessage();
            textMessage.setContent("知道了");
            return textMessage;
        }
        return null;
    }
}
