package pl.akademiaspring.teai.t02.pd01;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Shop {

    private ShopProperties shopProperties;

    public Shop(ShopProperties shopProperties) {
        this.shopProperties = shopProperties;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {

        Product product1 = new Product("Audio-Technica ATH-SR50BT", ProductService.generateRandomPrice());
        Product product2 = new Product("Logitech MX Keys", ProductService.generateRandomPrice());
        Product product3 = new Product("Silicon Power P34A80 256GB", ProductService.generateRandomPrice());
        Product product4 = new Product("Lexmark B2236dw", ProductService.generateRandomPrice());
        Product product5 = new Product("TP-Link Archer C6", ProductService.generateRandomPrice());

        Basket basket = new Basket();
        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);

        System.out.println(String.format("\n\n| %-33s | %-8s |", "Product", "Price"));
        System.out.println("------------------------------------------------");
        for (BasketItem basketItem : basket.getBasketItemList()) {
            System.out.println(String.format("| %-33s | %8s |", basketItem.getProduct().getName(), basketItem.getProduct().getPrice()));
        }
        System.out.println("------------------------------------------------");

        BigDecimal nettTotal = basket.calculateNettTotalValue();
        BigDecimal afterRebate = nettTotal;

        System.out.println(String.format(" %34s | %8s |", "Nett total", nettTotal));

        if ("PRO".equals(shopProperties.getVersion())) {
            afterRebate = nettTotal.multiply(BigDecimal.valueOf(1.0F - Float.valueOf(shopProperties.getRebate()) / 100))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println(String.format(" %34s | %8s |", "After rebate (-" + shopProperties.getRebate() + "%)", afterRebate));
        }

        if ("PLUS".equals(shopProperties.getVersion()) || "PRO".equals(shopProperties.getVersion())) {
            BigDecimal salesTax = afterRebate.multiply(BigDecimal.valueOf(shopProperties.getTax()))
                    .divide(BigDecimal.valueOf(100))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println(String.format(" %34s | %8s |", "Sales tax (" + shopProperties.getTax() + "%)", salesTax));
            System.out.println("                                    ------------");
            System.out.println(String.format(" %34s | %8s |", "TOTAL", afterRebate.add(salesTax)));
        }

        System.out.println("                                    ------------\n\n");
    }
}
