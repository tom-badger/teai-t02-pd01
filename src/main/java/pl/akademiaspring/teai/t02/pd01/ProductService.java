package pl.akademiaspring.teai.t02.pd01;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {

    private static final BigDecimal MINIMAL_PRICE = new BigDecimal(50);
    private static final BigDecimal MAXIMUM_PRICE = new BigDecimal(300);

    public static BigDecimal generateRandomPrice() {
        BigDecimal randomPrice = MINIMAL_PRICE.add(BigDecimal.valueOf(Math.random()).multiply(MAXIMUM_PRICE.subtract(MINIMAL_PRICE)));
        return randomPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }


}
