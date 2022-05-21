package Main;

import Controller.UserController;

import static spark.Spark.*;


public class Application {


    public static void main(String []args) {

        initializeServices();
        get("/", UserController::getOne);
    }


    private static void initializeServices() {

    }

}
