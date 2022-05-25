package DTO.profile;

import Model.Manager;
import Model.SportObject;

public class ManagerProfileDTO extends UserProfileDTO{
    SportObject sportObject;
    public ManagerProfileDTO(Manager manager) {
        super(manager);
        this.sportObject = manager.getSportObject();
    }
}
