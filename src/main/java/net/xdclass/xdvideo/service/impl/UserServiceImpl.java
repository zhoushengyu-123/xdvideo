package net.xdclass.xdvideo.service.impl;

import net.xdclass.xdvideo.config.WeChatConfig;
import net.xdclass.xdvideo.domain.User;
import net.xdclass.xdvideo.service.UserService;
import net.xdclass.xdvideo.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
  public  WeChatConfig weChatConfig;

    @Override
    public User saveWeChatUser(String code)  throws  Exception{
           User user=new User();
          try {
            String accessTokenUrl = String.format(WeChatConfig.getOpenAccessTokenUrl(), weChatConfig.getOpenAppid(), weChatConfig.getOpenAppsecret(), code);
            //获取access_token
            Map<String, Object> baseMap = HttpUtils.doGet(accessTokenUrl);
            System.out.println(baseMap);
            return new User();
        }
        catch (Exception ex)
        {
              throw ex;
        }

    }
}
