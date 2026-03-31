/** Project: Lab 4
 * Purpose Details:
 * Course: IST 242
 * Author: Jonah Wert
 * Date Developed: 3/27/2026
 * Last Date Changed: 3/30/2026
 * Rev: 3/30/2026

 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;

public class WebServiceSend {

    public static void main(String[] args) {
        try {
            // URL of your server
            URL url = new URL("http://localhost:8000/hello");

            // Open connection
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Set POST method
            con.setRequestMethod("POST");

            // Allow sending body
            con.setDoOutput(true);

            // Set headers
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");

            // Create Pizza object
            Pizza pizza = new Pizza("Pepperoni", "1", "Neapolitan", "Large");

            // Convert Pizza → JSON
            Gson gson = new Gson();
            String jsonInput = gson.toJson(pizza);

            System.out.println("Sending JSON: " + jsonInput);

            // Send JSON to server
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get response code
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read response
            BufferedReader br;
            if (responseCode >= 200 && responseCode < 300) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
            }

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }

            br.close();

            System.out.println("Response: " + response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}