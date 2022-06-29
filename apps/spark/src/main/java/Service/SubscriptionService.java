package Service;

import Interfaces.Repository.ISubscriptionRepository;
import Model.PromoCode;

public class SubscriptionService {

    private ISubscriptionRepository subscriptionRepository;

    public SubscriptionService(ISubscriptionRepository subscriptionRepository){
        this.subscriptionRepository = subscriptionRepository;
    }

    public void generatePromoCode(PromoCode promoCode) throws Exception{
        subscriptionRepository.addPromoCode(promoCode);
    }
}
