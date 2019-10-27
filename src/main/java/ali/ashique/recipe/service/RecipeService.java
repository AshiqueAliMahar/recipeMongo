package ali.ashique.recipe.service;

import ali.ashique.recipe.commandObjects.RecipeCommand;
import ali.ashique.recipe.converters.RecipeCommandToRecipe;
import ali.ashique.recipe.models.Recipe;
import ali.ashique.recipe.repositories.RecipeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RecipeService {

    private RecipeRepo recipeRepo;
    private RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeService(RecipeRepo recipeRepo, RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepo = recipeRepo;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    public List<Recipe> findAll() {
        log.debug("service find all");
        return (List<Recipe>) recipeRepo.findAll();
    }

    public Recipe save(RecipeCommand recipeCommand) {
        Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
        if (recipeRepo.existsById(recipe.getId()))
            recipe.setImage(recipeRepo.findById(recipe.getId()).get().getImage());
        recipeRepo.save(recipe);
        return recipe;
    }

    public Recipe saveImage(String id, MultipartFile image) throws IOException {
        Optional<Recipe> recipe = recipeRepo.findById(id);
        if (recipe.isPresent()) {
            recipe.get().setImage(image.getBytes());
            recipeRepo.save(recipe.get());
        }
        return recipe.get();
    }
}
