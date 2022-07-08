package Interfaces.Repository;

import Model.Subscription;
import Model.SubscriptionPackage;

import java.util.List;

public interface ISubscriptionPackagesRepository {
    List<SubscriptionPackage> findAll();
    SubscriptionPackage findById(int id);
}
