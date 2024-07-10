package model.Class;

public class Location {
    private String address;
    private float[] coordinates;

    public Location(String address, float[] coordinates) {
        this.address = address;
        this.coordinates = coordinates;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(float[] coordinates) {
        this.coordinates = coordinates;
    }
}
