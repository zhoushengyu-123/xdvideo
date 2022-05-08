package net.xdclass.xdvideo.service;

import net.xdclass.xdvideo.domain.VideoOrder;
import org.apache.ibatis.annotations.Param;

public interface VideoOrderService {

    /**
     * 下单接口
     * @param videoOrderDto
     * @return
     */
    String save(VideoOrder videoOrderDto) throws Exception;

    /**
     *
     * @param id
     * @return
     */
    VideoOrder findById(int id);
    /**
     * 根据流水号查找订单
     * @param outTradeNo
     * @return
     */
    VideoOrder findByOutTradeNo(String outTradeNo);


    /**
     * 根据流水号更新订单
     * @param videoOrder
     * @return
     */
    int updateVideoOderByOutTradeNo(VideoOrder videoOrder);

    VideoOrder findTrueByOutTradeNo(String outTradeNo);
}
