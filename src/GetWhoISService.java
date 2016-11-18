import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class GetWhoISService {
	JSONObject data;

	public GetWhoISService(String url) {
		JSONObject obj = new JSONObject(WhoisCall(url));
		if (obj.getString("response_code").toString().equals("success")) {
			data = obj.getJSONObject("formatted_data");
		}
	}

	public static String WhoisCall(String IP) {

		String output = "";
		try {
			URL url = new URL(
					"http://api.bulkwhoisapi.com/whoisAPI.php?domain=" + IP
							+ "&type=whois&token=usemeforfree");
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

	public String getDomainName() {
		if (data.has("DomainName"))
			return data.getString("DomainName");
		else
			return "";
	}

	public String getLastUpdated() {
		if (data.has("LastupdateofWHOISdatabase"))
			return data.getString("LastupdateofWHOISdatabase");
		else
			return "";
	}

	public String getRegistrar() {
		if (data.has("RegistrarWHOISServer"))
			return data.getString("RegistrarWHOISServer");
		else
			return "";
	}

	public String getRegistrarURL() {
		if (data.has("RegistrarURL"))
			return data.getString("RegistrarURL");
		else
			return "";
	}

	public String getRegistrant() {
		if (data.has("RegistrantName"))
			return data.getString("RegistrantName");
		else
			return "";
	}

	public String getRegistrantOrganization() {
		if (data.has("RegistrantOrganization"))
			return data.getString("RegistrantOrganization");
		else
			return "";
	}

}