package zhongqiu.springmvc_annotation_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemsController {

	//���Կ���ת����
	@RequestMapping("list")
	public String list(){
		System.out.println("���ʳɹ�");
		return "success";
	}
	
}
