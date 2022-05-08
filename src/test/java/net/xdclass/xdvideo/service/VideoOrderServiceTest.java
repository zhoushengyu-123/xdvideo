package net.xdclass.xdvideo.service;

import junit.framework.TestCase;
import net.xdclass.xdvideo.domain.Video;
import net.xdclass.xdvideo.domain.VideoOrder;
import net.xdclass.xdvideo.mapper.VideoOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoOrderServiceTest extends TestCase {
    @Autowired
    private VideoOrderMapper videoOrderMapper;

     @Test
     @Transactional(propagation= Propagation.SUPPORTS)
    public void testSave() {
//         String result = Stream.of("springboot", "mysql", "html5",
//                 "css3").collect(Collectors.joining(",", "[", "]"));
//         Map<String,String> s = new HashMap<>();
//         s.put("1","1");
//          List<String> list1=new ArrayList<>();
//          list1.add("zhou");
//          list1.add("zhou");
//          String aa=list1.stream().collect(Collectors.joining(",", "[", "]"));
//         Set<Map.Entry<String,String>> ffff= s.entrySet();
//         List<Map.Entry<String,String>>   list111=ffff.stream().collect(Collectors.toList());
//         long f= System.currentTimeMillis()/1000;
//         VideoOrder videoOrder2 = new VideoOrder();
//         videoOrder2.setDel(0);
//         videoOrder2.setTotalFee(111);
//         videoOrder2.setHeadImg("http://www.baidu.com");
//         videoOrder2.setVideoTitle("springBoot高级视频教程");
//         videoOrderMapper.insert(videoOrder2);
//         this.ceshi2();



    }
      public void  ceshi2(){
//          VideoOrder videoOrder2 = new VideoOrder();
//          videoOrder2.setDel(0);
//          videoOrder2.setTotalFee(11);
//          videoOrder2.setHeadImg("http://www.baidu.com");
//          videoOrder2.setVideoTitle("springBoot高级视频教程");
//          videoOrder2.setNickname("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
//          videoOrderMapper.insert(videoOrder2);

      }


    public void testFindByOutTradeNo() {
    }

    public void testUpdateVideoOderByOutTradeNo() {
    }
}