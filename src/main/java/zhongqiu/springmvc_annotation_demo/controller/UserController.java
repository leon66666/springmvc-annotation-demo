package zhongqiu.springmvc_annotation_demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import zhongqiu.springmvc_annotation_demo.domain.User;
import zhongqiu.springmvc_annotation_demo.domain.UserCustom;

@Controller
@RequestMapping("/user")
public class UserController {

	//http://localhost:9999/user/hello.do
	@RequestMapping(value = "/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String hello() {

		return "index";
	}

	// 跳转到add页面
	@RequestMapping("toAdd")
	public String toAdd() {

		return "add";
	}

	// 接受int类型参数
	//http://localhost:9999/user/recieveInt.do?id=3
	@RequestMapping("recieveInt")
	public String recieveInt(Integer id) {

		System.out.println(id);

		return "success";
	}

	// @RequestParam注解
	// A)常用来处理简单类型的绑定，通过Request.getParameter() 获取的String可直接转换为简单类型的情况
	// （ String--> 简单类型的转换操作由ConversionService配置的转换器来完成）；
	// 因为使用request.getParameter()方式获取参数，所以可以处理get 方式中queryString的值，也可以处理post方式中
	// body data的值；
	// B）用来处理Content-Type: 为
	// application/x-www-form-urlencoded编码的内容，提交方式GET、POST；
	// C) 该注解有两个属性： value、required； value用来指定要传入值的id名称，required用来指示参数是否必须绑定；
	@RequestMapping("recieveIntRequestParam")
	public String recieveIntRequestParam(@RequestParam(value = "ss", required = true) Integer id) {

		System.out.println(id);

		return "success";
	}

	//http://localhost:9999/user/recieveStr.do?username=%E6%B0%B4%E7%94%B5%E8%B4%B9
	// 接受字符类型参数
	@RequestMapping("recieveStr")
	public String recieveStr(String username) {

		System.out.println(username);
		return "success";
	}

	// 接受数组类型参数
	@RequestMapping("recieveArray")
	public String recieveArray(Integer[] ids) {

		System.out.println(ids);

		return "success";
	}

	// 接受参数封装User对象
	@RequestMapping("recieveUser")
	public String recieveUser(User user) {

		System.out.println(user);

		return "success";
	}

	// 接受包装类型参数
	@RequestMapping("recieveUserCustom")
	public String recieveUserCustom(UserCustom userCustom) {

		System.out.println(userCustom);

		return "success";
	}

	// 接受集合类型参数
	@RequestMapping("recieveList")
	public String recieveList(UserCustom userCustom) {

		System.out.println(userCustom);

		return "success";
	}

	// 接受集合类型参数
	@RequestMapping("recieveMap")
	public String recieveMap(UserCustom userCustom) {

		System.out.println(userCustom);

		return "success";
	}

	// 页面回显
	@RequestMapping("list")
	public String list(Model model) {
		// model 相当于application域对象

		List<User> userList = new ArrayList<User>();

		User user1 = new User();
		user1.setId(1);
		user1.setSex("男");
		user1.setUsername("张山峰");
		user1.setAddress("武当山");
		user1.setBirthday(new Date());

		User user2 = new User();
		user2.setId(2);
		user2.setSex("男2");
		user2.setUsername("张山峰222");
		user2.setAddress("武当山222");
		user2.setBirthday(new Date());

		User user3 = new User();
		user3.setId(3);
		user3.setSex("男3");
		user3.setUsername("张山峰333");
		user3.setAddress("武当山333");
		user3.setBirthday(new Date());

		userList.add(user1);
		userList.add(user2);
		userList.add(user3);

		model.addAttribute("userList", userList);

		return "list";
	}

	// @PathVariable注解
	// 当使用@RequestMapping URI template 样式映射时， 即 someUrl/{paramId},
	// 这时的paramId可通过 @Pathvariable注解绑定它传过来的值到方法的参数上。
	@RequestMapping("updateByID/{id}")
	public String updateByID(@PathVariable Integer id, Model model) {
		User user1 = new User();
		user1.setId(id);
		user1.setSex("男");
		user1.setUsername("张山峰");
		user1.setAddress("武当山");
		user1.setBirthday(new Date());

		model.addAttribute("user", user1);

		return "edit";
	}

	// 测试转发
	@RequestMapping("forward")
	public String forward() {
		// 跨类转发
		return "forward:/items/list.do";

		// 本类转发
		// return "forward:list.do";
	}

	// 测试重定向
	@RequestMapping("redirect")
	public String redirect() {
		// 跨类重定向
		return "redirect:/items/list.do";

		// 本类中重定向
		// return "redirect:list.do";
	}

	// 跳转到requestJson页面
	@RequestMapping("toJson")
	public String toJson() {

		return "requestJson";
	}

	// 接受json格式数据，使用RequestBody把json格式数据封装到user对象中
	// 返回user对象，使用ResponseBody把user对象转换成json对象
	@RequestMapping("requestJson")
	public @ResponseBody User requestJson(@RequestBody User user) {
		System.out.println(user);
		return user;
	}

	// 返回user对象，使用ResponseBody把user对象转换成json对象
    //和user类中的@XmlRootElement冲突，导致返回user，序列化json失败
	@RequestMapping("requestPo")
	public @ResponseBody User requestPo(User user) {
		System.out.println(user.getUsername());
		return user;
	}

	//
	//多视图
	@RequestMapping("multiView")
	public User multiView() {
		User user=new User();
		user.setAddress("武当山");
		user.setUsername("张三丰");
		return user;
	}
}
