package admin;

public class GetLogin {
	private String uname,pword,type,add_dt;

	public GetLogin(String uname, String pword, String type, String add_dt) {
		super();
		this.uname = uname;
		this.pword = pword;
		this.type = type;
		this.add_dt = add_dt;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAdd_dt() {
		return add_dt;
	}

	public void setAdd_dt(String add_dt) {
		this.add_dt = add_dt;
	}
	
}
