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

public class WebServiceSend {

    public static void main(String[] args) {
        try {
            String url = "http://localhost:8000/hello";
            URL obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Change to POST
            con.setRequestMethod("POST");

            // Enable sending data
            con.setDoOutput(true);

            // Set headers
            con.setRequestProperty("Content-Type", "application/json");

            // Create Pizza object
            Pizza pizza = new Pizza("Large", "Pepperoni", 2);

            // Convert Pizza to JSON (manual way)
            String jsonInput = "{"
                    + "\"size\":\"" + pizza.getSize() + "\","
                    + "\"topping\":\"" + pizza.getTopping() + "\","
                    + "\"quantity\":" + pizza.getQuantity()
                    + "}";

            // Send JSON
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line.trim());
            }
            in.close();

            System.out.println("Response: " + response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}