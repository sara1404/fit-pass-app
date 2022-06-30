package Service;

import Interfaces.Repository.IPromoCodeRepository;
import Interfaces.Repository.ISubscriptionPackagesRepository;
import Interfaces.Repository.ISubscriptionRepository;
import Interfaces.Repository.IUserRepository;
import Model.Buyer;
import Model.PromoCode;
import Model.Subscription;
import Utils.Constants;

import java.time.LocalDate;
import java.util.List;

public class SubscriptionService {

    private IPromoCodeRepository promoCodeRepository;
    private ISubscriptionRepository subscriptionRepository;
    private ISubscriptionPackagesRepository subscriptionPackagesRepository;
    private IUserRepository userRepository;

    public SubscriptionService(IPromoCodeRepository promoCodeRepository, ISubscriptionRepository subscriptionRepository, IUserRepository userRepository, ISubscriptionPackagesRepository subscriptionPackagesRepository){
        this.promoCodeRepository = promoCodeRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.subscriptionPackagesRepository = subscriptionPackagesRepository;
    }

    public void generatePromoCode(PromoCode promoCode) throws Exception{
        promoCodeRepository.addPromoCode(promoCode);
    }

    public List<Subscription> getAll(){
        return subscriptionPackagesRepository.findAll();
    }

    public void createSubscription(Subscription subscription, String buyerUsername){
        PromoCode promoCode = promoCodeRepository.findById(subscription.getPromoCode().getId());
        subscription.setPromoCode(promoCode);
        subscription.setBuyer(buyerUsername);
        subscription.setStatus(Constants.SubscriptionStatus.ACTIVE);
        subscription.setPaymentDate(LocalDate.now());
        subscription.setExpirationDate(LocalDate.now().plusDays(30));
        if(isValidPromoCode(subscription)) {
            calculateSubscriptionPrice(subscription);
            subscription.getPromoCode().setTimesToUse(subscription.getPromoCode().getTimesToUse()-1);
        }
        deactivateOldSubscription(subscription);
        subscriptionRepository.create(subscription);
    }

    public Subscription findByBuyer(String buyerUsername) throws Exception {
        Subscription subscription = subscriptionRepository.findByBuyer(buyerUsername);
        if(subscription == null) throw new Exception("Subscription does not exist");
        return subscription;
    }

    private void calculateSubscriptionPrice(Subscription subscription){
        Buyer buyer = (Buyer) userRepository.findByUsername(subscription.getBuyer());
        subscription.setPrice(subscription.getPrice() - (subscription.getPromoCode().getSalePercentage() + buyer.getBuyerType().getDiscount())*(subscription.getPrice()/100));
    }

    private void deactivateOldSubscription(Subscription subscription){
        Subscription oldSubscription = subscriptionRepository.findByBuyer(subscription.getBuyer());
        if(oldSubscription != null)
            oldSubscription.setStatus(Constants.SubscriptionStatus.INACTIVE);
    }

    private boolean promoCodeExists(Subscription subscription) {
        return promoCodeRepository.findById(subscription.getPromoCode().getId()) != null;
    }

    private boolean isValidPromoCode(Subscription subscription) {
        return subscription.getPromoCode() != null && promoCodeExists(subscription)
                && subscription.getPromoCode().isExpired(LocalDate.now())
                && subscription.getPromoCode().getTimesToUse() > 0;
    }

    public void calculateBuyerPoints(String buyerUsername){
        Buyer buyer = (Buyer) userRepository.findByUsername(buyerUsername);
        Subscription oldSubscription = subscriptionRepository.findByBuyer(buyerUsername);
        if(oldSubscription.getNumOfUsedEnters() < oldSubscription.getAllowedEntersPerDay()*30/3){
            buyer.setPoints(buyer.getPoints()-(oldSubscription.getPrice()/1000*133*4));
            return;
        }
        buyer.setPoints(oldSubscription.getPrice()/1000*oldSubscription.getNumOfUsedEnters());
    }

    public void setBuyerType(String buyerUsername){
        Buyer buyer = (Buyer) userRepository.findByUsername(buyerUsername);
        if(buyer.getPoints() >= 4000){
            setBuyerTypeInfo(buyer, Constants.BuyerTypeName.GOLD, 5);
        }
        else if(buyer.getPoints() >= 3000 && buyer.getPoints() < 4000){
            setBuyerTypeInfo(buyer, Constants.BuyerTypeName.SILVER, 3);
        }
        else{
            setBuyerTypeInfo(buyer, Constants.BuyerTypeName.BRONZE, 0);
        }
    }

    private void setBuyerTypeInfo(Buyer buyer, Constants.BuyerTypeName type, double discount){
        buyer.getBuyerType().setName(type);
        buyer.getBuyerType().setDiscount(discount);
    }
}
