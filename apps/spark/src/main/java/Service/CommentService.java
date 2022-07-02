package Service;

import Interfaces.Repository.ICommentRepository;
import Interfaces.Repository.ISportObjectRepository;
import Model.Comment;
import Model.SportObject;

import java.util.List;
import java.util.stream.Collectors;

public class CommentService {

    private ICommentRepository commentRepository;
    private ISportObjectRepository sportObjectRepository;

    public CommentService(ICommentRepository commentRepository, ISportObjectRepository sportObjectRepository) {
        this.commentRepository = commentRepository;
        this.sportObjectRepository = sportObjectRepository;
    }

    public void create(Comment comment) throws Exception{
        checkIfCommentOnSportObjectExists(comment.getSportObjectId(), comment.getBuyerUsername());
        checkIfSportObjectExists(comment);
        checkIfGradeValid(comment);
        commentRepository.create(comment);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public void update(Comment comment) {
        commentRepository.update(comment);
    }

    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }

    public void approveComment(int commentId) throws Exception{
        Comment comment = commentRepository.findById(commentId);
        if(comment == null) throw new Exception("Comment doesn't exist!");
        comment.setApproved(true);
        commentRepository.update(comment);
        updateObjectAverageGrade(commentId);
    }

    public void declineComment(int commentId) throws Exception{
        Comment comment = commentRepository.findById(commentId);
        if(comment == null) throw new Exception("Comment doesn't exist!");
        comment.setApproved(false);
        commentRepository.update(comment);
    }

    public List<Comment> findAllBySportObjectId(int sportObjectId) {
        return commentRepository.findAllBySportObjectId(sportObjectId);
    }

    public List<Comment> findAllApprovedBySportObjectId(int sportObjectId) {
        return filterAllApproved(commentRepository.findAllBySportObjectId(sportObjectId));
    }

    public List<Comment> findAllByUsername(String username) {
        return commentRepository.findAllByUsername(username);
    }

    private List<Comment> filterAllApproved(List<Comment> comments) {
        return comments.stream()
                .filter(Comment::isApproved)
                .collect(Collectors.toList());
    }

    private void checkIfCommentOnSportObjectExists(int sportObjectId, String buyerUsername) throws Exception {
        List<Comment> comments = commentRepository.findAllBySportObjectId(sportObjectId);
        boolean commentExists = comments.stream()
                .anyMatch(comm -> comm.getBuyerUsername().equals(buyerUsername));
        if(commentExists) throw new Exception("User has already put comment on this object!");
    }

    private void checkIfSportObjectExists(Comment comment) throws Exception {
        SportObject sportObject = sportObjectRepository.findById(comment.getSportObjectId());
        if(sportObject == null) throw new Exception("Sport object is required!");
    }

    private void checkIfGradeValid(Comment comment) throws Exception {
        if(!comment.isValidGrade()) throw new Exception("Grade should be between 1 and 5!");
    }

    private void updateObjectAverageGrade(int commentId) {
        Comment comment = commentRepository.findById(commentId);
        SportObject sportObject = sportObjectRepository.findById(comment.getSportObjectId());
        double average = calculateAverage(filterAllApproved(commentRepository.findAllBySportObjectId(sportObject.getId())));
        sportObject.setAverageMark(average);
        sportObjectRepository.update(sportObject);
    }

    private double calculateAverage(List<Comment> comments) {
        if(comments.size() == 0) return 0;
        double sum = 0;
        for(Comment comment : comments) {
            sum += comment.getGrade();
        }
        return sum / comments.size();
    }

}
