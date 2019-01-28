package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
     //calling the postcomment method in the comment repository
     public Comment addComment(Comment comment) {
         return commentRepository.createComment(comment);
     }

     public List<Comment> getComments() {
        return commentRepository.getComments();
     }
}
