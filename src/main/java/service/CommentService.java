package service;

import model.Comment;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ICommentRepo;
import repository.IUserRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    IUserRepo userRepo;
    @Autowired
    ICommentRepo commentRepo;
    public String addComment(Comment comment) {
        comment.setCommentCreationTimeStamp(LocalDateTime.now());
        commentRepo.save(comment);
        return "Comment has been added!!!";
    }

    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    public Object updateComment(Comment comment, String commenterEmail) {
        User commentOwner = userRepo.findFirstByUserEmail(commenterEmail);
        if (commentOwner != null) {
            Optional<Comment> existingComment = commentRepo.findCommentByCommentId(comment.getCommentId());
            if (existingComment.isPresent()) {
                Comment existingCommentEntity = existingComment.get();
                if (existingCommentEntity.getCommenter().equals(commentOwner)) {
                    existingCommentEntity.setCommentBody(comment.getCommentBody());
                    existingCommentEntity.setCommentCreationTimeStamp(LocalDateTime.now());
                    return commentRepo.save(existingCommentEntity);
                } else {
                    return "Comment does not belong to the user!!";
                }
            } else {
                return "Comment not found!!";
            }
        } else {
            return "User not found!!";
        }
    }
}
