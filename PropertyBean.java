package FindProperty1;

import java.time.LocalDate;

public class PropertyBean {

	String pid;
	String buyer;
	String seller;
	String tamount;
	String adv;
	String bal;
	LocalDate doreg;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getTamount() {
		return tamount;
	}
	public void setTamount(String tamount) {
		this.tamount = tamount;
	}
	public String getAdv() {
		return adv;
	}
	public void setAdv(String adv) {
		this.adv = adv;
	}
	public String getBal() {
		return bal;
	}
	public void setBal(String bal) {
		this.bal = bal;
	}
	public LocalDate getDoreg() {
		return doreg;
	}
	public void setDoreg(LocalDate doreg) {
		this.doreg = doreg;
	}
	public PropertyBean(String pid, String buyer, String seller, String tamount, String adv, String bal,
			LocalDate doreg) {
		super();
		this.pid = pid;
		this.buyer = buyer;
		this.seller = seller;
		this.tamount = tamount;
		this.adv = adv;
		this.bal = bal;
		this.doreg = doreg;
	}
		
}
