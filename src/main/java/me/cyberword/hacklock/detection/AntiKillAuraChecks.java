package me.cyberword.hacklock.detection;


import me.cyberword.hacklock.utils.AngleUtils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class AntiKillAuraChecks {

    public static double getAngle(Player attaker, Entity attacked) {
        Vector lookDirection = attaker.getEyeLocation().getDirection();
        Vector attackedDirection = attacked.getLocation().subtract(attaker.getLocation()).toVector().normalize();

        double radAngle = attackedDirection.angle(lookDirection);
        return AngleUtils.toDegrees(radAngle);
    }

    private static boolean shouldUseAngleCheck(Player attaker, Entity attacked) {
        Location attakerPosition = attaker.getLocation();
        Location attackedPosition = attacked.getLocation();

        double distance = attakerPosition.distance(attackedPosition);
        return distance > 1;
    }

    private static boolean angleCheck(Player attaker, Entity attacked) {
        double degAngle = getAngle(attaker, attacked);

        return !(degAngle > 45 || degAngle < -45);
    }

    public static boolean checkIfHitIsValid(Player attaker, Entity attacked) {
        boolean isValidHit = true;

        if (shouldUseAngleCheck(attaker, attacked)) {
            isValidHit = angleCheck(attaker, attacked);
        }

        return isValidHit;
    }
}
