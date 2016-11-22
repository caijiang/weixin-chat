package me.jiangcai.chat.service;

import me.jiangcai.wx.WeixinUserService;
import me.jiangcai.wx.model.PublicAccount;
import me.jiangcai.wx.model.UserAccessResponse;
import me.jiangcai.wx.model.WeixinUser;
import org.springframework.stereotype.Service;

/**
 * Created by luffy on 2016/11/23.
 *
 * @author luffy luffy.ja at gmail.com
 */
@Service
public class WeixinUserServiceImpl implements WeixinUserService {
    @Override
    public <T> T userInfo(PublicAccount publicAccount, String s, Class<T> aClass, Object o) {
        return null;
    }

    @Override
    public void updateUserToken(PublicAccount publicAccount, UserAccessResponse userAccessResponse, Object o) {

    }

    @Override
    public WeixinUser getTokenInfo(PublicAccount publicAccount, String s) {
        return null;
    }
}
