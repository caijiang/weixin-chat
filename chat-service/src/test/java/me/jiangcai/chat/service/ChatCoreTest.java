package me.jiangcai.chat.service;

import me.jiangcai.chat.People;
import me.jiangcai.chat.ServiceTestBase;
import me.jiangcai.chat.Words;
import me.jiangcai.wx.message.EventMessage;
import me.jiangcai.wx.message.Message;
import me.jiangcai.wx.message.TextMessage;
import me.jiangcai.wx.message.support.WeixinEvent;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

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
        Message message = weixinRequestHandler.receive(testPublicAccount, eventMessage);
        assertThat(message)
                .isNotNull()
                .isInstanceOf(TextMessage.class)
                .is(new Condition<>(message1 -> {
                    TextMessage textMessage = (TextMessage) message1;
                    return textMessage.getContent().contains("欢迎");
                }, ""));
    }

    /**
     * 用户A加入房间i A发言 用户B,C都看不到
     * 用户B加入房间j B发言 用户A,C都看不到
     * 用户C加入房间i C发言 用户A看到 用户B看不到
     * 用户A退出     C发言 用户A,B都看不到
     */
    @Test
    public void chat() {
        People A = randomPeople();
        People B = randomPeople();
        People C = randomPeople();

        int i = random.nextInt(ChatCore.MAX_ROOT);
        int j = random.nextInt(ChatCore.MAX_ROOT);
        while (j == i) {
            j = random.nextInt(ChatCore.MAX_ROOT);
        }

        A.joinRoom(i);
        Words words = A.makeWords();
        B.assertHasNot(words);
        C.assertHasNot(words);

        B.joinRoom(j);
        words = B.makeWords();
        A.assertHasNot(words);
        C.assertHasNot(words);

        C.joinRoom(i);
        words = C.makeWords();
        A.assertHas(words);
        B.assertHasNot(words);

        A.leaveRoom();
        words = C.makeWords();
        A.assertHasNot(words);
        B.assertHasNot(words);


    }

}