package Controller;

import DTO.CommentDTO;
import Model.Comment;
import Model.SportObject;
import Model.User;
import Service.CommentService;
import Service.SportObjectService;
import Service.UserService;
import Utils.Constants;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class CommentController extends Controller {

    private static CommentService commentService;
    private static UserService userService;
    private static SportObjectService sportObjectService;

    public static void initContext(CommentService commService, UserService usrService, SportObjectService spoService) {
        commentService = commService;
        userService = usrService;
        sportObjectService = spoService;
    }


    public static String getAllForObject(Request request, Response response) {
        int sportObjectId = Integer.parseInt(request.params(":id"));
        return gson.toJson(commentService.findAllBySportObjectId(sportObjectId));
    }

    public static String getAllApprovedForObject(Request request, Response response) {
        int sportObjectId = Integer.parseInt(request.params(":id"));
        return gson.toJson(commentService.findAllApprovedBySportObjectId(sportObjectId));
    }

    public static String approveComment(Request request, Response response) throws Exception {
        int commentId = Integer.parseInt(request.params(":commentId"));
        commentService.approveComment(commentId);
        return successResponse();
    }

    public static String declineComment(Request request, Response response) throws Exception {
        int commentId = Integer.parseInt(request.params(":commentId"));
        commentService.declineComment(commentId);
        commentService.deleteById(commentId);
        return successResponse();
    }

    public static String deleteComment(Request request, Response response) throws Exception {
        int commentId = Integer.parseInt(request.params(":commentId"));
        commentService.deleteById(commentId);
        return statusDeletedResponse(response);
    }

    public static String addComment(Request request, Response response) throws Exception {
        Comment comment = gson.fromJson(request.body(), Comment.class);
        comment.setBuyerUsername(request.attribute("username"));
        commentService.create(comment);
        return successResponse();
    }

    public static void redirectIfBuyer(Request request, Response response) {
        User user = userService.findByUsername(request.attribute("username"));
        if(user.getRole() == Constants.UserRole.BUYER)
            response.redirect("/api/objects/"+ request.params(":id") + "/comments");
    }

    public static String getAllUndecidedComments(Request request, Response response) {
        List<Comment> undecided = commentService.findAllByApprovedFlag(false);
        return gson.toJson(mapAllCommentsToDTO(undecided));
    }

    private static List<CommentDTO> mapAllCommentsToDTO(List<Comment> comments) {
        List<CommentDTO> mapped = new ArrayList<>();
        for(Comment comment : comments) {
            mapped.add(mapCommentToDTO(comment));
        }
        return mapped;
    }


    private static CommentDTO mapCommentToDTO(Comment comment) {
        SportObject spo = sportObjectService.findById(comment.getSportObjectId());
        return new CommentDTO(comment, spo);
    }


}
