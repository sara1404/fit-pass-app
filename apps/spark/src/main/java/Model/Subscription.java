package Model;

import Utils.Constants;

import java.time.LocalDate;

public class Subscription {
    private String id;
    private Constants.SubscriptionType type;
    private LocalDate paymentDate;
    private LocalDate expirationDate;
    private double price;
    private String buyer;
    private Constants.SubscriptionStatus status;
    private int allowedEntersPerDay;
    private PromoCode promoCode;

    public Subscription(String id, Constants.SubscriptionType type, LocalDate paymentDate, LocalDate expirationDate, double price, String buyer, Constants.SubscriptionStatus status, int allowedEntersPerDay
    , PromoCode promoCode) {
        this.id = id;
        this.type = type;
        this.paymentDate = paymentDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.buyer = buyer;
        this.status = status;
        this.allowedEntersPerDay = allowedEntersPerDay;
        this.promoCode = promoCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Constants.SubscriptionType getType() {
        return type;
    }

    public void setType(Constants.SubscriptionType type) {
        this.type = type;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Constants.SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(Constants.SubscriptionStatus status) {
        this.status = status;
    }

    public int getAllowedEntersPerDay() {
        return allowedEntersPerDay;
    }

    public void setAllowedEntersPerDay(int allowedEntersPerDay) {
        this.allowedEntersPerDay = allowedEntersPerDay;
    }

    public PromoCode getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCode promoCode) {
        this.promoCode = promoCode;
    }


}
