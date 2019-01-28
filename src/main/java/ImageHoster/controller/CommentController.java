package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private CommentService commentService;

    //this method is implemented to make new comment and also to redirect the user to the same image page

    @RequestMapping(value = "/image/{id}/{title}/comments" ,  method = RequestMethod.POST)
    public String createComment(@PathVariable("id") Integer id, @RequestParam("comment") String imageComment, HttpSession session){
        User user = (User) session.getAttribute("loggeduser");
         //setting the comment model class
        Image image = imageService.getImage(id);
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setImage(image);
        comment.setCreatedDate(new Date());
        comment.setText(imageComment);
         //registering the new comment in the database with help of comment service
        commentService.addComment(comment);

        //returning the same image page after post request
        return "redirect:/images/" + image.getId() + "/" + image.getTitle();
        //model.addAttribute("comments",image.getComment());
        //model.addAttribute("image", image);
        //model.addAttribute("tags", image.getTags());
        //return "images/image";
    }
}
