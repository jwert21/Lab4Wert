/** Project: Lab 4
 * Purpose Details:
 * Course: IST 242
 * Author: Jonah Wert
 * Date Developed: 3/27/2026
 * Last Date Changed: 3/30/2026
 * Rev: 3/30/2026

 */

import com.rabbitmq.client.*;

import java.io.FileWriter;
import java.io.PrintWriter;

public class RabbitMQReceive {

    private static final String QUEUE_NAME = "pizzashop_queue";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println("Waiting for flat file messages...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());

            System.out.println("[*] Received flat file data:");
            System.out.println(message);

            // Convert back to Pizza object
            Pizza newPizza = Pizza.fromFixedFormatString(message);

            //Deserializing pizza object after receiving
            System.out.println("\nConverted to Pizza object:");
            System.out.println("Type: " + newPizza.getType());
            System.out.println("ID: " + newPizza.getId());
            System.out.println("Crust: " + newPizza.getCrust());
            System.out.println("Size: " + newPizza.getSize());

            //write received data to a file for comparison
            try (PrintWriter writer = new PrintWriter(new FileWriter("pizzaFlatFile_received.txt"))) {
                writer.println(message);
            }

            System.out.println("\nSaved received data to pizzaFlatFile_received.txt");
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}