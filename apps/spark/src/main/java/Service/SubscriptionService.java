package Service;

import Interfaces.Repository.IPromoCodeRepository;
import Interfaces.Repository.ISubscriptionPackagesRepository;
import Interfaces.Repository.ISubscriptionRepository;
import Interfaces.Repository.IUserRepository;
import Model.*;
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

    public List<SubscriptionPackage> getAll(){
        return subscriptionPackagesRepository.findAll();
    }

    public void createSubscription(SubscriptionPackage subscriptionPackage, String buyerUsername) {
        PromoCode promoCode = promoCodeRepository.findById(subscriptionPackage.getPromoCode().getId());
        subscriptionPackage.loadImportantData(subscriptionPackagesRepository.findById(subscriptionPackage.getId()));
        if(isValidPromoCode(promoCode)) {
            promoCode.setTimesToUse(promoCode.getTimesToUse()-1);
            promoCodeRepository.update(promoCode);
        } else {
            promoCode = null;
        }
        setBuyerType(buyerUsername);
        calculateBuyerPoints(buyerUsername);
        deactivateOldSubscription(buyerUsername);
        Subscription subscription = new Subscription(-1,subscriptionPackage.getType(), LocalDate.now(),
                LocalDate.now().plusDays(subscriptionPackage.getDuration()), subscriptionPackage.getPrice(),
                buyerUsername, Constants.SubscriptionStatus.ACTIVE, subscriptionPackage.getAllowedEntersPerDay(), promoCode);
        calculateSubscriptionPrice(subscription, buyerUsername);
        subscriptionRepository.create(subscription);

    }

    public Subscription findByBuyer(String buyerUsername) throws Exception {
        Subscription subscription = subscriptionRepository.findByBuyer(buyerUsername);
        if(subscription == null) throw new Exception("Subscription does not exist");
        return subscription;
    }

    private void calculateSubscriptionPrice(Subscription subscription, String buyerUsername ){
        Buyer buyer = (Buyer) userRepository.findByUsername(buyerUsername);
        int salePercentage = subscription.getPromoCode() == null ? 0 : subscription.getPromoCode().getSalePercentage();
        double buyerTypeDiscount = buyer.getBuyerType() == null ? 0 : buyer.getBuyerType().getDiscount();
        subscription.setPrice(subscription.getPrice() - (salePercentage + buyerTypeDiscount)*(subscription.getPrice()/100));
    }

    private void deactivateOldSubscription(String buyerUsername){
        Subscription oldSubscription = subscriptionRepository.findByBuyer(buyerUsername);
        if(oldSubscription != null)
            oldSubscription.setStatus(Constants.SubscriptionStatus.INACTIVE);
    }

    private boolean isValidPromoCode(PromoCode promoCode) {
        return promoCode != null
                && !promoCode.isExpired(LocalDate.now())
                && promoCode.getTimesToUse() > 0;
    }

    public void calculateBuyerPoints(String buyerUsername){
        Buyer buyer = (Buyer) userRepository.findByUsername(buyerUsername);
        Subscription oldSubscription = subscriptionRepository.findByBuyer(buyerUsername);
        double newPoints = determineNewPointsByEnters(oldSubscription, buyer);
        buyer.setPoints(newPoints);
        if(buyer.getPoints() < 0) buyer.setPoints(0);
        userRepository.update(buyer);
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
        userRepository.update(buyer);
    }

    private void setBuyerTypeInfo(Buyer buyer, Constants.BuyerTypeName type, double discount){
        buyer.getBuyerType().setName(type);
        buyer.getBuyerType().setDiscount(discount);
    }

    private double determineNewPointsByEnters(Subscription oldSubscription, Buyer buyer) {
        if(oldSubscription.getNumOfUsedEnters() < oldSubscription.getAllowedEntersPerDay()*30/3) {
            return buyer.getPoints()-(oldSubscription.getPrice()/1000*133*4);
        }
        return oldSubscription.getPrice()/1000*oldSubscription.getNumOfUsedEnters();
    }
}
