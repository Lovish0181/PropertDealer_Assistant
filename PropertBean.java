package FindProperty;

import java.time.LocalDate;


public class PropertBean {
	String contact;
	String ptype;
    String size;
	String width;
	String depth;
	String lside;
	String rside;
	String location;
	String city;
	String amount;
	String direction;
	LocalDate doa;
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getLside() {
		return lside;
	}
	public void setLside(String lside) {
		this.lside = lside;
	}
	public String getRside() {
		return rside;
	}
	public void setRside(String rside) {
		this.rside = rside;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public LocalDate getDoa() {
		return doa;
	}
	public void setDoa(LocalDate doa) {
		this.doa = doa;
	}
	public PropertBean(String contact, String ptype, String size, String width, String depth, String lside,
			String rside, String location, String city, String amount, String direction, LocalDate doa) {
		super();
		this.contact = contact;
		this.ptype = ptype;
		this.size = size;
		this.width = width;
		this.depth = depth;
		this.lside = lside;
		this.rside = rside;
		this.location = location;
		this.city = city;
		this.amount = amount;
		this.direction = direction;
		this.doa = doa;
	}
	
	
}
