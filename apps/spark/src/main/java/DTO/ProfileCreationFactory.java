package DTO;

import Interfaces.IProfileDTO;
import Model.Buyer;
import Model.User;

public class ProfileCreationFactory {


    public static IProfileDTO createProfileDTO(User profile) {
        if(profile instanceof Buyer) {
            return new BuyerProfileDTO((Buyer) profile);
        }

        return new UserProfileDTO(profile);
    }

}
