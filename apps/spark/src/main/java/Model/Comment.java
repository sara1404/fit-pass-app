package Model;

public class Comment {
    private Buyer buyer;
    private SportObject object;
    private String comment;
    private int grade;

    public Comment(Buyer buyer, SportObject object, String comment, int grade) {
        this.buyer = buyer;
        this.object = object;
        this.comment = comment;
        this.grade = grade;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
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
}
