package Interfaces.Repository;

import Model.Subscription;

import java.util.List;

public interface ISubscriptionPackagesRepository {
    List<Subscription> findAll();
}
