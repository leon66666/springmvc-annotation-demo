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

	// ��ת��addҳ��
	@RequestMapping("toAdd")
	public String toAdd() {

		return "add";
	}

	// ����int���Ͳ���
	//http://localhost:9999/user/recieveInt.do?id=3
	@RequestMapping("recieveInt")
	public String recieveInt(Integer id) {

		System.out.println(id);

		return "success";
	}

	// @RequestParamע��
	// A)��������������͵İ󶨣�ͨ��Request.getParameter() ��ȡ��String��ֱ��ת��Ϊ�����͵����
	// �� String--> �����͵�ת��������ConversionService���õ�ת��������ɣ���
	// ��Ϊʹ��request.getParameter()��ʽ��ȡ���������Կ��Դ���get ��ʽ��queryString��ֵ��Ҳ���Դ���post��ʽ��
	// body data��ֵ��
	// B����������Content-Type: Ϊ
	// application/x-www-form-urlencoded��������ݣ��ύ��ʽGET��POST��
	// C) ��ע�����������ԣ� value��required�� value����ָ��Ҫ����ֵ��id���ƣ�required����ָʾ�����Ƿ����󶨣�
	@RequestMapping("recieveIntRequestParam")
	public String recieveIntRequestParam(@RequestParam(value = "ss", required = true) Integer id) {

		System.out.println(id);

		return "success";
	}

	//http://localhost:9999/user/recieveStr.do?username=%E6%B0%B4%E7%94%B5%E8%B4%B9
	// �����ַ����Ͳ���
	@RequestMapping("recieveStr")
	public String recieveStr(String username) {

		System.out.println(username);
		return "success";
	}

	// �����������Ͳ���
	@RequestMapping("recieveArray")
	public String recieveArray(Integer[] ids) {

		System.out.println(ids);

		return "success";
	}

	// ���ܲ�����װUser����
	@RequestMapping("recieveUser")
	public String recieveUser(User user) {

		System.out.println(user);

		return "success";
	}

	// ���ܰ�װ���Ͳ���
	@RequestMapping("recieveUserCustom")
	public String recieveUserCustom(UserCustom userCustom) {

		System.out.println(userCustom);

		return "success";
	}

	// ���ܼ������Ͳ���
	@RequestMapping("recieveList")
	public String recieveList(UserCustom userCustom) {

		System.out.println(userCustom);

		return "success";
	}

	// ���ܼ������Ͳ���
	@RequestMapping("recieveMap")
	public String recieveMap(UserCustom userCustom) {

		System.out.println(userCustom);

		return "success";
	}

	// ҳ�����
	@RequestMapping("list")
	public String list(Model model) {
		// model �൱��application�����

		List<User> userList = new ArrayList<User>();

		User user1 = new User();
		user1.setId(1);
		user1.setSex("��");
		user1.setUsername("��ɽ��");
		user1.setAddress("�䵱ɽ");
		user1.setBirthday(new Date());

		User user2 = new User();
		user2.setId(2);
		user2.setSex("��2");
		user2.setUsername("��ɽ��222");
		user2.setAddress("�䵱ɽ222");
		user2.setBirthday(new Date());

		User user3 = new User();
		user3.setId(3);
		user3.setSex("��3");
		user3.setUsername("��ɽ��333");
		user3.setAddress("�䵱ɽ333");
		user3.setBirthday(new Date());

		userList.add(user1);
		userList.add(user2);
		userList.add(user3);

		model.addAttribute("userList", userList);

		return "list";
	}

	// @PathVariableע��
	// ��ʹ��@RequestMapping URI template ��ʽӳ��ʱ�� �� someUrl/{paramId},
	// ��ʱ��paramId��ͨ�� @Pathvariableע�������������ֵ�������Ĳ����ϡ�
	@RequestMapping("updateByID/{id}")
	public String updateByID(@PathVariable Integer id, Model model) {
		User user1 = new User();
		user1.setId(id);
		user1.setSex("��");
		user1.setUsername("��ɽ��");
		user1.setAddress("�䵱ɽ");
		user1.setBirthday(new Date());

		model.addAttribute("user", user1);

		return "edit";
	}

	// ����ת��
	@RequestMapping("forward")
	public String forward() {
		// ����ת��
		return "forward:/items/list.do";

		// ����ת��
		// return "forward:list.do";
	}

	// �����ض���
	@RequestMapping("redirect")
	public String redirect() {
		// �����ض���
		return "redirect:/items/list.do";

		// �������ض���
		// return "redirect:list.do";
	}

	// ��ת��requestJsonҳ��
	@RequestMapping("toJson")
	public String toJson() {

		return "requestJson";
	}

	// ����json��ʽ���ݣ�ʹ��RequestBody��json��ʽ���ݷ�װ��user������
	// ����user����ʹ��ResponseBody��user����ת����json����
	@RequestMapping("requestJson")
	public @ResponseBody User requestJson(@RequestBody User user) {
		System.out.println(user);
		return user;
	}

	// ����user����ʹ��ResponseBody��user����ת����json����
    //��user���е�@XmlRootElement��ͻ�����·���user�����л�jsonʧ��
	@RequestMapping("requestPo")
	public @ResponseBody User requestPo(User user) {
		System.out.println(user.getUsername());
		return user;
	}

	//
	//����ͼ
	@RequestMapping("multiView")
	public User multiView() {
		User user=new User();
		user.setAddress("�䵱ɽ");
		user.setUsername("������");
		return user;
	}
}
