
package controller;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "AustralianPostCodeSoap", targetNamespace = "http://www.webserviceX.NET")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AustralianPostCodeSoap {


    /**
     * Get Australian Postcode by partial / full Location Name
     * 
     * @param location
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetAustralianPostCodeByLocation", action = "http://www.webserviceX.NET/GetAustralianPostCodeByLocation")
    @WebResult(name = "GetAustralianPostCodeByLocationResult", targetNamespace = "http://www.webserviceX.NET")
    @RequestWrapper(localName = "GetAustralianPostCodeByLocation", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetAustralianPostCodeByLocation")
    @ResponseWrapper(localName = "GetAustralianPostCodeByLocationResponse", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetAustralianPostCodeByLocationResponse")
    public String getAustralianPostCodeByLocation(
        @WebParam(name = "Location", targetNamespace = "http://www.webserviceX.NET")
        String location);

    /**
     * Get Australian Location by partial / full Postcode
     * 
     * @param postCode
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetAustralianLocationByPostCode", action = "http://www.webserviceX.NET/GetAustralianLocationByPostCode")
    @WebResult(name = "GetAustralianLocationByPostCodeResult", targetNamespace = "http://www.webserviceX.NET")
    @RequestWrapper(localName = "GetAustralianLocationByPostCode", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetAustralianLocationByPostCode")
    @ResponseWrapper(localName = "GetAustralianLocationByPostCodeResponse", targetNamespace = "http://www.webserviceX.NET", className = "net.webservicex.GetAustralianLocationByPostCodeResponse")
    public String getAustralianLocationByPostCode(
        @WebParam(name = "PostCode", targetNamespace = "http://www.webserviceX.NET")
        String postCode);

}