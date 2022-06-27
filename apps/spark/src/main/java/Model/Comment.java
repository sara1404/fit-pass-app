package Model;

public class Comment {
    private int id;
    private String buyerUsername;
    private SportObject object;
    private String comment;
    private int grade;

    public Comment(String buyerUsername, SportObject object, String comment, int grade) {
        this.buyerUsername = buyerUsername;
        this.object = object;
        this.comment = comment;
        this.grade = grade;
    }

    public SportObject getObject() {
        return object;
    }

    public void setObject(SportObject object) {
        this.object = object;
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

    public void update(Comment comment) {
        setComment(comment.getComment());
        setGrade(comment.getGrade());
    }
}
