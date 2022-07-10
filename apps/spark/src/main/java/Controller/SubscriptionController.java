package Controller;

import Model.PromoCode;
import Model.Subscription;
import Model.SubscriptionPackage;
import Service.SubscriptionService;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionController extends Controller{
    private static SubscriptionService subscriptionService;

    public static void initContext(SubscriptionService subService) {
        subscriptionService = subService;
    }

    public static String generatePromoCode(Request request, Response response) throws Exception{
        PromoCode promoCode = gson.fromJson(request.body(), PromoCode.class);
        subscriptionService.generatePromoCode(promoCode);
        return successResponse();
    }

    public static String getAllForBuyer(Request request, Response response) {
        String username = request.attribute("username");
        List<Subscription> subs = subscriptionService.getAllByBuyer(username);
        Collections.reverse(subs);
        return gson.toJson(subs);
    }

    public static String getAllPackages(Request request, Response response){
        List<SubscriptionPackage> subscriptions = subscriptionService.getAllPackages();
        return gson.toJson(subscriptions);
    }

    public static String createSubscription(Request request, Response response){
        String buyerUsername = request.attribute("username");
        SubscriptionPackage subscription = gson.fromJson(request.body(), SubscriptionPackage.class);
        subscriptionService.createSubscription(subscription, buyerUsername);
        return successResponse();
    }

    public static void checkSubscriptionStatus(Request request, Response response) throws Exception {
        Subscription subscription = subscriptionService.findByBuyer(request.attribute("username"));
        if(subscription == null) {
            halt(403, "You dont have active subscription to access!");
        }
    }
}
