package Model;


public class Location {

    private double longitude;
    private double latitude;
    private Address address;

    public Location(double longitude, double latitude, Address adress) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = adress;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isFromCountry(String country) {
        return address.getCountry().toLowerCase().contains(country.toLowerCase());
    }

    public boolean isFromCity(String city) {
        return address.getCity().toLowerCase().contains(city.toLowerCase());
    }
}
