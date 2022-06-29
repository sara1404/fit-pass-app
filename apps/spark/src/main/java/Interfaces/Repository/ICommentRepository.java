package Interfaces.Repository;

import Interfaces.Base.IGenericCRUD;
import Model.Comment;

import java.util.List;

public interface ICommentRepository extends IGenericCRUD<Comment> {
     List<Comment> findAllByUsername(String username);
     void deleteById(int id);
     Comment findById(int id);
     List<Comment> findAllBySportObjectId(int sportObjectId);
}
