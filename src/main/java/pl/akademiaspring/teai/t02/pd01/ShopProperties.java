package pl.akademiaspring.teai.t02.pd01;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "shop")
public class ShopProperties {

    private String version = "BASIC";
    private Integer tax = 0;
    private Integer rebate = 0;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getRebate() {
        return rebate;
    }

    public void setRebate(Integer rebate) {
        this.rebate = rebate;
    }
}
