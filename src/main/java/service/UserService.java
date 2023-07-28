package service;

import model.AuthenticationToken;
import model.Comment;
import model.User;
import model.dto.SignInInput;
import model.dto.SignUpOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IAuthRepo;
import repository.ICommentRepo;
import repository.IUserRepo;
import service.utility.hashingUtility.PasswordEncrypter;
import service.utility.hashingUtility.emailUtility.EmailHandler;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo iUserRepo;
    @Autowired
    IAuthRepo iAuthRepo;
    @Autowired
    ICommentRepo commentRepo;

    @Autowired
    RecipeService recipeService;

    @Autowired
    CommentService commentService;

    public SignUpOutput signUpUser(User user) throws NoSuchAlgorithmException {
        boolean signUpStatus = true;
        String signUpStatusMessage = "User Registered successfully";

        String userEmail = user.getUserEmail();

        User existingUser = iUserRepo.FindFistByEmail(userEmail);

        if(existingUser != null){
            signUpStatusMessage = "User already Exist";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());
        user.setUserPassword(encryptedPassword);
        iUserRepo.save(user);
        return new SignUpOutput(signUpStatus,signUpStatusMessage);
    }

    public String signInUser(SignInInput signInInput) {
        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }

        //check if this user email already exists ??
        User existingUser = iUserRepo.findFirstByUserEmail(signInEmail);

        if(existingUser == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                iAuthRepo.save(authToken);

                EmailHandler.sendEmail(signInEmail,"email testing",authToken.getTokenValue());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }

    }

    public String signOutUser(String email) {
        User user = iUserRepo.findFirstByUserEmail(email);
        if (user != null) {
            iAuthRepo.delete(iAuthRepo.findFirstByUser(user));
            return "User Signed out Successfully";
        } else {
            return "User not found!";
        }
    }

    public String addComment(Comment comment, String commenterEmail) {
        boolean recipeValid = recipeService.validateRecipe(comment.getRecipe());
        if (recipeValid) {
            User commenter = iUserRepo.findFirstByUserEmail(commenterEmail);
            comment.setCommenter(commenter);
            return commentService.addComment(comment);
        }else {
            return "Cannot comment on Invalid Post!!!";
        }
    }

    public String removeRecipeComment(Integer commentId, String email) {
        commentRepo.deleteById(commentId);
        return "Comment deleted successfully";
    }
}
