package Repository;

import DataHandler.TemplateDataHandler;
import Interfaces.Repository.ISubscriptionRepository;
import Model.PromoCode;
import Model.Subscription;
import Utils.Constants;

import java.util.List;

public class SubscriptionRepository implements ISubscriptionRepository {

    private List<Subscription> subscriptions;
    private TemplateDataHandler<Subscription> subscriptionDataHandler;
    public SubscriptionRepository(TemplateDataHandler<Subscription> subscriptionDataHandler){
        this.subscriptionDataHandler = subscriptionDataHandler;
        this.subscriptions = subscriptionDataHandler.readFromFile();
        System.out.println(subscriptions);
    }

    @Override
    public void create(Subscription subscription) {
        subscription.setId(generateId());
        subscriptions.add(subscription);
        subscriptionDataHandler.writeToFile(subscriptions);
    }

    @Override
    public List<Subscription> findAll() {
        return subscriptions;
    }

    @Override
    public Subscription findByBuyer(String buyerId) {
        for (Subscription subscription : subscriptions){
            if(subscription.getBuyer().equals(buyerId) && subscription.getStatus() == Constants.SubscriptionStatus.ACTIVE){
                return subscription;
            }
        }
        return null;
    }

    private String generateId(){
        return "SUBSCRIPTION" + subscriptions.size();
    }
}
