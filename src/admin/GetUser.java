package admin;

public class GetUser {
	private String uname,pword,fname,lname,addrs,mail,gender;
	private long mob;
	public GetUser(String uname, String pword, String fname, String lname, String addrs, String mail, String gender,
			long mob) {
		super();
		this.uname = uname;
		this.pword = pword;
		this.fname = fname;
		this.lname = lname;
		this.addrs = addrs;
		this.mail = mail;
		this.gender = gender;
		this.mob = mob;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPword() {
		return pword;
	}
	public void setPword(String pword) {
		this.pword = pword;
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
	public String getAddrs() {
		return addrs;
	}
	public void setAddrs(String addrs) {
		this.addrs = addrs;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getMob() {
		return mob;
	}
	public void setMob(long mob) {
		this.mob = mob;
	}
	

}
