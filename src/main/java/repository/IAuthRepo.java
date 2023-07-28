package repository;

import model.AuthenticationToken;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthRepo extends JpaRepository<AuthenticationToken,Integer> {


     AuthenticationToken findFirstByToken(String token);

    AuthenticationToken findFirstByUser(User user);
}
