package br.com.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.project.model.Messages;
import br.com.project.model.Trade;

/**Class that receives the values of the trade to be verified in the Web Service
 * @author Reinaldo Holtz
 * @version 1.00
 */
public class TradeController extends HttpServlet {
	
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
		PrintWriter writer = response.getWriter();
		
    	Trade trade = new Trade();
    	String type = request.getParameter("type");
    	
    	if (type != null && !type.isEmpty()){
    		trade.setCustomer(request.getParameter("customer"));
   			trade.setCcyPair(request.getParameter("ccypair"));
   			trade.setType(request.getParameter("type"));
   			trade.setDirection(request.getParameter("direction"));
   			trade.setTradeDate(request.getParameter("tradedate"));
			trade.setAmount1(request.getParameter("amount1"));     
   			trade.setAmount2(request.getParameter("amount2"));
   			trade.setRate(request.getParameter("rate"));
   			trade.setValueDate(request.getParameter("valuedate"));
			trade.setLegalEntity(request.getParameter("legalentity"));
   			trade.setTrader(request.getParameter("trader"));
	
   			if (type.toUpperCase().equals("VANILLAOPTION")){
    			trade.setStyle(request.getParameter("style"));
    			trade.setStrategy(request.getParameter("strategy"));
    			trade.setDeliveryDate(request.getParameter("deliverydate"));
    			trade.setExpiryDate(request.getParameter("expirydate"));
    			trade.setExcerciseStartDate(request.getParameter("excercisestartsate"));
    			trade.setPayCcy(request.getParameter("payccy"));
    			trade.setPremium(request.getParameter("premium"));
    			trade.setPremiumCcy(request.getParameter("premiumccy"));
    			trade.setPremiumType(request.getParameter("premiumtype"));
    			trade.setPremiumDate(request.getParameter("premiumdate"));
    		}
   			checkTrade(response,trade);
    	}else{
    		Messages msgs = new Messages();
        	writer.println("<h1>Trade Cheked</h1>");
        	writer.println("<h3>Message(s):</h3>");
        	writer.println("<ul>");
    		writer.println("<li>"+msgs.getMap().get("8888")+"</li>");
    		writer.println("</ul>");		    	
        	writer.println("<input type=\"button\" value=\"Back\" onClick=\"history.go(-1)\">"); 
    	}
    }
    
    public void checkTrade(HttpServletResponse response,Trade trade) throws ServletException, IOException{
    	String url = "http://localhost:8080/RESTWService/rest/json/service/get/";
    	String jsonCheck = "";
    	Messages msgs = new Messages();
    	Client client = Client.create();
		Gson gson = new Gson();
		PrintWriter writer = response.getWriter();		
		try{
			String jsonTrade = gson.toJson(trade);	
			jsonCheck =  jsonTrade;
			jsonTrade = jsonTrade.replace("{","%7B");
			jsonTrade = jsonTrade.replace("\"","%22");
			jsonTrade = jsonTrade.replace("}","%7D");
			jsonTrade = jsonTrade.replace(" ","%20");
		
			WebResource wr = client.resource(url+jsonTrade);			
			String msgResult = wr.get(String.class);			
			
	    	writer.println("<h1>Trade Cheked</h1>");
	    	writer.println(jsonCheck);
	    	writer.println("<h3>Message(s):</h3>");
	    	
	    	if (msgResult != null && !msgResult.isEmpty()){				
				for (int i=0;i<msgResult.length();i++){
					writer.println("<ul>");
			    	writer.println("<li>"+msgs.getMap().get(msgResult.substring(i, i+4))+"</li>");
			    	writer.println("</ul>");
			    	i = i+3;
				}
			}else{
				writer.println("<ul>");
		    	writer.println("<li>"+msgs.getMap().get(1000)+"</li>");
		    	writer.println("</ul>");	
			}	
		} catch (Exception ex) {
			writer.println("<h1>Trade Cheked</h1>");
	    	writer.println(jsonCheck);
	    	writer.println("<h3>Message(s):</h3>");
	    	writer.println("<ul>");
	    	writer.println("<li>"+msgs.getMap().get(9999)+"</li>");
	    	writer.println("</ul>");	
		}
    	writer.println("<input type=\"button\" value=\"Back\" onClick=\"history.go(-1)\">");
    }
 
}
