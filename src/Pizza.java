import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Pizza {
}

    public static class Pizza {
        private String type;
        private String id;
        private String crust;

        public Pizza(String type, String id, String crust) {
            this.type = type;
            this.id = id;
            this.crust = crust;
        }

        public String toFixedFormatString() {
            return String.format("%-10s%-10s%-20s", type, id, crust);
        }

        public static Pizza fromFixedFormatString(String line) {
            String type = line.substring(0, 10).trim();
            String id = line.substring(10, 20).trim();
            String crust = line.substring(20, 40).trim();
            return new Pizza(type, id, crust);
        }
    }

    public static void main(String[] args) {
        // Example data
        List<Pizza> pizza = new ArrayList<>();
        pizzas.add(new Pizza("Pepperoni", "1", "Neapolitan"));
        pizzas.add(new Pizza("Cheese", "2", "New York-Style"));
        pizzas.add(new Pizza("Sausage", "3", "Sicilian"));
        pizzas.add(new Pizza("Vegetable", "4", "Focaccia"));
        pizzas.add(new Pizza("Mushroom", "5", "New York-Style"));

        // Write students to a flat file
        try (PrintWriter writer = new PrintWriter(new FileWriter("pizzas.txt"))) {
            for (Pizza pizza : pizzas) {
                writer.println(pizza.toFixedFormatString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read students from the flat file
        List<Pizza> loadedPizzas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("pizzas.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                loadedPizzas.add(Pizza.fromFixedFormatString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display loaded students
        for (Pizza pizza : loadedPizzas) {
            System.out.println("Name: " + pizza.type + ", ID: " + pizza.id + ", Crust: " + pizza.crust);
        }
    }
}

