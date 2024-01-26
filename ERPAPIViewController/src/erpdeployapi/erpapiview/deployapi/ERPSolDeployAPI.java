package erpdeployapi.erpapiview.deployapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import oracle.adf.share.ADFContext;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.client.Configuration;

@Path("deployapi")
public class ERPSolDeployAPI {
    public ERPSolDeployAPI() {
        super();
    }
    
    private final String DEPLOYAPIACCESSKEY = "tH$@cCessK$Y";


    @GET
    @Produces("application/json")
    @Path("/getDeployCustomer")
    public String getDeployCustomer(@QueryParam("pApiAuthKey") String pApiAuthKey) {
                   System.out.println("hello");
                   if (pApiAuthKey==null || !(pApiAuthKey.equals(DEPLOYAPIACCESSKEY))) {
           return "{\n \"ReturnCode\":\"00\",\"CustomerInfoResult\":[" + "\"Not Authorized\"" + "]\n}";
       }
                             ADFContext oldContext = ADFContext.initADFContext(null, null, null, null);
                             String countryinfo = null;
                             try {
                                 // System.out.println("this is GhsStGenCustomerVORO");
                                 String amDef = "erpdeployapi.erpapimodel.erpapiam.ERPAPIAppModule";
                                 String config = "ERPAPIAppModuleLocal";
                                 ApplicationModule am = Configuration.createRootApplicationModule(amDef, config);
                                 ViewObject vo = am.findViewObject("VwCustomerApiRO");

                                 while (vo.hasNext()) {
                                     Row r = vo.next();
                                     if (countryinfo == null) {
                                         countryinfo =
                                             "{\"CustomerName\":\"" + r.getAttribute("CustomerName") + 
                                             "\",\"Customerid\":\"" + r.getAttribute("Customerid") +
                                             "\",\"AccountOpeningDate\":\"" + r.getAttribute("AcOpeningDate") + "\"" +
                                             ",\"Location\":\"" + r.getAttribute("Locationid") + "\"" +
                                             ",\"Address\":\"" +r.getAttribute("Address") + "\"" +
                                         ",\"SalespersonName\":\"" +r.getAttribute("Name") + "\"" +
                                         ",\"TerritoryName\":\"" +r.getAttribute("TerritoryName") + "\"" +
                                         ",\"SalesterritoryId\":\"" +r.getAttribute("Salesterritoryid") + "\"" +
                                         ",\"SalespersonId\":\"" +r.getAttribute("Salespersonid") + "\"" +    
                                         ",\"DivisionId\":\"" +r.getAttribute("Divid") + "\"" +    
                                         ",\"DivisionName\":\"" +r.getAttribute("DivisionName") + "\"" +
                                         ",\"DefaultPerson\":\"" +r.getAttribute("DefaultPerson") + "\"" +
                                          ",\"CnicNo\":\"" + r.getAttribute("CnicNo") +
                                             "\"}";
                                         ////  System.out.println(countryinfo);
                                     } else {
                                         countryinfo +=
                                         "\n , {\"CustomerName\":\"" + r.getAttribute("CustomerName") + 
                                         "\",\"Customerid\":\"" + r.getAttribute("Customerid") +
                                         "\",\"AccountOpeningDate\":\"" + r.getAttribute("AcOpeningDate") + "\"" +
                                         ",\"Location\":\"" + r.getAttribute("Locationid") + "\"" +
                                         ",\"Address\":\"" +r.getAttribute("Address") + "\"" +
                                         ",\"SalespersonName\":\"" +r.getAttribute("Name") +"\"" +
                                         ",\"TerritoryName\":\"" +r.getAttribute("TerritoryName") +"\"" +
                                         ",\"SalesterritoryId\":\"" +r.getAttribute("Salesterritoryid") +"\"" +
                                         ",\"SalespersonId\":\"" +r.getAttribute("Salespersonid") +"\"" +
                                         ",\"DivisionId\":\"" +r.getAttribute("Divid") +"\"" +
                                         ",\"DivisionName\":\"" +r.getAttribute("DivisionName") +"\"" +
                                         ",\"DefaultPerson\":\"" +r.getAttribute("DefaultPerson") +"\"" +
                                         ",\"CnicNo\":\"" + r.getAttribute("CnicNo") +
                                         "\"}";
                                         
                                     }
                                 }
                                 // Work with your appmodule and view object here
                                 Configuration.releaseRootApplicationModule(am, true);
                                 System.out.println("this is wscalling-end");
                             } finally {
                                 ADFContext.resetADFContext(oldContext);
                             }

                             String result = "{\n \"ReturnCode\":\"00\",\"CustomerInfoResult\":[" + countryinfo + " \n]\n}";
                             return result;
               }

