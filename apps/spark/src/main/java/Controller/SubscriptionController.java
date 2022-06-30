package Controller;

import Model.PromoCode;
import Model.Subscription;
import Service.SubscriptionService;
import Utils.Constants;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

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

    public static String createSubscription(Request request, Response response){
        String buyerUsername = request.attribute("username");
        Subscription subscription = gson.fromJson(request.body(), Subscription.class);
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
