package controller;

import model.Class.order.OrderItem;

public class CalculateCost {
    public static double calculateGoRide(double distance, double fareKm) {
        return fareKm * distance;
    }

    public static double calculateGoCar(double distance, double fareKm, double addFareKm) {
        return (fareKm + addFareKm) * distance;
    }

    public static double calculateGoSend(double distance, double fareKm, double fare5Kg, float weight) {
        return (fareKm * distance) + (fare5Kg * ((int) weight / 5));
    }

    public static double calculateGoFood(double distance, double fareKm, OrderItem[] cart) {
        double totalHarga = 0;

        for (OrderItem c : cart) {
            totalHarga += (c.getJumlah() * c.getItem().getHarga());
        }

        return (fareKm * distance) + totalHarga;
    }
}
