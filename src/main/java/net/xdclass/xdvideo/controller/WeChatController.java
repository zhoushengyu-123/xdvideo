package net.xdclass.xdvideo.controller;


import net.xdclass.xdvideo.config.WeChatConfig;
import net.xdclass.xdvideo.domain.JsonData;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Controller
@RequestMapping("/api")
public class WeChatController {
     @Autowired
     public WeChatConfig weChatConfig;
    @GetMapping("loginurl")
    public JsonData loginurl(@RequestParam(value = "url",defaultValue = "lisi") String url, HttpServletResponse httpServletResponse){
        try {

            String  redicturl= URLEncoder.encode(weChatConfig.getOpenRedirectUrl(),"GBK");
            String callBackUrl=String.format(weChatConfig.getOpenQrcodeUrl(),weChatConfig.getOpenAppid(),redicturl,url);
            System.out.println(callBackUrl);
        }
        catch (Exception ex)
        {

        }

        return JsonData.buildSuccess();


   }

}
