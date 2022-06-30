package Model;

import Utils.Constants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Subscription {
    private int id;
    private Constants.SubscriptionType type;
    private LocalDate paymentDate;
    private LocalDate expirationDate;
    private double price;
    private String buyer;
    private Constants.SubscriptionStatus status;
    private int allowedEntersPerDay;
    private PromoCode promoCode;
    private List<TrainingSubscription> additionalSubs;

    public Subscription(int id, Constants.SubscriptionType type, LocalDate paymentDate, LocalDate expirationDate, double price, String buyer, Constants.SubscriptionStatus status, int allowedEntersPerDay
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
        this.additionalSubs = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<TrainingSubscription> getAdditionalSubs() {
        return additionalSubs;
    }

    public void setAdditionalSubs(List<TrainingSubscription> additionalSubs) {
        this.additionalSubs = additionalSubs;
    }

    public void update(Subscription subscription) {
        setAllowedEntersPerDay(subscription.allowedEntersPerDay);
        setStatus(subscription.status);
        setExpirationDate(subscription.expirationDate);
        setAdditionalSubs(subscription.additionalSubs);
    }

    public void addAdditionalSub(TrainingSubscription subscription) {
        for(TrainingSubscription sub : additionalSubs) {
            if(sub.getObjectId() == subscription.getObjectId() && sub.getContentName().equals(subscription.getContentName())) {
                sub.setAppointmentsLeft(sub.getAppointmentsLeft() + subscription.getAppointmentsLeft());
                return;
            }
        }
        additionalSubs.add(subscription);
    }

    public void checkIn(String contentName, int sportObjectId) throws Exception {
        for(TrainingSubscription sub : additionalSubs) {
            if(sub.getObjectId() == sportObjectId && sub.getContentName().equals(contentName)) {
                if(sub.getAppointmentsLeft() == 0) throw new Exception("Number of appointments left is 0!");
                sub.setAppointmentsLeft(sub.getAppointmentsLeft() - 1);
                return;
            }
        }
        throw new Exception("There is no training with this name!");
    }
}
