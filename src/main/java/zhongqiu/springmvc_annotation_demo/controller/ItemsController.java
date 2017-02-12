package zhongqiu.springmvc_annotation_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemsController {

	//测试跨类转发用
	@RequestMapping("list")
	public String list(){
		System.out.println("访问成功");
		return "success";
	}
	
}
