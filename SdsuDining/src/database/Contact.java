package database;

public class Contact {

	int id;
	String name;
	String mobile;
	String home;
	String addrs;
	
	public Contact(){
		
	}
	
	public Contact(int id, String name, String mobile, String home, String addrs){
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.home = home;
		this.addrs = addrs;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getHome() {
		return home;
	}

	public String getAddrs() {
		return addrs;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public void setAddrs(String addrs) {
		this.addrs = addrs;
	}
	
	

}
