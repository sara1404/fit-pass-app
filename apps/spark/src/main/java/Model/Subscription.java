package Model;

import Utils.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Subscription {
    private String id;
    private Constants.SubscriptionType type;
    private LocalDate paymentDate;
    private LocalDateTime expirationDate;
    private double price;
    private Buyer buyer;
    private Constants.SubscriptionStatus status;
    private int allowedEntersPerDay;

    public Subscription(String id, Constants.SubscriptionType type, LocalDate paymentDate, LocalDateTime expirationDate, double price, Buyer buyer, Constants.SubscriptionStatus status, int allowedEntersPerDay) {
        this.id = id;
        this.type = type;
        this.paymentDate = paymentDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.buyer = buyer;
        this.status = status;
        this.allowedEntersPerDay = allowedEntersPerDay;
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

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
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
}
