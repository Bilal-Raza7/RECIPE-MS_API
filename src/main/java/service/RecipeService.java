package service;

import jakarta.validation.Valid;
import model.Recipe;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IRecipeRepo;
import repository.IUserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    IRecipeRepo iRecipeRepo;

    @Autowired
    IUserRepo iUserRepo;


    public String createRecipe(Recipe recipe, String email) {
        User recipeOwner = iUserRepo.findFirstByUserEmail(email);
        if (recipeOwner != null) {
            recipe.setRecipeOwner(recipeOwner);
            iRecipeRepo.save(recipe);
            return "Recipe Posted!!!";
        }else {
            return "User not found";
        }
    }

    public List<Recipe> getAllRecipes() {
        return iRecipeRepo.findAll();
    }

    public List<Recipe> getRecipesByName(String recipeName) {
        return iRecipeRepo.findByRecipeNameContainingIgnoreCase(recipeName);
    }

    public Object updateRecipe(Recipe recipe, String email) {
        User recipeOwner = iUserRepo.findFirstByUserEmail(email);
        if(recipeOwner != null) {
            Optional<Recipe> existingRecipe = iRecipeRepo.findRecipeByRecipeId(recipe.getRecipeId());

            if (existingRecipe.isPresent()) {
                Recipe newRecipe = existingRecipe.get();

                if(newRecipe.getRecipeOwner().equals(recipeOwner)) {
                    newRecipe.setRecipeName(recipe.getRecipeName());
                    newRecipe.setIngredients(recipe.getIngredients());
                    newRecipe.setInstructions(recipe.getInstructions());


                    return iRecipeRepo.save(newRecipe);
                }else {
                    return "Recipe does not belong to the user!!";
                }
            }else {
                return "Recipe not found!!";
            }
        }else {
            return "User not found!!";
        }
    }

    public boolean deleteRecipeByName(String recipeName, String email) {
        Optional<Recipe> optionalRecipe = iRecipeRepo.findByRecipeNameAndOwnerEmail(recipeName,email);

        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();

            iRecipeRepo.delete(recipe);
            return true; // Deletion successful
        }else {
            return false; // Recipe not found or doesn't belong to the user
        }
    }

    public boolean validateRecipe(Recipe recipe) {
        return (recipe!=null && iRecipeRepo.existsById(recipe.getRecipeId()));
    }
}
