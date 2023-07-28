package service;

import model.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IAuthRepo;

@Service
public class AuthenticationService {
    @Autowired
    IAuthRepo iAuthRepo;

    public boolean authenticate(String email, String token) {
        AuthenticationToken authenticationToken = iAuthRepo.findFirstByToken(token);

        if(authenticationToken == null) {
            return  false;
        }
        String tokenConnectedEmail = authenticationToken.getUser().getUserEmail();

        return tokenConnectedEmail.equals(email);
    }
}
