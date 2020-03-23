package pl.akademiaspring.teai.t02.pd01;

import java.math.BigDecimal;
import java.util.*;

public class Basket {

    private Map<UUID, BasketItem> basket = new LinkedHashMap<>();

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public Optional<BasketItem> addProduct(Product product, int quantity) {
        BasketItem basketItem = null;
        if (product != null && quantity > 0) {
            if (basket.containsKey(product.getId())) {
                basketItem = basket.get(product.getId()).changeQuantityBy(quantity);
            } else {
                basketItem = new BasketItem(product, quantity);
                basket.put(product.getId(), basketItem);
            }
            basketItem = basketItem.copy();
        }
        return Optional.ofNullable(basketItem);
    }

    public BigDecimal calculateNettTotalValue() {
        BigDecimal total = new BigDecimal(0);
        for (BasketItem basketItem : basket.values()) {
            total = total.add(basketItem.getProduct().getPrice().multiply(new BigDecimal(basketItem.getQuantity())));
        }
        return total;
    }

    public List<BasketItem> getBasketItemList() {
        return new ArrayList<>(basket.values());
    }

    public void removeProduct(Product product) {
        if (product != null) {
            basket.remove(product.getId());
        }
    }

    private Optional<BasketItem> updateProductQuantity(Product product, int quantity) {
        BasketItem basketItem = null;
        if (product != null && basket.containsKey(product.getId())) {
            if (quantity > 1) {
                basketItem = basket.get(product.getId()).setQuantity(quantity);
                basketItem = basketItem.copy();
            } else {
                basket.remove(product.getId());
                basketItem = new BasketItem(product, 0);
            }
        }
        return Optional.ofNullable(basketItem);
    }
}
