import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;

import org.xbill.DNS.TextParseException;

import featureTagging.FeatureTagging;


public class MainClass {
public static void main(String [] parms) throws ParseException{
	try {
		FeatureTagging.featureCollector("http://www.techlogix.com");
	} catch (UnknownHostException | TextParseException | MalformedURLException
			| SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
