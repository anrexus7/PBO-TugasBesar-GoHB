package model.Class.location;

public class Location extends Region {
    private String address;

    public Location(int regionID, String village, String district, double latitude, double longitude, String address) {
        super(regionID, village, district, latitude, longitude);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
