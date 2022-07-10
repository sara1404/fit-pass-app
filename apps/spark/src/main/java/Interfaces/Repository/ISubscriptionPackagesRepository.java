package Interfaces.Repository;

import Model.Subscription;
import Model.SubscriptionPackage;
import Utils.Constants;

import java.util.List;

public interface ISubscriptionPackagesRepository {
    List<SubscriptionPackage> findAll();
    SubscriptionPackage findById(int id);
    SubscriptionPackage findByDuration(Constants.SubscriptionType type);
}
