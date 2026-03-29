import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Pizza {
}

    public static class Pizza {
        private String name;
        private String id;
        private String course;

        public Pizza(String name, String id, String course) {
            this.name = name;
            this.id = id;
            this.course = course;
        }

        public String toFixedFormatString() {
            return String.format("%-10s%-10s%-20s", name, id, course);
        }

        public static Pizza fromFixedFormatString(String line) {
            String name = line.substring(0, 10).trim();
            String id = line.substring(10, 20).trim();
            String course = line.substring(20, 40).trim();
            return new Pizza(name, id, course);
        }
    }

    public static void main(String[] args) {
        // Example data
        List<Pizza> pizza = new ArrayList<>();
        pizzas.add(new Pizza("Alice", "1001", "Math"));
        pizzas.add(new Pizza("Bob", "1002", "History"));

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
            System.out.println("Name: " + pizza.name + ", ID: " + pizza.id + ", Course: " + pizza.course);
        }
    }
}

