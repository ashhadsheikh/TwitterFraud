package featureTagging;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.xbill.DNS.TextParseException;

import featureCollectorHostBased.CheckBlackList;
import featureCollectorHostBased.GetGeoProperties;
import featureCollectorHostBased.GetHost;
import featureCollectorHostBased.GetIPProperties;
import featureCollectorHostBased.GetWhoISService;

public class FeatureTagging {
	static ArrayList<Boolean> features;
	

	public static void featureCollector(String uri) throws SQLException,
			UnknownHostException, TextParseException, MalformedURLException {
		features = new ArrayList<Boolean>();
		URL url = new URL(uri);
		String host = url.getHost();
		CheckBlackList cbl = new CheckBlackList();
		System.out.println("isBlacklisted: " + cbl.isBlacklisted(host));
		/*==================================================*/
		features.add(cbl.isBlacklisted(host));
		/*==================================================*/
		GetHost ghost = new GetHost();
		System.out
				.println("isFullCircle: " + ghost.isFullCircleConfirmed(host));
		/*==================================================*/
		features.add(ghost.isFullCircleConfirmed(host));
		/*==================================================*/
		GetIPProperties gIP = new GetIPProperties();
		System.out.println("isASNSame: " + gIP.isASNSame(host));
		/*==================================================*/
		features.add(gIP.isASNSame(host));
		/*==================================================*/
		GetGeoProperties ggp = new GetGeoProperties(host);
		System.out.println("Country: " + ggp.getCountry());
		System.out.println("Region: " + ggp.getRegion());
		System.out.println("City: " + ggp.getCity());
		System.out.println("Zip Code: " + ggp.getZipCode());
		System.out.println("TimeZone: " + ggp.getTimeZone());
		System.out.println("Latitude: " + ggp.getLatitude());
		System.out.println("Longitude: " + ggp.getLongitude());
		CountryTagging ct=new CountryTagging();
		/*==================================================*/
		features.add(ct.isCountryMalicious(ggp.getCountry()));
		/*==================================================*/
		GetWhoISService gwois = new GetWhoISService(host);
		System.out.println("Last Updated: " + gwois.getLastUpdated());
		System.out.println("Registrar: " + gwois.getRegistrar());
		System.out.println("Registrar URL: " + gwois.getRegistrarURL());
		System.out.println("Registrant: " + gwois.getRegistrant());
		System.out.println("Registrant Organization: "
				+ gwois.getRegistrantOrganization());
	}

}
