package me.jiangcai.chat.service;

import me.jiangcai.chat.model.Chatter;
import me.jiangcai.chat.model.Room;
import me.jiangcai.wx.MessageReply;
import me.jiangcai.wx.message.EventMessage;
import me.jiangcai.wx.message.Message;
import me.jiangcai.wx.message.TextMessage;
import me.jiangcai.wx.message.support.WeixinEvent;
import me.jiangcai.wx.model.PublicAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
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
    private final Map<String, List<Room>> allRooms = Collections.synchronizedMap(new HashMap<>());
    private final Map<String, Room> userRoom = Collections.synchronizedMap(new HashMap<>());
    @Autowired
    private ManageService manageService;

    @Override
    public boolean focus(PublicAccount publicAccount, Message message) {
        if (manageService.focus(publicAccount, message))
            return false;
        if (message instanceof EventMessage) {
            EventMessage eventMessage = (EventMessage) message;
            if (eventMessage.getEvent() == WeixinEvent.CLICK) {
                try {
                    Object[] arg = ManageService.ROOM_SELECTOR.parse(eventMessage.getKey());
                    // 加入这个房间
                    joinRoom(publicAccount, message.getFrom(), (Number) arg[0]);
                    return true;
                } catch (ParseException e) {
                    return false;
                }
            }
        }
        return true;
    }

    private void joinRoom(PublicAccount publicAccount, String openId, Number index) {
        List<Room> rooms = allRooms.get(publicAccount.getAppID());
        if (rooms == null) {
            rooms = Collections.synchronizedList(new ArrayList<>(MAX_ROOT));
            for (int i = 0; i < MAX_ROOT; i++) {
                rooms.add(new Room());
            }
            allRooms.put(publicAccount.getAppID(), rooms);
        }

        Room room = rooms.get(index.intValue());
        // 退出原来的房间
        leaveRoom(publicAccount, openId);

        final Chatter e = new Chatter();
        e.setOpenId(openId);
        room.getChatterList().add(e);
        userRoom.put(openId + "@" + publicAccount.getAppID(), room);
    }

    private void leaveRoom(PublicAccount publicAccount, String openId) {
        final String key = openId + "@" + publicAccount.getAppID();
        Room room = userRoom.get(key);
        if (room != null) {
            room.getChatterList().removeIf(chatter -> chatter.getOpenId().equals(openId));
            userRoom.remove(key);
        }
    }

    @Override
    public Message reply(PublicAccount publicAccount, Message message) {
        if (message instanceof EventMessage) {
            Room room = currentRoom(publicAccount, message.getFrom());
            TextMessage textMessage = new TextMessage();
            textMessage.setContent(room.toString() + "欢迎你");
            return textMessage;
        }
        if (message instanceof TextMessage) {
            if (((TextMessage) message).getContent().equals("#退出")) {
                leaveRoom(publicAccount, message.getFrom());
                TextMessage textMessage = new TextMessage();
                textMessage.setContent("再见");
                return textMessage;
            }
        }

        Room room = currentRoom(publicAccount, message.getFrom());
        if (room != null) {
            room.forwardMessage(publicAccount, message);
        }
        return null;
    }

    private Room currentRoom(PublicAccount publicAccount, String openId) {
        return userRoom.get(openId + "@" + publicAccount.getAppID());
    }
}
