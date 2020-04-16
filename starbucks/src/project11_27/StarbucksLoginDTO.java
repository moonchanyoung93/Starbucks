package project11_27;

public class StarbucksLoginDTO {

	private String name, userid, password, phone, location, sex;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "StarbucksLoginDTO [name=" + name + ", userid=" + userid + ", password=" + password + ", phone=" + phone
				+ ", location=" + location + ", sex=" + sex + ", age=" + age + "]";
	}
	
	public StarbucksLoginDTO(String name, String userid, String password, String phone, String location, String sex,
			int age) {
		this.name = name;
		this.userid = userid;
		this.password = password;
		this.phone = phone;
		this.location = location;
		this.sex = sex;
		this.age = age;
	}
	
	public StarbucksLoginDTO() {
	}
	public StarbucksLoginDTO(String userid, String password, String phone, String sex) {
		this.userid = userid;
		this.password = password;
		this.phone = phone;
		this.sex = sex;
	}
	public StarbucksLoginDTO(String userid, String phone, String sex) {
		super();
		this.userid = userid;
		this.phone = phone;
		this.sex = sex;
	}
	
	
	
	}
