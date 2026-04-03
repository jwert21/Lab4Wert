/** Project: Lab 4
 * Purpose Details:
 * Course: IST 242
 * Author: Jonah Wert
 * Date Developed: 3/27/2026
 * Last Date Changed: 3/30/2026
 * Rev: 3/30/2026

 */

public class Pizza {

    private String type;
    private String id;
    private String crust;
    private String size;

    // Constructor
    public Pizza(String type, String id, String crust, String size) {
        this.type = type;
        this.id = id;
        this.crust = crust;
        this.size = size;
    }

    // Default constructor
    public Pizza() {}

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // Convert to fixed-width flat file string
    public String toFixedFormatString() {
        return String.format("%-10s%-10s%-20s%-40s",
                type, id, crust, size);
    }

    // Convert from fixed-width string
    public static Pizza fromFixedFormatString(String line) {
        String type = line.substring(0, 10).trim();
        String id = line.substring(10, 20).trim();
        String crust = line.substring(20, 40).trim();
        String size = line.length() >= 80
                ? line.substring(40, 80).trim()
                : line.substring(40).trim();

        return new Pizza(type, id, crust, size);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", crust='" + crust + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}