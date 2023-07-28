package repository;

import model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRecipeRepo extends JpaRepository<Recipe,Integer> {


     List<Recipe> findByRecipeNameContainingIgnoreCase(String recipeName);



     Optional<Recipe> findRecipeByRecipeId(Integer recipeId);

     Optional<Recipe> findByRecipeNameAndOwnerEmail(String recipeName, String email);
}
