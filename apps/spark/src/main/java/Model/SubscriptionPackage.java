package Model;

import Utils.Constants;

public class SubscriptionPackage {
    private int id;
    private Constants.SubscriptionType type;
    private double price;
    private int allowedEntersPerDay;
    private PromoCode promoCode;
    private int duration;

    public SubscriptionPackage(int id, Constants.SubscriptionType type, double price, int allowedEntersPerDay, PromoCode promoCode, int duration) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.allowedEntersPerDay = allowedEntersPerDay;
        this.promoCode = promoCode;
        this.duration = duration;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void loadImportantData(SubscriptionPackage pack) {
        this.price = pack.getPrice();
        this.duration = pack.getDuration();
        this.allowedEntersPerDay = pack.getAllowedEntersPerDay();
        this.type = pack.getType();
    }
}
