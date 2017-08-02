package br.com.project.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Messages {
	public static final Map<String, String> map = initializeMap();
    
    private static Map<String, String> initializeMap() {
	      Map<String, String> m = new HashMap<String, String>();
	      m.put( "1000", new String( "Trade checked successfully..." ));
	      m.put( "1001", new String( "The Value Date cannot be before Trade Date." ));
	      m.put( "1002", new String( "The Value Date cannot fall on weekend or non-working day for currency." ));
	      m.put( "1003", new String( "The counterparty is not supported." ));
	      m.put( "1004", new String( "The CcyPair the currency is not valid according ISO code." ));
	      m.put( "1005", new String( "The style can be either American or European." ));
	      m.put( "1006", new String( "The American option the Excercise Start Date has to be after the Trade Date and before Expiry Date." ));
	      m.put( "1007", new String( "Expiry date and premium date shall be before delivery date." ));
	      m.put( "8888", new String( "It is mandatory to fill in all fields."));
	      m.put( "9999", new String( "Error found in trade!!! Please, fill in all fields and try again." ));
	      return Collections.unmodifiableMap(m);
    }

	public static Map<String, String> getMap() {
		return map;
	}
}
