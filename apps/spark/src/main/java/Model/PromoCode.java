package Model;

import java.time.LocalDate;

public class PromoCode {
    private String id;
    private LocalDate validFrom;
    private LocalDate validTo;
    private int timesToUse;
    private int salePercentage;

    public PromoCode(String id, LocalDate validFrom, LocalDate validTo, int timesToUse, int salePercentage) {
        this.id = id;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.timesToUse = timesToUse;
        this.salePercentage = salePercentage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public int getTimesToUse() {
        return timesToUse;
    }

    public void setTimesToUse(int timesToUse) {
        this.timesToUse = timesToUse;
    }

    public int getSalePercentage() {
        return salePercentage;
    }

    public void setSalePercentage(int salePercentage) {
        this.salePercentage = salePercentage;
    }

    public boolean isExpired(LocalDate dateOfUsage){
        if(validTo.isBefore(dateOfUsage) && validFrom.isAfter(dateOfUsage))
            return true;
        return false;
    }
}
