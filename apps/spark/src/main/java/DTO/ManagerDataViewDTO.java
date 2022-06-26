package DTO;

import DTO.profile.CoachProfileDTO;
import DTO.profile.UserProfileDTO;
import Model.SportObject;

import java.util.List;

public class ManagerDataViewDTO {
    public SportObject sportObject;
    public List<UserProfileDTO> coaches;
    public List<UserProfileDTO> usersVisited;

    public ManagerDataViewDTO(SportObject sportObject, List<UserProfileDTO> coaches, List<UserProfileDTO> usersVisited) {
        this.sportObject = sportObject;
        this.coaches = coaches;
        this.usersVisited = usersVisited;
    }

}
