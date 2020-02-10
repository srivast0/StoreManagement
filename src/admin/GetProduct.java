package admin;

public class GetProduct {
	private String pname,add_dt;
	private int pqty;
	private double pprice;
	public GetProduct(String pname, String add_dt, int pqty, double pprice) {
		super();
		this.pname = pname;
		this.add_dt = add_dt;
		this.pqty = pqty;
		this.pprice = pprice;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getAdd_dt() {
		return add_dt;
	}
	public void setAdd_dt(String add_dt) {
		this.add_dt = add_dt;
	}
	public int getPqty() {
		return pqty;
	}
	public void setPqty(int pqty) {
		this.pqty = pqty;
	}
	public double getPprice() {
		return pprice;
	}
	public void setPprice(double pprice) {
		this.pprice = pprice;
	}
	
}
