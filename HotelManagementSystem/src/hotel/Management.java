package hotel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Management {
	
	private IntegerProperty guestid;
    private StringProperty name;
    private IntegerProperty age;
    private StringProperty country;
    private StringProperty email;
    private StringProperty roomtype;
	private IntegerProperty roomnumber;
	private StringProperty bedtype;
	private StringProperty arrival;
	private StringProperty departure;
	private IntegerProperty adults;
	private IntegerProperty children;
	
	public Management() {
	}

	public Management(int guestid, String name, int age,String email, String country,  int roomnumber,String roomtype, String bedtype, 
			String arrival, String departure, int adults, int children) {
		this.guestid = new SimpleIntegerProperty(guestid);
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleIntegerProperty(age);
		this.email = new SimpleStringProperty(email);
		this.country = new SimpleStringProperty(country);
		this.roomnumber= new SimpleIntegerProperty(roomnumber);
		this.roomtype= new SimpleStringProperty(roomtype);
		this.bedtype= new SimpleStringProperty(bedtype);
		this.arrival = new SimpleStringProperty(arrival);
		this.departure = new SimpleStringProperty(departure);
		this.adults = new SimpleIntegerProperty(adults);
		this.children = new SimpleIntegerProperty(children);
	}

	//0) name
	public final IntegerProperty guestidProperty() {
		return this.guestid;
	}

	public final int getGuestID() {
		return this.guestidProperty().get();
	}
		
	public final void setGuestID(final int guestid) {
		this.guestidProperty().set(guestid);
	}
		
	//1) name
	public final StringProperty nameProperty() {
		return this.name;
	}

	public final String getName() {
		return this.nameProperty().get();
	}
	
	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
	
	//2)age

	public final IntegerProperty ageProperty() {
		return this.age;
	}
	
	public final int getAge() {
		return this.ageProperty().get();
	}
	
	public final void setAge(final int age) {
		this.ageProperty().set(age);
	}
	
	//3)country
	public final StringProperty countryProperty() {
		return this.country;
	}

	public final String getCountry() {
		return this.countryProperty().get();
	}
	
	public final void setCountry(final String country) {
		this.countryProperty().set(country);
	}
	
	//4)email
	public final StringProperty emailProperty() {
		return this.email;
	}

	public final String getEmail() {
		return this.emailProperty().get();
	}
	
	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	
	
	//5)room type
	public final StringProperty roomTypeProperty() {
		return this.roomtype;
	}
	

	public final String getRoomType() {
		return this.roomTypeProperty().get();
	}
	

	public final void setRoomType(final String roomtype) {
		this.roomTypeProperty().set(roomtype);
	}
	//6)bed type
	public final StringProperty bedTypeProperty() {
		return this.bedtype;
	}
	

	public final String getBedType() {
		return this.bedTypeProperty().get();
	}
	

	public final void setBedType(final String bedtype) {
		this.bedTypeProperty().set(bedtype);
	}
	
	//7)room number
	public final IntegerProperty roomNumberProperty() {
		return this.roomnumber;
	}
	

	public final int getRoomNumber() {
		return this.roomNumberProperty().get();
	}
	

	public final void setRoomNumber(final int roomnumber) {
		this.roomNumberProperty().set(roomnumber);
	}
	
	//8)arrival date

	public final StringProperty arrivalProperty() {
		return this.arrival;
	}
	

	public final String getArrival() {
		return this.arrivalProperty().get();
	}
	

	public final void setArrival(final String arrival) {
		this.arrivalProperty().set(arrival);
	}
	
	//9)departure date
	public final StringProperty departureProperty() {
		return this.departure;
	}

	public final String getDeparture() {
		return this.departureProperty().get();
	}
	

	public final void setDeparture(final String departure) {
		this.departureProperty().set(departure);
	}	
	
	//10)adults
	public final IntegerProperty adultsProperty() {
		return this.adults;
	}
	

	public final int getAdults() {
		return this.adultsProperty().get();
	}
	
	public final void setAdults(final int adults) {
		this.adultsProperty().set(adults);
	}
	
	//11)children
	
	public final IntegerProperty childrenProperty() {
		return this.children;
	}
	

	public final int getChildren() {
		return this.childrenProperty().get();
	}
	

	public final void setChildren(final int children) {
		this.childrenProperty().set(children);
	}
	
}
