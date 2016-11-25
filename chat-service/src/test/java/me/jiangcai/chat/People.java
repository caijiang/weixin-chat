package me.jiangcai.chat;

import me.jiangcai.chat.service.ManageService;
import me.jiangcai.wx.couple.WeixinRequestHandler;
import me.jiangcai.wx.message.EventMessage;
import me.jiangcai.wx.message.Message;
import me.jiangcai.wx.message.TextMessage;
import me.jiangcai.wx.message.support.WeixinEvent;
import me.jiangcai.wx.model.PublicAccount;
import me.jiangcai.wx.protocol.Protocol;
import org.assertj.core.api.Condition;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 虚拟出来的一个人
 *
 * @author CJ
 */
public class People {

    private final WeixinRequestHandler weixinRequestHandler;
    private final PublicAccount publicAccount;
    private final String openId;

    public People(ApplicationContext applicationContext, PublicAccount publicAccount) {
        openId = UUID.randomUUID().toString();
        this.weixinRequestHandler = applicationContext.getBean(WeixinRequestHandler.class);
        this.publicAccount = publicAccount;
    }

    /**
     * 加入房间
     *
     * @param roomIndex
     */
    public void joinRoom(int roomIndex) {
        EventMessage eventMessage = new EventMessage();
        initMessage(eventMessage);
        eventMessage.setEvent(WeixinEvent.CLICK);
        eventMessage.setKey(ManageService.ROOM_SELECTOR.format(new Object[]{roomIndex}));

        Message message = weixinRequestHandler.receive(publicAccount, eventMessage);
        assertThat(message)
                .isNotNull()
                .isInstanceOf(TextMessage.class)
                .is(new Condition<>(message1 -> {
                    TextMessage textMessage = (TextMessage) message1;
                    return textMessage.getContent().contains("欢迎");
                }, ""));
    }

    private void initMessage(Message message) {
        message.setFrom(openId);
        message.setTime(LocalDateTime.now());
    }

    /**
     * 随机发言
     *
     * @return
     */
    public Words makeWords() {
        Message message;
        switch (new Random().nextInt(1)) {
            case 0:
            default:
                message = new TextMessage(UUID.randomUUID().toString());
        }

        initMessage(message);
        weixinRequestHandler.receive(publicAccount, message);
        return new Words(message);
    }

    /**
     * 没有收到这句话
     *
     * @param words
     */
    public void assertHasNot(Words words) {
        List<Message> messageList = myList();
        assertThat(messageList)
                .doNotHave(new Condition<>(message -> message.sameContent(words.getMessage()), ""));
    }

    /**
     * 收到了这句话
     *
     * @param words
     */
    public void assertHas(Words words) {
        List<Message> messageList = myList();
        assertThat(messageList)
                .have(new Condition<>(message -> message.sameContent(words.getMessage()), ""));

    }

    private List<Message> myList() {
        return Protocol.virtualActions.stream()
                .filter(action -> action.getMethod().getName().equals("send"))
                .filter(action -> action.getArguments().length == 1 && action.getArguments()[0] instanceof Message)
                .filter(action -> {
                    Message message = (Message) action.getArguments()[0];
                    return message.getTo().equals(openId);
                })
                .map(action -> (Message) action.getArguments()[0])
                .collect(Collectors.toList());
    }

    /**
     * 离开当前房间
     */
    public void leaveRoom() {
        TextMessage textMessage = new TextMessage("#退出");
        initMessage(textMessage);
        weixinRequestHandler.receive(publicAccount, textMessage);
    }
}
