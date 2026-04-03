/** Project: Lab 4
 * Purpose Details:
 * Course: IST 242
 * Author: Jonah Wert
 * Date Developed: 3/27/2026
 * Last Date Changed: 3/30/2026
 * Rev: 3/30/2026

 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.FileWriter;
import java.io.PrintWriter;

public class RabbitMQSend {

    private static final String QUEUE_NAME = "pizzashop_queue";

    public static void main(String[] argv) throws Exception {

        //creates pizza object
        Pizza pizza = new Pizza("Pepperoni", "1", "Thin Crust", "Large");

        // Convert to fixed flat file format using method in pizza class
        String message = pizza.toFixedFormatString();

        //Write to pizzaFlatFile.txt (fixed flat file)
        try (PrintWriter writer = new PrintWriter(new FileWriter("pizzaFlatFile.txt"))) {
            writer.println(message);
        }

        System.out.println("Flat file written to pizzaFlatFile.txt");

        // Sending flat file data to RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            //publishing the flat file data bytes to rabbitmq queue that was declared
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            System.out.println("Sent flat file data: " + message);
        }
    }
}