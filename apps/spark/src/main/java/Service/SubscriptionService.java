package Service;

import Interfaces.Repository.IPromoCodeRepository;
import Interfaces.Repository.ISubscriptionRepository;
import Model.PromoCode;
import Model.Subscription;
import Utils.Constants;

import java.time.LocalDate;

public class SubscriptionService {

    private IPromoCodeRepository promoCodeRepository;
    private ISubscriptionRepository subscriptionRepository;

    public SubscriptionService(IPromoCodeRepository promoCodeRepository, ISubscriptionRepository subscriptionRepository){
        this.promoCodeRepository = promoCodeRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public void generatePromoCode(PromoCode promoCode) throws Exception{
        promoCodeRepository.addPromoCode(promoCode);
    }

    public void createSubscription(Subscription subscription, String buyerUsername){
        subscription.setBuyer(buyerUsername);
        subscription.setStatus(Constants.SubscriptionStatus.ACTIVE);
        subscription.setPaymentDate(LocalDate.now());
        subscription.setExpirationDate(LocalDate.now().plusDays(30));
        if(isValidPromoCode(subscription)) {
            calculateSubscriptionPrice(subscription);
        }
        deactivateOldSubscription(subscription);
        subscriptionRepository.create(subscription);
    }

    private void calculateSubscriptionPrice(Subscription subscription){
        subscription.setPrice(subscription.getPrice() - subscription.getPromoCode().getSalePercentage()*(subscription.getPrice()/100));
    }

    private void deactivateOldSubscription(Subscription subscription){
        Subscription oldSubscription = subscriptionRepository.findByBuyer(subscription.getBuyer());
        if(oldSubscription != null)
            oldSubscription.setStatus(Constants.SubscriptionStatus.INACTIVE);
    }

    private boolean promoCodeExists(Subscription subscription) {
        return promoCodeRepository.findById(subscription.getId()) != null;
    }

    private boolean isValidPromoCode(Subscription subscription) {
        return subscription.getPromoCode() != null && promoCodeExists(subscription)
                && subscription.getPromoCode().isExpired(LocalDate.now());
    }

}
