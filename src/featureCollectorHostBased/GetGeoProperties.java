package featureCollectorHostBased;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import org.json.JSONObject;

public class GetGeoProperties {
	JSONObject obj;

	public GetGeoProperties(String hostname) {

		try {
			InetAddress ipaddress = InetAddress.getByName(hostname);
			System.out.println("IP address: " + ipaddress.getHostAddress());
			obj = new JSONObject(ApiCall(ipaddress.getHostAddress()));
		} catch (Exception e) {

		}
	}

	public String getCountry() {
		return obj.getString("country_name");
	}

	public String getRegion() {
		return obj.getString("region_name");
	}

	public String getCity() {
		return obj.getString("city");
	}

	public String getZipCode() {
		return obj.getString("zip_code");
	}

	public String getTimeZone() {
		return obj.getString("time_zone");
	}

	public String getMetroCode() {
		return obj.getString("metro_code");
	}

	public double getLatitude() {
		return obj.getDouble("latitude");
	}

	public double getLongitude() {
		return obj.getDouble("longitude");
	}

	public static String ApiCall(String IP) {

		String output = "";
		try {
			URL url = new URL("http://freegeoip.net/json/" + IP);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
			return res;
		} catch (Exception e) {

		}
		return output;
	}

}
