package collector;

// This class has logic to manage collection rules
public class Ruler {

    // Return true if value of specified parameter name need to be collected
    public static boolean isNeeded(String paramName) {
        String lower = paramName.toLowerCase();
        return lower.endsWith("id");
    }
}
