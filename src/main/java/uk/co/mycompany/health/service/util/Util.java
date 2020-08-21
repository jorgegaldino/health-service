package uk.co.mycompany.health.service.util;

public class Util {
	
	public static double kmTomiles(double distanceInKm) {
        return Math.ceil(distanceInKm * 0.621371);
    }

}
