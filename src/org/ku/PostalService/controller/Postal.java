package org.ku.PostalService.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.ku.PostalService.model.NewDataSet;
import org.ku.PostalService.model.Table;


/**
 * controller class
 * @author Eknarin 5510546239
 *
 */
public class Postal {
	/*
	 * find all postal from given district
	 * @param district
	 * @return list of postal code and district name
	 */
	public Table[] findPostal(String district ) {
		AustralianPostCode factory = new AustralianPostCode();
		AustralianPostCodeSoap ausSoap = factory.getAustralianPostCodeSoap();
		
		String txt = ausSoap.getAustralianPostCodeByLocation(district);
		Table[] result = null;
		try {
			JAXBContext ctx = JAXBContext.newInstance(NewDataSet.class);
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			InputStream is = new ByteArrayInputStream(txt.getBytes());
			NewDataSet data = (NewDataSet) unmarshaller.unmarshal(is);

			List<Table> tables = data.getTables();			
			result = new Table [data.getTables().size()];
			tables.toArray(result);	
			
		} catch (JAXBException x) {
			x.printStackTrace();
		}
		return result;
	}
}
