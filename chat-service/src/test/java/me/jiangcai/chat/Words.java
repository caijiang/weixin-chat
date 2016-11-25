package me.jiangcai.chat;

import lombok.Data;
import me.jiangcai.wx.message.Message;

/**
 * 其实是一种消息
 *
 * @author CJ
 */
@Data
public class Words {

    private final Message message;

    public Words(Message message) {
        this.message = message;
    }
}
