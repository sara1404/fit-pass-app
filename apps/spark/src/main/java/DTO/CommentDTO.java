package DTO;

import Model.Comment;
import Model.SportObject;

import java.util.List;

public class CommentDTO {
    public Comment comment;
    public SportObject sportObject;

    public CommentDTO(Comment comment, SportObject sportObject) {
        this.comment = comment;
        this.sportObject = new SportObject();
        this.sportObject.setName(sportObject.getName());
        this.sportObject.setType(sportObject.getType());
        this.sportObject.setLocation(sportObject.getLocation());
    }
}
