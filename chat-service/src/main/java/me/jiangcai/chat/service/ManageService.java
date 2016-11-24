package me.jiangcai.chat.service;

import me.jiangcai.chat.entity.WeixinAccount;
import me.jiangcai.chat.repository.WeixinAccountRepository;
import me.jiangcai.wx.MessageReply;
import me.jiangcai.wx.message.Message;
import me.jiangcai.wx.message.MessageType;
import me.jiangcai.wx.message.TextMessage;
import me.jiangcai.wx.model.Menu;
import me.jiangcai.wx.model.MenuType;
import me.jiangcai.wx.model.PublicAccount;
import me.jiangcai.wx.protocol.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 管理者 可以通过输入init初始化菜单
 *
 * @author CJ
 */
@Service
public class ManageService implements MessageReply {

    public static final String KEY_ROOM_LIST = "KEY_ROOM_LIST";
    public static final MessageFormat ROOM_NAME = new MessageFormat("聊天室-{0,number}", Locale.CHINA);
    public static final MessageFormat ROOM_SELECTOR = new MessageFormat("ROOM-{0,number}", Locale.CHINA);
    private static final Pattern numberPattern = Pattern.compile("init(\\d+)");

    @Autowired
    private WeixinAccountRepository weixinAccountRepository;

    @Override
    public boolean focus(PublicAccount account, Message message) {
        if (message.getType() == MessageType.text) {
            TextMessage textMessage = (TextMessage) message;
            if (textMessage.getContent().startsWith("init")) {
                WeixinAccount weixinAccount = weixinAccountRepository.getOne(account.getAppID());
                return weixinAccount.getOwnerOpenId() != null
                        && message.getFrom().equals(weixinAccount.getOwnerOpenId());
            } else
                return false;
        } else
            return false;

    }

    @Override
    public Message reply(PublicAccount account, Message message) {
        Matcher matcher = numberPattern.matcher(((TextMessage) message).getContent());
        if (matcher.matches()) {
            int numbers = NumberUtils.parseNumber(matcher.group(1), Integer.class);
            if (numbers > ChatCore.MAX_ROOT || numbers <= 0) {
                TextMessage message1 = new TextMessage();
                message1.setContent("数量不合法,最多" + ChatCore.MAX_ROOT);
                return message1;
            }

            Menu parent = new Menu();
            parent.setName("开始");
            parent.setSubs(new Menu[numbers]);
            for (int i = 0; i < numbers; i++) {
                Menu menu = new Menu();
                parent.getSubs()[i] = menu;
                menu.setName(ROOM_NAME.format(new Object[]{i}));
                menu.setData(ROOM_SELECTOR.format(new Object[]{i}));
                menu.setType(MenuType.click);
            }

            Protocol.forAccount(account).createMenu(new Menu[]{
                    parent
            });
            TextMessage textMessage = new TextMessage();
            textMessage.setContent("菜单已设置");
            return textMessage;
        } else
            return null;
    }
}
