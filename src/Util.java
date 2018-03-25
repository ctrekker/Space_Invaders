//A+ Computer Science  -  www.apluscompsci.com
//Names - Amber, Akash, Arjun, Connor, Nihal, Narendhar, Sonia
//Date - 3/24/18
//Class - AP Comp Sci Period 7
//Lab  - Galaga

// A simple utility class which can't be instantiated (because it's abstract)
public abstract class Util {
    // Static random method which takes a minimum and maximum value (remember, its INCLUSIVE on BOTH sides)
    public static int random(int min, int max) {
        // To be inclusive, add one to max
        max++;
        return (int)(Math.random()*(max-min)+min);
    }
}
