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
		for (String string : links) {
			FeatureTagging.featureCollector(string);
			HashMap<String, Boolean> features=FeatureTagging.getFeatureVector();
			
			System.out.println(string+","+features.get("isBlacklisted")+","+features.get("isFullCircle")+","+features.get("isASNSame")+","+features.get("isCountryMalicious")+","+features.get("checkLifeline"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
