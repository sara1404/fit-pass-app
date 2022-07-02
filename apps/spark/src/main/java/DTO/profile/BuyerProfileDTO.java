package DTO.profile;

import Model.Buyer;
import Model.BuyerType;
import Model.SportObject;

import java.util.ArrayList;
import java.util.List;

public class BuyerProfileDTO extends UserProfileDTO {

    public BuyerType buyerType;
    public double points;
    public List<SportObject> visitedObjects;

    protected BuyerProfileDTO(Buyer buyer) {
        super(buyer);
        this.buyerType = buyer.getBuyerType();
        this.points = buyer.getPoints();
        this.visitedObjects = buyer.getVisitedObjects();
    }
}
