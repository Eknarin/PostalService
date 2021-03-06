
package org.ku.PostalService.controller;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * Australian Postcode ,Location Web service
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "AustralianPostCode", targetNamespace = "http://www.webserviceX.NET", wsdlLocation = "http://www.webservicex.net/AustralianPostCode.asmx?WSDL")
public class AustralianPostCode
    extends Service
{

    private final static URL AUSTRALIANPOSTCODE_WSDL_LOCATION;
    private final static WebServiceException AUSTRALIANPOSTCODE_EXCEPTION;
    private final static QName AUSTRALIANPOSTCODE_QNAME = new QName("http://www.webserviceX.NET", "AustralianPostCode");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://www.webservicex.net/AustralianPostCode.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        AUSTRALIANPOSTCODE_WSDL_LOCATION = url;
        AUSTRALIANPOSTCODE_EXCEPTION = e;
    }

    public AustralianPostCode() {
        super(__getWsdlLocation(), AUSTRALIANPOSTCODE_QNAME);
    }

    public AustralianPostCode(WebServiceFeature... features) {
        super(__getWsdlLocation(), AUSTRALIANPOSTCODE_QNAME, features);
    }

    public AustralianPostCode(URL wsdlLocation) {
        super(wsdlLocation, AUSTRALIANPOSTCODE_QNAME);
    }

    public AustralianPostCode(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, AUSTRALIANPOSTCODE_QNAME, features);
    }

    public AustralianPostCode(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AustralianPostCode(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns AustralianPostCodeSoap
     */
    @WebEndpoint(name = "AustralianPostCodeSoap")
    public AustralianPostCodeSoap getAustralianPostCodeSoap() {
        return super.getPort(new QName("http://www.webserviceX.NET", "AustralianPostCodeSoap"), AustralianPostCodeSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AustralianPostCodeSoap
     */
    @WebEndpoint(name = "AustralianPostCodeSoap")
    public AustralianPostCodeSoap getAustralianPostCodeSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.webserviceX.NET", "AustralianPostCodeSoap"), AustralianPostCodeSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (AUSTRALIANPOSTCODE_EXCEPTION!= null) {
            throw AUSTRALIANPOSTCODE_EXCEPTION;
        }
        return AUSTRALIANPOSTCODE_WSDL_LOCATION;
    }

}
