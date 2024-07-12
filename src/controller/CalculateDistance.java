package controller;

import model.Class.location.Location;

public class CalculateDistance {
    public static double calculateDistance(Location initialLocation, Location finaLocation) {
        double initialRadiansLatitude = Math.toRadians(initialLocation.getLatitude());
        double finalRadiansLatitude = Math.toRadians(finaLocation.getLatitude());
        double deltaRadiansLatitude = Math.abs(finalRadiansLatitude - initialRadiansLatitude);

        double initialRadiansLongitude = Math.toRadians(initialLocation.getLongitude());
        double finalRadiansLongitude = Math.toRadians(finaLocation.getLongitude());
        double deltaRadiansLongitude = Math.abs(finalRadiansLongitude - initialRadiansLongitude);

        double earthRadius = 6371.0; // in kilometers
        double haversine = Math.pow(Math.sin(deltaRadiansLatitude / 2), 2) +
                       Math.cos(initialRadiansLatitude) * Math.cos(finalRadiansLatitude) *
                       Math.pow(Math.sin(deltaRadiansLongitude / 2), 2);
        double centralAngle = Math.atan2(Math.sqrt(haversine), Math.sqrt(1 - haversine));

        return earthRadius * centralAngle;
    }
}
