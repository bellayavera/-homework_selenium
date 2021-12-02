package training_selenium;

import java.util.Objects;

public class Duck {
    private String name;
    private String price;
    private String discountPrice;
    private String lineThroughPrice;
    private String lineThroughDiscountPrice;
    private String weightPrice;
    private String weightDiscountPrice;


    public Duck() {
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public String getLineThroughDiscountPrice() {
        return lineThroughDiscountPrice;
    }

    public String getLineThroughPrice() {
        return lineThroughPrice;
    }

    public String getWeightDiscountPrice() {
        return weightDiscountPrice;
    }

    public String getWeightPrice() {
        return weightPrice;
    }



    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setLineThroughDiscountPrice(String lineThroughDiscountPrice) {
        this.lineThroughDiscountPrice = lineThroughDiscountPrice;
    }

    public void setLineThroughPrice(String lineThroughPrice) {
        this.lineThroughPrice = lineThroughPrice;
    }

    public void setWeightDiscountPrice(String weightDiscountPrice) {
        this.weightDiscountPrice = weightDiscountPrice;
    }

    public void setWeightPrice(String weightPrice) {
        this.weightPrice = weightPrice;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duck duck = (Duck) o;
        return Objects.equals(name, duck.name) && Objects.equals(price, duck.price) && Objects.equals(discountPrice, duck.discountPrice) && Objects.equals(lineThroughPrice, duck.lineThroughPrice) && Objects.equals(lineThroughDiscountPrice, duck.lineThroughDiscountPrice) && Objects.equals(weightPrice, duck.weightPrice) && Objects.equals(weightDiscountPrice, duck.weightDiscountPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, discountPrice, lineThroughPrice, lineThroughDiscountPrice, weightPrice, weightDiscountPrice);
    }
}
