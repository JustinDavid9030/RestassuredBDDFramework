package resources;


import receiving.Address;
import receiving.BasicInfo;
import receiving.Phone;
import receiving.VehicleInformation;
import receiving.Yard;
import receiving.Zip;

public class TestDataBuild {

	
	public void receiving_payload() {
		VehicleInformation v = new VehicleInformation ();
		v.setColor("Black");
		v.setVehicleIdentificationNumber(null);
		v.setVinSerial(null);
		v.setMakeOfVehicle(null);
		v.setModelOfVehicle(null);
		v.setVehicleType(null);
		v.setVrn(null);
		v.setYearOfVehicle(2022);
		BasicInfo b = new BasicInfo();
		b.setCheckInDate(null);
		b.setCopartSelectCode(null);
		b.setEnableCancelAssignment(false);
		b.setHasConditionReport(null);
		b.setIs360ImagingEnabled(null);
		b.setIsBluCar(false);
		b.setIsDriveYard(null);
		b.setIsT3Enabled(null);
		b.setLotId(0);
		b.setLotStage(null);
		b.setLotStageDescription(null);
		b.setLotSuffix(null);
		b.setLotType(null);
		b.setSaleYard(0);
		b.setSellerType(null);
		Address a = new Address();
		a.setCity(null);
		a.setCountry(null);
		a.setLine1(null);
		a.setLine2(null);
		a.setState(null);
		a.setZip(null);
		Phone p = new Phone();
		p.setAreaCode(null);
		p.setLine(null);
		Yard y = new Yard();
		y.setAddress(a);
		y.setIsCrashToysYard(false);
		y.setName(null);
		y.setNumber(0);
		y.setPhone(p);
		y.setTimeZoneCode(null);
		Zip z = new Zip();
		z.setZip1(null);
		z.setZip2(null);
	}
}
