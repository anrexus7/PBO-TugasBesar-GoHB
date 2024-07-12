package controller;

import model.Enum.CarTypeOfCarService;
import model.Enum.TipeBarang;

public class ServiceAndPackage {
    public static CarTypeOfCarService getTypeOfCarService(String service) {
        for (CarTypeOfCarService value : CarTypeOfCarService.values()) {
            if (value.toString().equals(service)) {
                return value;
            }
        }
        return null;
    }

    public static TipeBarang getTipeBarang(String barang) {
        for (TipeBarang value : TipeBarang.values()) {
            if (value.toString().equals(barang)) {
                return value;
            }
        }
        return null;
    }
}
