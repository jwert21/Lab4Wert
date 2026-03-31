import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String type;
    private String id;
    private String crust;
    private String size;

    public Pizza(String type, String id, String crust, String size) {
        this.type = type;
        this.id = id;
        this.crust = crust;
        this.size = size;
    }

    public String toFixedFormatString() {
            return String.format("%-10s%-10s%-20s", type, id, crust, size);
        }

        public static Pizza fromFixedFormatString(String line) {
            String type = line.substring(0, 10).trim();
            String id = line.substring(10, 20).trim();
            String crust = line.substring(20, 40).trim();
            String size = line.substring(40, 80).trim();
            return new Pizza(type, id, crust, size);
        }
    }

    public static void main(String[] args) {
        List<Pizza> pizza = new ArrayList<>();
        pizzas.add(new Pizza("Pepperoni", "1", "Neapolitan", "Personal"));
        pizzas.add(new Pizza("Cheese", "2", "New York-Style", "Small"));
        pizzas.add(new Pizza("Sausage", "3", "Sicilian", "Medium"));
        pizzas.add(new Pizza("Vegetable", "4", "Focaccia", "Large"));
        pizzas.add(new Pizza("Mushroom", "5", "New York-Style", "Jumbo Large"));

        try (PrintWriter writer = new PrintWriter(new FileWriter("pizzas.txt"))) {
            for (Pizza pizza : pizzas) {
                writer.println(pizza.toFixedFormatString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Pizza> loadedPizzas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("pizzas.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                loadedPizzas.add(Pizza.fromFixedFormatString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Pizza pizza : loadedPizzas) {
            System.out.println("Type: " + pizza.type + ", ID: " + pizza.id + ", Crust: " + pizza.crust + ", Size: " + pizza.size);
        }

        public String getType() { return type; }
        public String getId() { return id; }
        public String getCrust() { return crust; }
        public String getSize() { return size; }
    }
}

