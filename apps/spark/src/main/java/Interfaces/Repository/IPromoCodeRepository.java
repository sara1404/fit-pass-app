package Interfaces.Repository;

import Model.PromoCode;

public interface IPromoCodeRepository {
    void addPromoCode(PromoCode promoCode) throws Exception;
    PromoCode findById(String id);
}
