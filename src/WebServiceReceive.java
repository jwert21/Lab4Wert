/** Project: Lab 4
 * Purpose Details:
 * Course: IST 242
 * Author: Jonah Wert
 * Date Developed: 3/27/2026
 * Last Date Changed: 3/30/2026
 * Rev: 3/30/2026

 */

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class WebServiceReceive {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/pizzashop", new MyHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server running on http://localhost:8000/pizzashop");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {

                InputStream is = exchange.getRequestBody();
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

                System.out.println("Received JSON: " + body);

                Gson gson = new Gson();
                Pizza pizza = gson.fromJson(body, Pizza.class);

                System.out.println("Converted to Pizza object:");
                System.out.println("Type: " + pizza.getType());
                System.out.println("ID: " + pizza.getId());
                System.out.println("Crust: " + pizza.getCrust());
                System.out.println("Size: " + pizza.getSize());

                String response = "Pizza received successfully!";
                exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();

            } else {
                String response = "Only POST requests are supported.";
                exchange.sendResponseHeaders(405, response.getBytes(StandardCharsets.UTF_8).length);

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();
            }
        }
    }
}