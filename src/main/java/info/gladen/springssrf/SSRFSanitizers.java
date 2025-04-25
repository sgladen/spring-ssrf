package info.gladen.springssrf;

public class SSRFSanitizers {
    public static String sanitize(String input) {
        // This is not an actual sanitizer.
        return input.replace("/", "_");
    }
}
