package zhongqiu.springmvc_annotation_demo.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
//多视图返回，对xml视图的支持，必须有这个注解
//@XmlRootElement
public class User {
	
	private Integer id;
	private String username;
	private Date birthday;
	private String sex;
	private String address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
