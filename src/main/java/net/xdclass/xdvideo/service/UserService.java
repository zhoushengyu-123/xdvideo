package net.xdclass.xdvideo.service;

import net.xdclass.xdvideo.domain.User;

public interface UserService {
    User saveWeChatUser (String code) throws  Exception;
    default void zhou(){

    }
}
