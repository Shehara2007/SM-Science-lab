package lk.ijse.sciencelab.util;

import javafx.scene.control.Button;

public class cartTm {
    private String itemId;
    private String name;
    private String quantity;
    private Button button;

    public cartTm(String itemId, String name, String quantity, Button button) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.button = button;
    }

    public cartTm() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}