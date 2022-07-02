package Repository;

import DataHandler.TemplateDataHandler;
import Interfaces.Repository.ISubscriptionRepository;
import Model.PromoCode;
import Model.Subscription;
import Model.TrainingReservation;
import Utils.Constants;

import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionRepository implements ISubscriptionRepository {

    private List<Subscription> subscriptions;
    private TemplateDataHandler<Subscription> subscriptionDataHandler;
    public SubscriptionRepository(TemplateDataHandler<Subscription> subscriptionDataHandler){
        this.subscriptionDataHandler = subscriptionDataHandler;
        this.subscriptions = subscriptionDataHandler.readFromFile();
    }

    @Override
    public void create(Subscription subscription) {
        subscription.setId(generateId());
        subscriptions.add(subscription);
        subscriptionDataHandler.writeToFile(subscriptions);
    }

    @Override
    public void update(Subscription subscription) {
        Subscription sub = findById(subscription.getId());
        sub.update(subscription);
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

    @Override
    public Subscription findById(int id) {
        return subscriptions.stream()
                .filter(sub -> sub.getId() == id)
                .findAny()
                .orElse(null);
    }

    private int generateId() {
        int id = 0;
        List<Integer> ids = extractExistingIds();

        while(ids.contains(id))
            id++;
        return id;
    }

    private List<Integer> extractExistingIds() {
        return subscriptions.stream().map(Subscription::getId).collect(Collectors.toList());
    }
}
