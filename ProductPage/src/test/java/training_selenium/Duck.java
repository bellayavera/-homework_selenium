package training_selenium;

import java.util.Objects;

public class Duck {
    private String name;
    private String price;
    private String discountPrice;


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


    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duck duck = (Duck) o;
        return Objects.equals(name, duck.name) && Objects.equals(price, duck.price) && Objects.equals(discountPrice, duck.discountPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, discountPrice);
    }
}
