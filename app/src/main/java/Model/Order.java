package Model;

import java.util.ArrayList;

import Model.Food;

public class Order {

    private String orderId;
    private String orderName;
    private String quantity;
    private String price;

    public Order() {
    }

    public Order(String orderId, String orderName, /*String quantity,*/ String price) {
        this.orderId = orderId;
        this.orderName = orderName;
     //   this.quantity = quantity;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

 /*   public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }*/

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
