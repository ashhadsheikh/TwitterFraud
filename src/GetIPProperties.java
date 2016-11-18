// Print out a sorted list of mail exchange servers for a network domain name
import org.json.JSONObject;
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.NSRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

//isASNSame("techlogix.com")
public class GetIPProperties {
	public static boolean allElementsTheSame(ArrayList<String> array) {
	    if (array.size() == 0) {
	        return true;
	    } else {
	        String first = array.get(0);
	        for (String element : array) {
	            if (!element.equals(first)) {
	                return false;
	            }
	        }
	        return true;
	    }
	}
	public static boolean isASNSame(String hostname) throws UnknownHostException,
			TextParseException {
		ArrayList<String> al = new ArrayList<String>();
		ArrayList<String> ASN = new ArrayList<String>();
		InetAddress ip2 = InetAddress.getByName(hostname);
		System.out.println("A-Server: " + ip2.getHostAddress());
		al.add(ip2.getHostAddress());
		Record[] recordsMX = new Lookup(hostname, Type.MX).run();
		for (int i = 0; i < recordsMX.length; i++) {
			MXRecord ns = (MXRecord) recordsMX[i];
			al.add(InetAddress.getByName(ns.getTarget().toString())
					.getHostAddress());
			System.out.println("MXServer: "
					+ InetAddress.getByName(ns.getTarget().toString())
							.getHostAddress());
		}
		Record[] records = new Lookup(hostname, Type.NS).run();
		for (int i = 0; i < records.length; i++) {
			NSRecord ns = (NSRecord) records[i];
			al.add(InetAddress.getByName(ns.getTarget().toString())
					.getHostAddress());
			System.out.println("Nameserver: "
					+ InetAddress.getByName(ns.getTarget().toString())
							.getHostAddress());
		}

		for (String ip : al) {
			String output = "";
			try {
				URL url = new URL("https://api.moocher.io/as/ip/" + ip);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				if (conn.getResponseCode() != 200) {

					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
				String res = "";
				while ((output = br.readLine()) != null) {
					res += output;
				}
				conn.disconnect();
				JSONObject obj = new JSONObject(res);
				JSONObject data = obj.getJSONObject("as");

				ASN.add(data.getString("asn"));
			} catch (Exception e) {

			}
		}
		for (String record : ASN) {
			System.out.println(record);
		}
		if(allElementsTheSame(ASN))
			return true;
			else return false;
	}
}