package repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {

    User FindFistByEmail(String userEmail);

    User findFirstByUserEmail(String signInEmail);
}
