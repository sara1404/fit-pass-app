package Repository;

import DataHandler.TemplateDataHandler;
import Interfaces.Repository.ISubscriptionPackagesRepository;
import Model.Subscription;

import java.util.List;

public class SubscriptionPackagesRepository implements ISubscriptionPackagesRepository {
    List<Subscription> subscriptionPackages;
    TemplateDataHandler<Subscription> subscriptionPackagesDataHandler;

    public SubscriptionPackagesRepository(TemplateDataHandler<Subscription> subscriptionPackagesDataHandler){
        this.subscriptionPackagesDataHandler = subscriptionPackagesDataHandler;
        this.subscriptionPackages = subscriptionPackagesDataHandler.readFromFile();
    }
    @Override
    public List<Subscription> findAll() {
        return subscriptionPackages;
    }
}
