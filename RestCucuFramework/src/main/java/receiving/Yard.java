package receiving;

public class Yard {
	private float number;
	  private String name;
	  Address AddressObject;
	  private boolean isCrashToysYard;
	  Phone PhoneObject;
	  private String timeZoneCode;


	 // Getter Methods 

	  public float getNumber() {
	    return number;
	  }

	  public String getName() {
	    return name;
	  }

	  public Address getAddress() {
	    return AddressObject;
	  }

	  public boolean getIsCrashToysYard() {
	    return isCrashToysYard;
	  }

	  public Phone getPhone() {
	    return PhoneObject;
	  }

	  public String getTimeZoneCode() {
	    return timeZoneCode;
	  }

	 // Setter Methods 

	  public void setNumber( float number ) {
	    this.number = number;
	  }

	  public void setName( String name ) {
	    this.name = name;
	  }

	  public void setAddress( Address addressObject ) {
	    this.AddressObject = addressObject;
	  }

	  public void setIsCrashToysYard( boolean isCrashToysYard ) {
	    this.isCrashToysYard = isCrashToysYard;
	  }

	  public void setPhone( Phone phoneObject ) {
	    this.PhoneObject = phoneObject;
	  }

	  public void setTimeZoneCode( String timeZoneCode ) {
	    this.timeZoneCode = timeZoneCode;
	  }
	}

