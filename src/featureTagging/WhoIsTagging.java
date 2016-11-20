package featureTagging;

public class WhoIsTagging {
	
	public boolean isUrlMalicious(String registry,String expiry){
		String[] registryPart = registry.split("-");
		String[] expiryPart = expiry.split("-");
		int RegistryYear=Integer.parseInt(registryPart[2]);
		int expiryYear=Integer.parseInt(expiryPart[2]);
		int time=expiryYear-RegistryYear;
		System.out.println("Total Time: "+time);
		if(time<=1)
			return true;
		else
			return false;
	}

}
