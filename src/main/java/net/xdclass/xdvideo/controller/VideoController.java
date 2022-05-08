package net.xdclass.xdvideo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import net.xdclass.xdvideo.domain.JsonData;
import net.xdclass.xdvideo.domain.User;
import net.xdclass.xdvideo.domain.Video;
import net.xdclass.xdvideo.domain.VideoOrder;
import net.xdclass.xdvideo.mapper.UserMapper;
import net.xdclass.xdvideo.mapper.VideoMapper;
import net.xdclass.xdvideo.mapper.VideoOrderMapper;
import net.xdclass.xdvideo.service.UserService;
import net.xdclass.xdvideo.service.VideoService;
import net.xdclass.xdvideo.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/video/")
public class VideoController {
	@Autowired
	public VideoMapper videoMapper;
	@Autowired
	public VideoOrderMapper videoorder;
	@Autowired
    private  VideoService videoService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserService userService;
	@RequestMapping("test")
	public String test() {

		return "hello xdclass.net";
	}
	private  final  Gson gson=new Gson();

	@RequestMapping(value = "ceshi", method = RequestMethod.GET)
	public void ceshi() {
		userService.zhou();


	}

	public void insert() {




	}

	/**
	 *
	 * @param page
	 * @param size
	 * @return
	 */
	@Transactional
	@PostMapping("page")
	public JsonData pageVideo(@RequestParam(value = "page",required = true)int page,
							  @RequestParam(value = "size",defaultValue = "10")int size, HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) {

        try {

			String f=IpUtils.getIpAddr(httpServletRequest);
			User user= userMapper.findByid(1);

        	Video video=new Video();
        	video.setId(1);
        	video.setTitle("SpringBoot+Maven整合Websocket课程11111");

        	videoMapper.update(video);


			//httpServletResponse.sendRedirect("http://www.baidu.com"); //跳转页面

			PageHelper.startPage(page,size);
			List<Video> videoList =videoService.findAll();


			return  JsonData.buildSuccess(videoList);
		}
        catch (Exception ex)
		{
			return  JsonData.buildError(ex.getMessage());
		}

	}
	@GetMapping("update")
    public Object update(@RequestBody Video video)
	{
		return videoService.update(video);
	}
     public  Object save(@RequestBody Video video)
	 {
	 	 return  videoService.save(video);
	 }

}
