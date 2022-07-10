package Repository;

import DataHandler.TemplateDataHandler;
import Interfaces.Repository.ISubscriptionPackagesRepository;
import Model.PromoCode;
import Model.Subscription;
import Model.SubscriptionPackage;
import Utils.Constants;

import java.util.List;

public class SubscriptionPackagesRepository implements ISubscriptionPackagesRepository {
    List<SubscriptionPackage> subscriptionPackages;
    TemplateDataHandler<SubscriptionPackage> subscriptionPackagesDataHandler;

    public SubscriptionPackagesRepository(TemplateDataHandler<SubscriptionPackage> subscriptionPackagesDataHandler){
        this.subscriptionPackagesDataHandler = subscriptionPackagesDataHandler;
        this.subscriptionPackages = subscriptionPackagesDataHandler.readFromFile();
    }
    @Override
    public List<SubscriptionPackage> findAll() {
        return subscriptionPackages;
    }

    @Override
    public SubscriptionPackage findById(int id) {
        return subscriptionPackages.stream()
                .filter(pack -> pack.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public SubscriptionPackage findByDuration(Constants.SubscriptionType type) {
        return subscriptionPackages.stream()
                .filter(pack -> pack.getType() == type)
                .findAny()
                .orElse(null);
    }

}