    @GET
    @Produces("application/json")
    @Path("/getDeploySale")
    public String getDeploySale(@QueryParam("pApiAuthKey") String pApiAuthKey,
                                @QueryParam("pStartDate") String pStartDate, @QueryParam("pEndDate") String pEndDate) {
                       if (pApiAuthKey==null || !(pApiAuthKey.equals(DEPLOYAPIACCESSKEY))) {
                           return "{\n \"ReturnCode\":\"00\",\"SaleInfoResult\":[" + "\"Not Authorized\"" + "]\n}";
                       }
                       System.out.println("hello");
                                 ADFContext oldContext = ADFContext.initADFContext(null, null, null, null);
                                 String countryinfo = null;
                                 try {
                                     // System.out.println("this is GhsStGenCustomerVORO");
                                     String amDef = "erpdeployapi.erpapimodel.erpapiam.ERPAPIAppModule";
                                     String config = "ERPAPIAppModuleLocal";
                                     ApplicationModule am = Configuration.createRootApplicationModule(amDef, config);
                                     ViewObject vo = am.findViewObject("VwSaleApiRO");
                                     vo.setWhereClause("Sale_Date between TO_DATE('"+pStartDate+"','yyyy-mm-dd') AND TO_DATE('"+pEndDate+"','yyyy-mm-dd') AND TO_DATE('"+pEndDate+"','yyyy-mm-dd') - TO_DATE('"+pStartDate+"','yyyy-mm-dd')<=1");
                                     System.out.println(vo.getWhereClause());
                                     vo.executeQuery();
                                     vo.setRangeSize(-1);
                                     while (vo.hasNext()) {
                                         Row r = vo.next();
                                         if (countryinfo == null) {
                                             countryinfo =
                                                "{\"Salesorderid\":\"" + r.getAttribute("Salesorderid") + "\"" +
                                                ",\"SaleDate\":\"" + r.getAttribute("SaleDate") +"\"" +
                                                ",\"Storeid\":\"" + r.getAttribute("Storeid") + "\"" +
                                                ",\"StoreName\":\"" + r.getAttribute("StoreName") + "\"" +
                                                ",\"DealerId\":\"" +r.getAttribute("DealerId") + "\"" + 
                                                ",\"DealerName\":\"" +r.getAttribute("DealerName") + "\"" + 
                                                ",\"Salespersonid\":\"" +r.getAttribute("Salespersonid") + "\"" + 
                                                ",\"SalesPerson\":\"" +r.getAttribute("SalesPerson") + "\"" + 
                                                ",\"Location\":\"" +r.getAttribute("Location") + "\"" +     
                                                ",\"Productid\":\"" +r.getAttribute("Productid") + "\"" +     
                                                ",\"ModelNo\":\"" +r.getAttribute("ModelNo") + "\"" + 
                                                ",\"Quantity\":" +r.getAttribute("Quantity")  + 
                                                ",\"ImeiNo\":\"" + r.getAttribute("ImeiNo") + "\"}";
                                             ////  System.out.println(countryinfo);
                                         } else {
                                             countryinfo +=
                                             "\n , {\"Salesorderid\":\"" + r.getAttribute("Salesorderid") + "\"" +
                                             ",\"SaleDate\":\"" + r.getAttribute("SaleDate") +"\"" +
                                             ",\"Storeid\":\"" + r.getAttribute("Storeid") + "\"" +
                                             ",\"StoreName\":\"" + r.getAttribute("StoreName") + "\"" +
                                             ",\"DealerId\":\"" +r.getAttribute("DealerId") + "\"" + 
                                             ",\"DealerName\":\"" +r.getAttribute("DealerName") + "\"" + 
                                             ",\"Salespersonid\":\"" +r.getAttribute("Salespersonid") + "\"" + 
                                             ",\"SalesPerson\":\"" +r.getAttribute("SalesPerson") + "\"" + 
                                             ",\"Location\":\"" +r.getAttribute("Location") + "\"" +     
                                             ",\"Productid\":\"" +r.getAttribute("Productid") + "\"" +     
                                             ",\"ModelNo\":\"" +r.getAttribute("ModelNo") + "\"" + 
                                             ",\"Quantity\":" +r.getAttribute("Quantity")  + 
                                             ",\"ImeiNo\":\"" + r.getAttribute("ImeiNo")+"\"}";
                                             
                                         }
                                         
                                     }
                                     // Work with your appmodule and view object here
                                     Configuration.releaseRootApplicationModule(am, true);
                                     System.out.println("this is wscalling-end");
                                 } 
                                 catch(Exception ex){
                                     return "{\n \"ReturnCode\":\"00\",\"CustomerInfoResult\":[" + "\"InvalidDateParameters\"" + "]\n}";
                                 } finally {
                                     ADFContext.resetADFContext(oldContext);
                                 }

                                 String result = "{\n \"ReturnCode\":\"00\",\"SaleInfoResult\":[" + countryinfo + " \n]\n}";
                                 return result;
                   }
    
        
    }

