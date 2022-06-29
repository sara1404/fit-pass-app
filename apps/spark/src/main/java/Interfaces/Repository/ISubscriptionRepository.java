package Interfaces.Repository;

import Model.PromoCode;

public interface ISubscriptionRepository {
    void addPromoCode(PromoCode promoCode) throws Exception;
}
