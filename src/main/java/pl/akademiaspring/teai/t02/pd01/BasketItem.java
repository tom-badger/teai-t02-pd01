package pl.akademiaspring.teai.t02.pd01;

public class BasketItem {

    private Product product;
    private int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = Math.max(quantity, 0);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BasketItem setQuantity(int quantity) {
        this.quantity = Math.max(quantity, 0);
        return this;
    }

    public BasketItem changeQuantityBy(int amount) {
        quantity = Math.max(quantity + amount, 0);
        return this;
    }

    public BasketItem copy() {
        return new BasketItem(this.product, this.quantity);
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
