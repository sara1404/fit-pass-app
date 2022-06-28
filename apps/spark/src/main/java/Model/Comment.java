package Model;

public class Comment {

    private int id;
    private String buyerUsername;
    private int sportObjectId;
    private String comment;
    private int grade;
    private boolean approved;

    public Comment(String buyerUsername, int objectId, String comment, int grade) {
        this.buyerUsername = buyerUsername;
        this.sportObjectId = objectId;
        this.comment = comment;
        this.grade = grade;
        this.approved = false;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public void setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getSportObjectId() {
        return sportObjectId;
    }

    public void setSportObjectId(int sportObjectId) {
        this.sportObjectId = sportObjectId;
    }

    public void update(Comment comment) {
        setComment(comment.getComment());
        setGrade(comment.getGrade());
    }

    public boolean isValidGrade() {
        return this.grade >= 1 && this.grade <= 5;
    }
}
