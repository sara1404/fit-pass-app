package Interfaces.Repository;

import Interfaces.Base.IGenericCRUD;
import Model.Comment;

import java.util.List;

public interface ICommentRepository extends IGenericCRUD<Comment> {
    public List<Comment> findAllByUsername(String username);
    public void deleteById(int id);
    public Comment findById(int id);
    public List<Comment> findAllBySportObjectId(int sportObjectId);
}
