//A+ Computer Science  -  www.apluscompsci.com
//Names - Amber, Akash, Arjun, Connor, Nihal, Narendhar, Sonia
//Date - 3/24/18
//Class - AP Comp Sci Period 7
//Lab  - Galaga
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// A utility class for sending HTTP requests to servers
public class HTTPUtils {
    // Desired HTTP user agent
    public final static String USER_AGENT = "Mozilla/5.0";
    // HTTP GET request
    public static String sendGet(String url) {
        try {
            // Create objects around specified url
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

            // Create a bufferedreader for reading the server's response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            // A buffer for faster string concatenation
            // Technically += should never be used, but
            // it works for most scenarios. This one requires
            // a better method of concatenation.
            StringBuffer response = new StringBuffer();
            // Continue appending data until there's no more lines left
            while ((inputLine = in.readLine()) != null) {
                // Concatenate the line to the string
                response.append(inputLine);
            }
            // Close the connection buffer
            in.close();

            // Return the response
            return response.toString();
        }
        // If anything went wrong, print the exception
        // If the user isn't connected, a ConnectException will be thrown and printed
        catch(IOException e) {
            System.out.println(e);
        }
        // If there was an error, return an empty string
        return "";
    }
}
