package Model.Class;

public class Location {
    private String address;
    private String city;
    private float[] coordinates;

    public Location(String address, String city, float[] coordinates) {
        this.address = address;
        this.city = city;
        this.coordinates = coordinates;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(float[] coordinates) {
        this.coordinates = coordinates;
    }
}
