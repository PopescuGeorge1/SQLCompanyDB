package Home;

public class person {

	private String id, fname, lname, birthday, salary, user, pass, branch, supervisor;
	
	
	public person(String id, String fname, String lname, String birthday
			, String salary, String user, String pass, String branch, String supervisor) {
		this.id = id;
		this.fname=fname;
		this.lname=lname;
		this.birthday=birthday;
		this.salary=salary;
		this.user = user;
		this.pass = pass;
		this.branch = branch;
		this.supervisor = supervisor;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getBranch() {
		return branch;
	}


	public void setBranch(String branch) {
		this.branch = branch;
	}


	public String getSupervisor() {
		return supervisor;
	}


	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getSalary() {
		return salary;
	}


	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	 
}
