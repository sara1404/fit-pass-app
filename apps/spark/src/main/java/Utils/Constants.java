package Utils;

public class Constants {


    public enum Sex { MALE, FEMALE }
    public enum UserRole { ADMIN, MANAGER, COACH, BUYER }
    public enum BuyerTypeName { GOLD, SILVER, BRONZE }
    public enum SportObjectType { GYM, POOL, SPORT_CENTER, DANCE_STUDIO }
    public enum SportObjectOffer { GROUP_TRAINING, PERSONAL_TRAINING, SAUNA }
    public enum SportObjectStatus { OPEN, CLOSED }
    public enum TrainingType {GROUP_TRAINING, PERSONAL_TRAINING, GYM}
    public enum SubscriptionType {YEARLY, MONTHLY}
    public enum SubscriptionStatus {ACTIVE, INACTIVE}
    public static String usersPath = "./src/main/resources/storage/users.txt";
    public static String sportObjectPath = "./src/main/resources/storage/sportObjects.txt";
    public static String commentsPath = "./src/main/resources/storage/comments.txt";
    public static String promoCodesPath = "./src/main/resources/storage/promoCodes.txt";
    public static String subscriptionPath = "./src/main/resources/storage/subscriptions.txt";
    public static String subscriptionPackagesPath = "./src/main/resources/storage/subscriptionPackages.txt";
}
