package me.jiangcai.chat.service;

import me.jiangcai.chat.ServiceTestBase;
import me.jiangcai.wx.message.EventMessage;
import me.jiangcai.wx.message.support.WeixinEvent;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author CJ
 */
public class ChatCoreTest extends ServiceTestBase {

    // 获取聊天室列表
    @Test
    public void list() {
        EventMessage eventMessage = new EventMessage();
        eventMessage.setEvent(WeixinEvent.CLICK);
        eventMessage.setKey(ManageService.ROOM_SELECTOR.format(new Object[]{1}));
        eventMessage.setTime(LocalDateTime.now());
        weixinRequestHandler.receive(testPublicAccount, eventMessage);
    }

}