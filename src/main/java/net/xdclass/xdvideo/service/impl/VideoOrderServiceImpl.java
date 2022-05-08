package net.xdclass.xdvideo.service.impl;

import net.xdclass.xdvideo.config.WeChatConfig;
import net.xdclass.xdvideo.domain.User;
import net.xdclass.xdvideo.domain.Video;
import net.xdclass.xdvideo.domain.VideoOrder;
import net.xdclass.xdvideo.mapper.UserMapper;
import net.xdclass.xdvideo.mapper.VideoMapper;
import net.xdclass.xdvideo.mapper.VideoOrderMapper;
import net.xdclass.xdvideo.service.VideoOrderService;
import net.xdclass.xdvideo.utils.CommonUtils;
import net.xdclass.xdvideo.utils.HttpUtils;
import net.xdclass.xdvideo.utils.WXPayUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class VideoOrderServiceImpl  implements VideoOrderService {

    @Autowired
    private VideoOrderMapper videoOrderMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeChatConfig weChatConfig;
    @Override
    public String save(VideoOrder videoOrderDto) throws Exception {
        Integer integer=0;

            Video video = videoMapper.findById(videoOrderDto.getVideoId());
            User user = userMapper.findByid(videoOrderDto.getUserId());
            //生成订单

            VideoOrder videoOrder = new VideoOrder();

            videoOrder.setTotalFee(video.getPrice());

            videoOrder.setVideoImg(video.getCoverImg());

            videoOrder.setVideoTitle(video.getTitle());

            videoOrder.setCreateTime(new Date());

            videoOrder.setVideoId(video.getId());

            videoOrder.setState(0);

            videoOrder.setUserId(user.getId());

            videoOrder.setHeadImg(user.getHeadImg());
            videoOrder.setNickname(user.getName());

            videoOrder.setDel(0);
            videoOrder.setIp(videoOrderDto.getIp());
            videoOrder.setOutTradeNo(CommonUtils.generateUUID());

            integer = videoOrderMapper.insert(videoOrder);
            String str1= unifield(videoOrder);
            return str1;
    }
    @Override
    public VideoOrder findById(int id) {
       return  videoOrderMapper.findById(id);
    }
    @Override
    public VideoOrder findByOutTradeNo(String outTradeNo) {
        return  videoOrderMapper.findByOutTradeNo(outTradeNo);
    }

    @Override
    public int updateVideoOderByOutTradeNo(VideoOrder videoOrder) {

       return   videoOrderMapper.updateVideoOderByOutTradeNo(videoOrder);
    }

    /**
     *
     * @return
     */
    public  String unifield(VideoOrder videoOrder) throws  Exception{
        SortedMap<String,String> sortedMap=new TreeMap<>();
        //生成签名
        SortedMap<String,String> params = new TreeMap<>();
        params.put("appid",weChatConfig.getAppId());
        params.put("mch_id", weChatConfig.getMchId());
        params.put("nonce_str",CommonUtils.generateUUID());
        params.put("body",videoOrder.getVideoTitle());
        params.put("out_trade_no",videoOrder.getOutTradeNo());
        params.put("total_fee",videoOrder.getTotalFee().toString());
        params.put("spbill_create_ip",videoOrder.getIp());
        params.put("notify_url",weChatConfig.getPayCallbackUrl());
        params.put("trade_type","NATIVE");
        //sign签名
        String sign = WXPayUtils.createSign(params, weChatConfig.getKey());
        params.put("sign",sign);
        //map转xml
        String payXml = WXPayUtils.mapToXml(params);

        //统一下单
        String orderStr = HttpUtils.doPost(WeChatConfig.getUnifiedOrderUrl(),payXml,4000);
        if(null == orderStr) {
            return null;
        }
        Map<String, String> unifiedOrderMap =  WXPayUtils.xmlToMap(orderStr);
        System.out.println(unifiedOrderMap.toString());
         String returnMsg= unifiedOrderMap.get("return_msg");
        returnMsg = new String(returnMsg.getBytes("ISO-8859-1"), "UTF-8");
        if(unifiedOrderMap != null) {
            return unifiedOrderMap.get("code_url");
        }

        return null;

    }
     public  VideoOrder findTrueByOutTradeNo( String outTradeNo){
         return videoOrderMapper.findTrueByOutTradeNo(outTradeNo);
     }
}
