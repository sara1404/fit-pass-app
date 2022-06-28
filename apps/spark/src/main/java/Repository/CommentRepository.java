package Repository;

import DataHandler.CommentDataHandler;
import DataHandler.TemplateDataHandler;
import Interfaces.Repository.ICommentRepository;
import Model.Comment;
import Model.SportObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CommentRepository implements ICommentRepository {

    private List<Comment> comments;
    private TemplateDataHandler<Comment> commentDataHandler;

    public CommentRepository(TemplateDataHandler<Comment> commentDataHandler) {
        this.commentDataHandler = commentDataHandler;
        comments = commentDataHandler.readFromFile();
    }

    @Override
    public List<Comment> findAll() {
        return comments;
    }

    @Override
    public Comment create(Comment comment) {
        comment.setId(generateId());
        comments.add(comment);
        commentDataHandler.writeToFile(comments);
        return comment;
    }

    @Override
    public void update(Comment newComment) {
        Comment comment = findById(newComment.getId());
        comment.update(newComment);
        commentDataHandler.writeToFile(comments);
    }

    @Override
    public List<Comment> findAllByUsername(String username) {
        List<Comment> filtered = new ArrayList<>();
        for(Comment comment : comments) {
            if(comment.getBuyerUsername().equals(username)) {
                filtered.add(comment);
            }
        }
        return filtered;
    }

    @Override
    public void deleteById(int id) {
        Comment comment = findById(id);
        comments.remove(comment);
        commentDataHandler.writeToFile(comments);
    }

    @Override
    public Comment findById(int id) {
       for(Comment comment : comments) {
           if(comment.getId() == id)
               return comment;
       }
       return null;
    }

    @Override
    public List<Comment> findAllBySportObjectId(int sportObjectId) {
        return comments.stream()
                .filter(comment -> comment.getSportObjectId() == sportObjectId)
                .collect(Collectors.toList());
    }

    private int generateId() {
        int id = 0;
        List<Integer> ids = extractExistingIds();

        while(ids.contains(id))
            id++;
        return id;
    }

    private List<Integer> extractExistingIds() {
        return comments.stream().map(Comment::getId).collect(Collectors.toList());
    }
}
