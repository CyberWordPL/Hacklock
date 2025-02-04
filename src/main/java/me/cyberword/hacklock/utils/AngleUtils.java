package me.cyberword.hacklock.utils;

public class AngleUtils {
    public static double toDegrees(double radians) {
        double oneDegreeInRadians = Math.PI / 180;
        return radians / oneDegreeInRadians;
    }
}
