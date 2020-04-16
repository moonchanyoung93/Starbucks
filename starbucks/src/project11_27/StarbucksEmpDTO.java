package project11_27;

public class StarbucksEmpDTO {
	private int eno, sal;
	private String position, ename, phone, location, sex;
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
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
	@Override
	public String toString() {
		return "StarbucksEmpDTO [eno=" + eno + ", sal=" + sal + ", position=" + position + ", ename=" + ename
				+ ", phone=" + phone + ", location=" + location + ", sex=" + sex + "]";
	}
	public StarbucksEmpDTO(int eno, int sal, String position, String ename, String phone, String location, String sex) {
		this.eno = eno;
		this.sal = sal;
		this.position = position;
		this.ename = ename;
		this.phone = phone;
		this.location = location;
		this.sex=sex;
	}
	
	
	
	public StarbucksEmpDTO(int eno, int sal, String position, String ename, String phone, String location) {
		super();
		this.eno = eno;
		this.sal = sal;
		this.position = position;
		this.ename = ename;
		this.phone = phone;
		this.location = location;
	}
	public StarbucksEmpDTO() {
	}
	

	
	
}
