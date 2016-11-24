package me.jiangcai.chat.model;

import lombok.Data;

import java.util.List;

/**
 * @author CJ
 */
@Data
public class Room {

    private List<Chatter> chatterList;

}
