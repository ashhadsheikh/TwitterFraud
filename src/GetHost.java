import java.net.InetAddress;
import java.net.UnknownHostException;
import org.xbill.DNS.ExtendedResolver;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Name;
import org.xbill.DNS.PTRRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.Resolver;
import org.xbill.DNS.ReverseMap;
import org.xbill.DNS.Type;

public class GetHost {

	// isFullCircleConfirmed("www.almusnet.com")
	public boolean isFullCircleConfirmed(String url) {
		try {
			final InetAddress ip2 = InetAddress.getByName(url);
			System.out.println(ip2.getHostAddress());
			final byte[] bytes = ip2.getAddress();
			final String host = getHostByIPAddress(bytes);
			System.out.println("\nJAVADISCOVER HOST : " + host);
			System.out.println(InetAddress.getByName(host).getHostAddress());
			if (ip2.getHostAddress().toString()
					.equals(InetAddress.getByName(host).getHostAddress()))
				return true;
			else
				return false;
		} catch (UnknownHostException e) {
			System.out.println("Host NOT Avaialble");
			return false;
		}
	}

	public static String getHostByIPAddress(byte[] addr)
			throws UnknownHostException {

		Name name = ReverseMap.fromAddress(InetAddress.getByAddress(addr));

		// OPEN DNS SERVERS
		final String[] openDNS = new String[] { "208.67.222.222",
				"208.67.220.220" };
		final Resolver resolver = new ExtendedResolver(openDNS);
		final Lookup lookUp = new Lookup(name, Type.PTR);
		lookUp.setResolver(resolver);
		Record[] records = lookUp.run();
		if (records == null) {
			throw new UnknownHostException();
		}
		return ((PTRRecord) records[0]).getTarget().toString();
	}

}
