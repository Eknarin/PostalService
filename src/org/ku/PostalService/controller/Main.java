package org.ku.PostalService.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.ku.PostalService.model.NewDataSet;
import org.ku.PostalService.view.PostalUI;



/**
 * main class
 * run the GUI
 * @author Eknarin 5510546239
 *
 */
public class Main {
	
	public static void main(String[] args) {				
		PostalUI postalUI = new PostalUI();
		postalUI.run();
	}
	
}
