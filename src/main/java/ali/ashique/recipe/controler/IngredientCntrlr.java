package ali.ashique.recipe.controler;

import ali.ashique.recipe.commandObjects.IngredientCommand;
import ali.ashique.recipe.commandObjects.RecipeCommand;
import ali.ashique.recipe.converters.IngredientCommandToIngredient;
import ali.ashique.recipe.converters.IngredientToIngredientCommand;
import ali.ashique.recipe.converters.RecipeToRecipeCommand;
import ali.ashique.recipe.models.Ingredient;
import ali.ashique.recipe.models.Recipe;
import ali.ashique.recipe.repositories.IngredientRepo;
import ali.ashique.recipe.repositories.RecipeRepo;
import ali.ashique.recipe.repositories.UnitOfMeasureRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class IngredientCntrlr {
    private RecipeRepo recipeRepo;
    private RecipeToRecipeCommand recipeToRecipeCommand;
    private IngredientRepo ingredientRepo;
    private UnitOfMeasureRepo unitOfMeasureRepo;
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientCntrlr(RecipeRepo recipeRepo, RecipeToRecipeCommand recipeToRecipeCommand, IngredientRepo ingredientRepo, UnitOfMeasureRepo unitOfMeasureRepo, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepo = recipeRepo;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.ingredientRepo = ingredientRepo;
        this.unitOfMeasureRepo = unitOfMeasureRepo;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String getIngredients(@PathVariable("recipeId") String recipeId, Model model) {
        log.debug("/recipe/{recipeId}/ingredients" + recipeId);
        Optional<Recipe> recipe = recipeRepo.findById(recipeId);
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe.get());
        List<IngredientCommand> ingredients = recipeCommand.getIngredients();
        log.debug("ingredients" + ingredients.size());
        model.addAttribute("ingredients", ingredients);
        return "recipe/view-ingredients";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}")
    public String getIngredient(@PathVariable long id, Model model) {
        Optional<Ingredient> ingredientRepoById = ingredientRepo.findById(id);
        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredientRepoById.get());
        model.addAttribute("ingredient", ingredientCommand);
        return "recipe/view-specific-ingredient";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable long id, Model model) {
        Optional<Ingredient> ingredientRepoById = ingredientRepo.findById(id);
        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredientRepoById.get());
        model.addAttribute("uom", unitOfMeasureRepo.findAll());
        model.addAttribute("ingredient", ingredientCommand);
        return "recipe/update-ingredient";
    }

    @PostMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable Long recipeId, @Valid @ModelAttribute("ingredient") IngredientCommand ingredientCommand, Errors errors, Model model) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(i -> log.debug(i.toString()));
            model.addAttribute("uom", unitOfMeasureRepo.findAll());
            return "recipe/update-ingredient";
        }
        Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
        ingredientRepo.save(ingredient);
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    public String dltIngredient(@PathVariable long id, @PathVariable String recipeId, Model model) {
        ingredientRepo.deleteById(id);
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/add")
    public String addIngredient(@PathVariable String recipeId, Model model) {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);
        model.addAttribute("uom", unitOfMeasureRepo.findAll());
        model.addAttribute("ingredient", ingredientCommand);
        return "recipe/update-ingredient";
    }
}
