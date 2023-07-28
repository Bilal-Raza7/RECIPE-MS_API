package controller;

import jakarta.validation.Valid;
import model.User;
import model.dto.SignInInput;
import model.dto.SignUpOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.AuthenticationService;
import service.UserService;

import java.security.NoSuchAlgorithmException;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authService;
    @PostMapping("/signUp")
    public SignUpOutput signUpUser(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.signUpUser(user);
    }
    @PostMapping("/signin")
    public String signInUser(@RequestBody  @Valid SignInInput signInInput){
        return userService.signInUser(signInInput);
    }
    @DeleteMapping("/signOut")
    public String signOutUser(@RequestParam String email, @RequestParam String token) {
        if (authService.authenticate(email, token)) {
            return userService.signOutUser(email);
        } else {
            return "Sign out not allowed for non-authenticated user.";
        }
    }
}
