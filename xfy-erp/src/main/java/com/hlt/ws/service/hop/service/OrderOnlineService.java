package com.hlt.ws.service.hop.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-11-18T10:33:55.749+08:00
 * Generated source version: 3.0.4
 * 
 */
@WebService(targetNamespace = "http://service.hop.service.ws.hlt.com/", name = "OrderOnlineService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface OrderOnlineService {

    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://service.hop.service.ws.hlt.com/", partName = "return")
    public CreateOrderResponse createOrder(
        @WebParam(partName = "userToken", name = "userToken")
        java.lang.String userToken,
        @WebParam(partName = "createOrderRequest", name = "createOrderRequest")
        CreateOrderRequest createOrderRequest
    );

    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://service.hop.service.ws.hlt.com/", partName = "return")
    public LookupOrderResponse lookupOrder(
        @WebParam(partName = "userToken", name = "userToken")
        java.lang.String userToken,
        @WebParam(partName = "lookupOrderRequest", name = "lookupOrderRequest")
        LookupOrderRequest lookupOrderRequest
    );

    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://service.hop.service.ws.hlt.com/", partName = "return")
    public PrintOrderResponse printOrder(
        @WebParam(partName = "userToken", name = "userToken")
        java.lang.String userToken,
        @WebParam(partName = "printOrderRequest", name = "printOrderRequest")
        PrintOrderRequest printOrderRequest
    );

    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://service.hop.service.ws.hlt.com/", partName = "return")
    public GetTransportWayListResponse getTransportWayList(
        @WebParam(partName = "userToken", name = "userToken")
        java.lang.String userToken
    );

    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://service.hop.service.ws.hlt.com/", partName = "return")
    public CreateOrderResponse createAndAuditOrder(
        @WebParam(partName = "userToken", name = "userToken")
        java.lang.String userToken,
        @WebParam(partName = "createOrderRequest", name = "createOrderRequest")
        CreateOrderRequest createOrderRequest
    );

    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://service.hop.service.ws.hlt.com/", partName = "return")
    public DeleteOrderResponse deleteOrder(
        @WebParam(partName = "userToken", name = "userToken")
        java.lang.String userToken,
        @WebParam(partName = "orderId", name = "orderId")
        long orderId
    );

    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://service.hop.service.ws.hlt.com/", partName = "return")
    public CalculateChargeResponse calculateCharge(
        @WebParam(partName = "userToken", name = "userToken")
        java.lang.String userToken,
        @WebParam(partName = "calculateChargeRequest", name = "calculateChargeRequest")
        CalculateChargeRequest calculateChargeRequest
    );

    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://service.hop.service.ws.hlt.com/", partName = "return")
    public AuditOrderResponse auditOrder(
        @WebParam(partName = "userToken", name = "userToken")
        java.lang.String userToken,
        @WebParam(partName = "orderId", name = "orderId")
        long orderId
    );
}
