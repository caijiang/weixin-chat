package me.jiangcai.chat.service;

import me.jiangcai.chat.ServiceTestBase;
import me.jiangcai.wx.message.Message;
import me.jiangcai.wx.message.TextMessage;
import me.jiangcai.wx.protocol.Protocol;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试管理功能
 *
 * @author CJ
 */
public class ManageServiceTest extends ServiceTestBase {

    @Test
    public void manageIt() {
        int size = Protocol.virtualActions.size();
        TextMessage textMessage = new TextMessage();
        textMessage.setContent("init5");
        textMessage.setTime(LocalDateTime.now());
        textMessage.setFrom(UUID.randomUUID().toString());
        weixinRequestHandler.receive(testPublicAccount, textMessage);
        assertThat(Protocol.virtualActions)
                .hasSize(size);
        textMessage.setFrom(testWeixinAccount.getOwnerOpenId());
        Message message = weixinRequestHandler.receive(testPublicAccount, textMessage);
        assertThat(message)
                .isNotNull()
                .isInstanceOf(TextMessage.class)
                .is(new Condition<>(message1 -> {
                    TextMessage reply = (TextMessage) message1;
                    return reply.getContent().contains("已设置");
                }, ""));
        assertThat(Protocol.virtualActions)
                .hasSize(size + 1);
    }

}