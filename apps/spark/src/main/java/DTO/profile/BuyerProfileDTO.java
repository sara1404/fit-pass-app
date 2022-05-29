package DTO.profile;

import Model.Buyer;
import Model.BuyerType;

public class BuyerProfileDTO extends UserProfileDTO {

    public BuyerType buyerType;
    public int points;

    protected BuyerProfileDTO(Buyer buyer) {
        super(buyer);
        this.buyerType = buyer.getBuyerType();
        this.points = buyer.getPoints();
    }
}
