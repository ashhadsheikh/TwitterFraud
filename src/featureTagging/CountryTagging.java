package featureTagging;

public class CountryTagging {
	String[] countries = new String[17];
	public CountryTagging() {
		// TODO Auto-generated constructor stub
		countries[0]="Thailand";
		countries[1]="Hong Kong";
		countries[2]="Chile";
		countries[3]="Belgium";
		countries[4]="Virgin Islands";
		countries[5]="Ukraine";
		countries[6]="Hungary";
		countries[7]="Bulgaria";
		countries[8]="Mexico";
		countries[9]="Belarus";
		countries[10]="Argentina";
		countries[11]="Serbia";
		countries[12]="Antartica";
		countries[13]="Lithuania";
		countries[14]="Georgia";
		countries[15]="Romania";
		countries[16]="Taiwan";
		
	}
	public boolean isCountryMalicious(String country){
		for(int i=0;i<17;i++){
			if(country.equals(countries[i]))
				return true;
		}
		return false;
	}
}
