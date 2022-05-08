package net.xdclass.xdvideo.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.StringUtils;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.xdclass.xdvideo.config.WeChatConfig;
import net.xdclass.xdvideo.domain.JsonData;
import net.xdclass.xdvideo.domain.VideoOrder;
import net.xdclass.xdvideo.enums.StateEnum;
import net.xdclass.xdvideo.service.VideoOrderService;
import net.xdclass.xdvideo.utils.IpUtils;
import net.xdclass.xdvideo.utils.WXPayUtils;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

@RequestMapping("/api/order")
@RestController
public class OrderController {
       private Logger logger= LoggerFactory.getLogger(this.getClass());


    @Autowired
    private VideoOrderService videoOrderService;
    @Autowired
    private WeChatConfig weChatConfig;
    @GetMapping("ceshi1")
    public  void ceshiEXCTPITON(){
        int i=4/0;
    }
    @PostMapping("add")
    @Transactional(propagation= Propagation.REQUIRED)
    public JsonData savaOrder(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                              VideoOrder videoOrderreq)
                               {

       try {

            logger.info("s");
           String FF=StateEnum.SUCCESS.getStateInfo();
         int videoId=1;
           String ip = IpUtils.getIpAddr(httpServletRequest);

           int userId = 1;
           VideoOrder videoOrder = new VideoOrder();
           videoOrder.setUserId(userId);
           videoOrder.setVideoId(videoId);
           videoOrder.setIp(ip);
          String codeUrl=  videoOrderService.save(videoOrder);
           //生成二维码配置
           Map<EncodeHintType,Object> hints =  new HashMap<>();

           //设置纠错等级
           hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.L);
           //编码类型
           hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");

           BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE,400,400,hints);
           OutputStream out =  httpServletResponse.getOutputStream();

           MatrixToImageWriter.writeToStream(bitMatrix,"png",out);

       }
       catch (Exception ex)
       {
           ex.printStackTrace();





       }
           finally {
           return JsonData.buildSuccess();
       }
    }
    @PostMapping("orderCallBack")
    public  synchronized  void  orderCallBack(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) throws  Exception{



        try {
            InputStream inputStream =httpServletRequest.getInputStream();
            Reader reader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader br = new BufferedReader(reader);
            StringBuilder sb=new StringBuilder();
            char[] ch = new char[1024];
            int num = 0 ;
            while((num = reader.read(ch)) != -1){
                sb.append(new String(ch,0,num));
            }

            br.close();
            inputStream.close();
            Map<String,String>  mapstr= WXPayUtils.xmlToMap(sb.toString());
            //转为有序的map
            SortedMap<String,String> sortedMap=WXPayUtils.getSortedMap(mapstr);
            if(WXPayUtils.isCorrectSign(sortedMap,weChatConfig.getKey()))
            {
                String outTradeNo = sortedMap.get("out_trade_no");

                VideoOrder dbVideoOrder = videoOrderService.findByOutTradeNo(outTradeNo);
                if(sortedMap.get("result_code").equals(StateEnum.SUCCESS.getStateInfo())) {
                    VideoOrder videoOrder = new VideoOrder();
                    videoOrder.setOpenid(sortedMap.get("openid"));
                    videoOrder.setOutTradeNo(outTradeNo);
                    videoOrder.setNotifyTime(new Date());
                    videoOrder.setState(1);
                    int rows = videoOrderService.updateVideoOderByOutTradeNo(videoOrder);

                    if(rows == 1){ //通知微信订单处理成功
                        httpServletResponse.setContentType("text/xml");
                        httpServletResponse.getWriter().println("Success");
                    }
                    VideoOrder videoOrder1=videoOrderService.findTrueByOutTradeNo(outTradeNo);

                    if(videoOrder!=null&&!videoOrder1.equals(""))
                    {
                        httpServletResponse.setContentType("text/xml");
                        httpServletResponse.getWriter().println("Success");
                    }
                }

            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().println("Success");
        }
        finally {
            System.out.println("这里记录日志");
        }
        httpServletResponse.setContentType("text/xml");
        httpServletResponse.getWriter().println("Success");
    }

}
