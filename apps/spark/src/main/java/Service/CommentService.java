package Service;

import Interfaces.Repository.ICommentRepository;
import Model.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentService {

    private ICommentRepository commentRepository;

    public CommentService(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void create(Comment comment) {
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

    public List<Comment> findAllBySportObjectId(int sportObjectId) {
        return commentRepository.findAllBySportObjectId(sportObjectId);
    }

    public List<Comment> findAllByUsername(String username) {
        return commentRepository.findAllByUsername(username);
    }

    public List<Comment> filterAllApproved(List<Comment> comments) {
        return comments.stream()
                .filter(Comment::isApproved)
                .collect(Collectors.toList());
    }

}
