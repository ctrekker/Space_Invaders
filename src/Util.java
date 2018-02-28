public class Util {
    public static int random(int min, int max) {
        // To be inclusive, add one to max
        max++;
        return (int)(Math.random()*(max-min)+min);
    }
}
