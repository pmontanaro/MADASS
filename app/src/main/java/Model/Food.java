package Model;

public class Food {

    private String name;
    private String image;
    private String description;
    private String price;
    private String MenuID;

    public Food() {
    }

    public Food(String name, String image, String description, String price, String menuID) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        MenuID = menuID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String menuID) {
        MenuID = menuID;
    }
}
