import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.xbill.DNS.TextParseException;

import dbHandler.TestDB;
import featureTagging.FeatureTagging;


public class MainClass {
public static void main(String [] parms) throws ParseException, MalformedURLException, TextParseException, UnknownHostException{
	try {
		TestDB tb=new TestDB();
		ArrayList<String> links=tb.getMostTweetedLinks();
		for (String link : links) {
			FeatureTagging.featureCollector(link);
			HashMap<String, Boolean> features=FeatureTagging.getFeatureVector();
			tb.addResult(link, 
					convertBooleanToFlag(features.get("isBlacklisted")),
					convertBooleanToFlag(features.get("isFullCircle")),
					convertBooleanToFlag(features.get("isASNSame")),
					convertBooleanToFlag(features.get("isCountryMalicious")),
					convertBooleanToFlag(features.get("checkLifeline")));
			System.out.println(link+","+features.get("isBlacklisted")+","+features.get("isFullCircle")+","+features.get("isASNSame")+","+features.get("isCountryMalicious")+","+features.get("checkLifeline"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static String convertBooleanToFlag(boolean value){
	if (value)
		return "1";
	else
		return "0";
				
}
}
