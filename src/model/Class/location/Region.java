package model.Class.location;

public class Region {
    private int regionID;
    private String village;
    private String district;
    private double latitude;
    private double longitude;
    
    public Region(int regionID, String village, String district, double latitude, double longitude) {
        this.regionID = regionID;
        this.village = village;
        this.district = district;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Region() {
        //TODO Auto-generated constructor stub
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
