package Repository;

import DataHandler.TemplateDataHandler;
import Interfaces.Repository.ISubscriptionRepository;
import Model.PromoCode;

import java.util.List;

public class PromoCodesRepository implements ISubscriptionRepository {

    private List<PromoCode> promoCodes;
    private TemplateDataHandler<PromoCode> subscriptionDataHandler;


    public PromoCodesRepository(TemplateDataHandler<PromoCode> subscriptionDataHandler){
        this.subscriptionDataHandler = subscriptionDataHandler;
        this.promoCodes = subscriptionDataHandler.readFromFile();
    }

    @Override
    public void addPromoCode(PromoCode promoCode) throws Exception{
        if(doesExist(promoCode))
            throw new Exception("Promo code with this id already exists!");
        promoCodes.add(promoCode);
        subscriptionDataHandler.writeToFile(promoCodes);
    }

    public boolean doesExist(PromoCode promoCode){
        for (PromoCode code: promoCodes){
            if(code.getId().equals(promoCode.getId()))
                return true;
        }
        return false;
    }
}
