package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Table class
 * @author macbookair
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Table {
	
	@XmlElement(name="Location")
	private String location;
	
	@XmlElement(name="PostCode")
	private String postalCode;
	
	/*
	 * constructor
	 */
	public Table() {
		
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return getLocation() + " : " +getPostalCode();
	}
	
}
