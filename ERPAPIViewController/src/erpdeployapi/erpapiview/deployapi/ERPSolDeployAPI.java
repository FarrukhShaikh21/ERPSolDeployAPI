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


    @GET
    @Produces("application/json")
    @Path("/getDeployProduct")
    public String getDeployProduct(@QueryParam("pApiAuthKey") String pApiAuthKey) {
                       System.out.println("hello");
                       if (pApiAuthKey==null || !(pApiAuthKey.equals(DEPLOYAPIACCESSKEY))) {
               return "{\n \"ReturnCode\":\"00\",\"ProductInfoResult\":[" + "\"Not Authorized\"" + "]\n}";
           }
                                 ADFContext oldContext = ADFContext.initADFContext(null, null, null, null);
                                 String countryinfo = null;
                                 try {
                                     // System.out.println("this is GhsStGenCustomerVORO");
                                     String amDef = "erpdeployapi.erpapimodel.erpapiam.ERPAPIAppModule";
                                     String config = "ERPAPIAppModuleLocal";
                                     ApplicationModule am = Configuration.createRootApplicationModule(amDef, config);
                                     ViewObject vo = am.findViewObject("VwProductApiRO");

                                     while (vo.hasNext()) {
                                         Row r = vo.next();
                                         if (countryinfo == null) {
                                             countryinfo =
                                                 "{\"Productid\":\"" + r.getAttribute("Productid") + 
                                                 "\",\"ModelNo\":\"" + r.getAttribute("ModelNo") +
                                                 "\",\"Groupid\":\"" + r.getAttribute("Groupid") + "\"" +
                                                 ",\"GroupName\":\"" + r.getAttribute("GroupName") + "\"" +
                                                 ",\"Divid\":\"" +r.getAttribute("Divid") + "\"" +
                                             ",\"DivName\":\"" +r.getAttribute("DivName") +
                                                 "\"}";
                                             ////  System.out.println(countryinfo);
                                         } else {
                                             countryinfo +=
                                             "\n ,{\"Productid\":\"" + r.getAttribute("Productid") + 
                                             "\",\"ModelNo\":\"" + r.getAttribute("ModelNo") +
                                             "\",\"Groupid\":\"" + r.getAttribute("Groupid") + "\"" +
                                             ",\"GroupName\":\"" + r.getAttribute("GroupName") + "\"" +
                                             ",\"Divid\":\"" +r.getAttribute("Divid") + "\"" +
                                             ",\"DivName\":\"" +r.getAttribute("DivName") +
                                             "\"}";
                                             
                                         }
                                     }
                                     // Work with your appmodule and view object here
                                     Configuration.releaseRootApplicationModule(am, true);
                                     System.out.println("this is wscalling-end");
                                 } finally {
                                     ADFContext.resetADFContext(oldContext);
                                 }

                                 String result = "{\n \"ReturnCode\":\"00\",\"ProductInfoResult\":[" + countryinfo + " \n]\n}";
                                 return result;
                   }

    @GET
    @Produces("application/json")
    @Path("/getDeploySaleReturn")
    public String getDeploySaleReturn(@QueryParam("pApiAuthKey") String pApiAuthKey,
                                      @QueryParam("pStartDate") String pStartDate,
                                      @QueryParam("pEndDate") String pEndDate) {
                           if (pApiAuthKey==null || !(pApiAuthKey.equals(DEPLOYAPIACCESSKEY))) {
                               return "{\n \"ReturnCode\":\"00\",\"SaleReturnInfoResult\":[" + "\"Not Authorized\"" + "]\n}";
                           }
                           System.out.println("hello");
                                     ADFContext oldContext = ADFContext.initADFContext(null, null, null, null);
                                     String countryinfo = null;
                                     try {
                                         // System.out.println("this is GhsStGenCustomerVORO");
                                         String amDef = "erpdeployapi.erpapimodel.erpapiam.ERPAPIAppModule";
                                         String config = "ERPAPIAppModuleLocal";
                                         ApplicationModule am = Configuration.createRootApplicationModule(amDef, config);
                                         ViewObject vo = am.findViewObject("VwSaleReturnApiRO");
                                         vo.setWhereClause("SALE_RET_DATE between TO_DATE('"+pStartDate+"','yyyy-mm-dd') AND TO_DATE('"+pEndDate+"','yyyy-mm-dd') AND TO_DATE('"+pEndDate+"','yyyy-mm-dd') - TO_DATE('"+pStartDate+"','yyyy-mm-dd')<=1");
                                         System.out.println(vo.getWhereClause());
                                         vo.executeQuery();
                                         vo.setRangeSize(-1);
                                         while (vo.hasNext()) {
                                             Row r = vo.next();
                                             if (countryinfo == null) {
                                                 countryinfo =
                                                    "{\"Salesretid\":\"" + r.getAttribute("Salesretid") + "\"" +
                                                    ",\"SaleRetDate\":\"" + r.getAttribute("SaleRetDate") +"\"" +
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
                                                 "\n , {\"Salesretid\":\"" + r.getAttribute("Salesretid") + "\"" +
                                                 ",\"SaleRetDate\":\"" + r.getAttribute("SaleRetDate") +"\"" +
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
                                         return "{\n \"ReturnCode\":\"00\",\"SaleReturnInfoResult\":[" + "\"InvalidDateParameters\"" + "]\n}";
                                     } finally {
                                         ADFContext.resetADFContext(oldContext);
                                     }

                                     String result = "{\n \"ReturnCode\":\"00\",\"SaleReturnInfoResult\":[" + countryinfo + " \n]\n}";
                                     return result;
                       } 
        @GET
        @Produces("application/json")
        @Path("/getLatestSale")
        public String getLatestSale(@QueryParam("pApiAuthKey")String pApiAuthKey,
                                    @QueryParam("pCustomerId")String pCustomerId,
                                    @QueryParam("pProductId") String pProductId,
                                    @QueryParam("pDivId") String pDivId,
                                    @QueryParam("pGroupId") String pGroupId, 
                                    @QueryParam("pStartDate") String pStartDate,
                                    @QueryParam("pEndDate") String pEndDate) {
                           System.out.println("hello");
                           if (pApiAuthKey==null || !(pApiAuthKey.equals(DEPLOYAPIACCESSKEY))) {
                   return "{\n \"ReturnCode\":\"00\",\"LatestSaleInfoResult\":[" + "\"Not Authorized\"" + "]\n}";
               }
                                     ADFContext oldContext = ADFContext.initADFContext(null, null, null, null);
                                     String countryinfo = null;
                                     try {
                                         // System.out.println("this is GhsStGenCustomerVORO");
                                         String amDef = "erpdeployapi.erpapimodel.erpapiam.ERPAPIAppModule";
                                         String config = "ERPAPIAppModuleLocal";
                                         ApplicationModule am = Configuration.createRootApplicationModule(amDef, config);
//                                         ViewObject vo = am.findViewObject("VwLatestSaleApiRO");
                                         ViewObject vo = am.findViewObject("VwLatestSaleApiQVO");
                                         if (vo!=null) {
                                        vo.remove();
                                   }
                                         String strPlsql="WITH LatestSales AS (\n" + 
                                         "            -- The same CTE as before\n" + 
                                         "            SELECT soimei.imei_no, MAX(so.confirm_date) AS latest_sale_date\n" + 
                                         "            FROM so_sales_order so, so_sales_order_lines sol, so_sales_order_imei soimei\n" + 
                                         "            WHERE so.salesorderid = sol.salesorderid\n" + 
                                         "              AND sol.salesorderid = soimei.salesorderid AND sol.lineno = soimei.line_no\n" + 
                                         "                 GROUP BY soimei.imei_no\n" + 
                                         "        )\n" + 
                                         "\n" + 
                                         "SELECT t.model_no Model_No,\n" + 
                                         "       count(soimei.imei_no) Active_Qty,\n" + 
                                         "       so.customerid,\n" + 
                                         "       CUSTNAME(so.customerid) Cname,\n" + 
                                         "       soimei.imei_no IMEI_NO,\n" + 
                                         "       sol.Productid\n" + 
                                         "  FROM so_sales_order       so,\n" + 
                                         "       so_sales_order_lines sol,\n" + 
                                         "       so_sales_order_imei  soimei,\n" + 
                                         "       LatestSales          LS,\n" + 
                                         "       in_items             t\n" + 
                                         " WHERE so.salesorderid = sol.salesorderid\n" + 
                                         "   AND sol.salesorderid = soimei.salesorderid\n" + 
                                         "   AND sol.lineno = soimei.line_no\n" + 
                                         "   AND soimei.imei_no = LS.imei_no\n" + 
                                         "   AND so.confirm_date = LS.latest_sale_date\n" + 
                                         "   and sol.productid = t.productid\n" + 
                                         "   AND NOT EXISTS\n" + 
                                         " (SELECT 1\n" + 
                                         "          FROM so_sales_return sr, so_sales_return_lines srl, srimei srim\n" + 
                                         "         WHERE sr.salesretid = srl.salesretid\n" + 
                                         "           AND srl.srdetlid = srim.srdetlid\n" + 
                                         "           AND srim.imei_no = soimei.imei_no\n" + 
                                         "           AND sr.return_date > so.confirm_date)\n" + 
                                         " and not EXISTS ( select 1 from active_imei m where m.imei1= soimei.imei_no)  " + 
                                         " and not EXISTS ( select 1 from active_imei m where m.imei2= soimei.imei_no)  " + 
                                         " AND t.SIGroupID  in ('013','011') "  +             
                                        "AND     sol.ProductID = NVL('"+(pProductId==null?"":pProductId)+"', sol.ProductID)\n" + 
                                         "    AND so.CustomerID = NVL('"+(pCustomerId==null?"":pCustomerId)+"',so.CustomerID)\n" + 
                                         " AND    so.confirm_date  between TO_DATE('"+pStartDate+"','yyyy-mm-dd') AND TO_DATE('"+pEndDate+"','yyyy-mm-dd') " + 
                                         " AND     T.SIGroupID = NVL('"+(pGroupId==null?"":pGroupId)+"', T.SIGroupID)\n" + 
                                         " AND     T.DIVID = NVL('"+(pDivId==null?"":pDivId)+"', T.DIVID)\n" + 
                                         " GROUP BY  soimei.imei_no,t.model_no, so.customerid,sol.ProductID " + 
                                         " ORDER BY 1 ";
                                         vo=am.createViewObjectFromQueryStmt("VwLatestSaleApiQVO", strPlsql);
                                        // String strWhereClause="confirm_date between TO_DATE('"+pStartDate+"','yyyy-mm-dd') AND TO_DATE('"+pEndDate+"','yyyy-mm-dd') ";
                                        // strWhereClause+=" and not EXISTS ( select 1 from active_imei m where m.imei1= imei_no  and  TRUNC(m.ACTIVE_DATE_DATE) between TO_DATE('"+pStartDate+"','yyyy-mm-dd') AND TO_DATE('"+pEndDate+"','yyyy-mm-dd'))";
                                         //strWhereClause+=" and not EXISTS ( select 1 from active_imei m where m.imei2= imei_no  and  TRUNC(m.ACTIVE_DATE_DATE) between TO_DATE('"+pStartDate+"','yyyy-mm-dd') AND TO_DATE('"+pEndDate+"','yyyy-mm-dd'))";
                                         /*
                                         if (pProductId!=null) {
                                            strWhereClause += " and ProductID='" + pProductId + "'";
                                         }
                                         if(pCustomerId!=null){
                                            strWhereClause += " and CustomerID='" + pCustomerId + "'";
                                            }
                                         if(pDivId!=null){
                                            strWhereClause += " and DIVID='" + pDivId + "'";
                                            } 
                                         if(pGroupId!=null){
                                            strWhereClause += " and SIGroupID='" + pGroupId + "'";
                                            } */
//                                        System.out.println(vo.getAttributeDef(0).getName());
//                                         System.out.println(vo.getAttributeDef(1).getName());
//                                         System.out.println(vo.getAttributeDef(2).getName());
//                                         System.out.println(vo.getAttributeDef(3).getName());
//                                         System.out.println(vo.getAttributeDef(4).getName());
//                                         System.out.println(vo.getAttributeDef(5).getName());
                                        // vo.setWhereClause(strWhereClause);
                                         System.out.println(vo.getQuery());
                                         vo.executeQuery();
                                         while (vo.hasNext()) {
                                             Row r = vo.next();
                                             if (countryinfo == null) {
                                                 countryinfo =
                                                     "{\"Productid\":\"" + r.getAttribute("PRODUCTID") + 
                                                     "\",\"ModelNo\":\"" + r.getAttribute("MODEL_NO") +
                                                     "\",\"CustomerId\":\"" + r.getAttribute("CUSTOMERID") + "\"" +
                                                     ",\"CustomerName\":\"" + r.getAttribute("CNAME") + "\"" +
                                                 ",\"ImeiNo\":\"" +r.getAttribute("IMEI_NO") +
                                                     "\"}";
                                                 ////  System.out.println(countryinfo);
                                             } else {
                                                 countryinfo +=
                                                 "\n ,{\"Productid\":\"" + r.getAttribute("PRODUCTID") + 
                                                 "\",\"ModelNo\":\"" + r.getAttribute("MODEL_NO") +
                                                 "\",\"CustomerId\":\"" + r.getAttribute("CUSTOMERID") + "\"" +
                                                 ",\"CustomerName\":\"" + r.getAttribute("CNAME") + "\"" +
                                                 ",\"ImeiNo\":\"" +r.getAttribute("IMEI_NO") +
                                                 "\"}";
                                                 
                                             }
                                         }
                                         // Work with your appmodule and view object here
                                         Configuration.releaseRootApplicationModule(am, true);
//                                         System.out.println("this is wscalling-end");
                                     } finally {
                                         ADFContext.resetADFContext(oldContext);
                                     }

                                     String result = "{\n \"ReturnCode\":\"00\",\"LatestSaleInfoResult\":[" + countryinfo + " \n]\n}";
                                     return result;
                       }
    }

