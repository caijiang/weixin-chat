package me.jiangcai.chat.repository;

import me.jiangcai.chat.entity.WeixinAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by luffy on 2016/11/23.
 *
 * @author luffy luffy.ja at gmail.com
 */
public interface WeixinAccountRepository extends JpaRepository<WeixinAccount, String> {
}
