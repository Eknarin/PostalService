package org.ku.PostalService.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data class
 * @author Eknarin 5510546239
 *
 */
@XmlRootElement(name="NewDataSet")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewDataSet {
	
	/*
	 * list of Tables
	 */
	@XmlElement(name="Table")
	private List<Table> tables;
	
	
	/*
	 * constructor
	 */
	public NewDataSet() {
		
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
	
	
	
}