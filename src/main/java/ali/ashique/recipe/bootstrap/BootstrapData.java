package ali.ashique.recipe.bootstrap;

import ali.ashique.recipe.models.*;
import ali.ashique.recipe.repositories.CategoryRepo;
import ali.ashique.recipe.repositories.IngredientRepo;
import ali.ashique.recipe.repositories.RecipeRepo;
import ali.ashique.recipe.repositories.UnitOfMeasureRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@AllArgsConstructor
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {
    private CategoryRepo categoryRepo;
    private RecipeRepo recipeRepo;
    private UnitOfMeasureRepo unitOfMeasureRepo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("debug bootstrap event");
        List<Category> categories = new LinkedList<>();
        Category americanCat = new Category();
        Category Italian = new Category();
        Category Mexican = new Category();
        Category Japanian = new Category();

        americanCat.setCategoryName("American");
        Italian.setCategoryName("Italian");
        Mexican.setCategoryName("Mexican");
        Japanian.setCategoryName("Mexican");
        categories = Arrays.asList(americanCat, Italian, Mexican, Japanian);
        categoryRepo.saveAll(categories);

        List<UnitOfMeasure> unitOfMeasures = Arrays.asList(new UnitOfMeasure(null, "tea spoon"), new UnitOfMeasure(null, "table spoon"), new UnitOfMeasure(null, "cup"), new UnitOfMeasure(null, "pinch"));
        unitOfMeasureRepo.saveAll(unitOfMeasures);

        final Optional<Category> american = categoryRepo.findByCategoryName("American");
        final Optional<Category> italian = categoryRepo.findByCategoryName("Italian");
        final Optional<UnitOfMeasure> teaSpoon = unitOfMeasureRepo.findByUnitOfMeasure("tea spoon");
        final Optional<UnitOfMeasure> tableSpoon = unitOfMeasureRepo.findByUnitOfMeasure("table spoon");
        Recipe recipe = new Recipe();//"Spicy Grilled Chicken Tacos",20,15,"4 To 6 Servings","Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.","https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/","Look for ancho chile powder with the Mexican ingredients at your", Difficulty.EASY,null,new Notes("Note chicken taco"),ingredientList,null);
        recipe.setDescription("Spicy Grilled Chicken Tacos");
        recipe.setPrepTime(20);
        recipe.setCookTime(15);
        recipe.setServings("4 To 6 Servings");
        recipe.setSource("Spicy grilled chicken tacos! Quick marinade source");
        recipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        recipe.setDirections("Look for ancho chile powder with the Mexican ingredients at your directions");
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setNotes(new Notes(UUID.randomUUID().toString(), "Note1"));

        recipe.addIngredient(new Ingredient(UUID.randomUUID().toString(), "ancho chili powder", 2, teaSpoon.get()));
        recipe.addIngredient(new Ingredient(UUID.randomUUID().toString(), "powder ", 3, tableSpoon.get()));
        recipe.getCategories().add(american.get());
        recipe.getCategories().add(italian.get());
        Recipe save = recipeRepo.save(recipe);

        Recipe guacRecipe = new Recipe();//"Spicy Grilled Chicken Tacos",20,15,"4 To 6 Servings","Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.","https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/","Look for ancho chile powder with the Mexican ingredients at your", Difficulty.EASY,null,new Notes("Note chicken taco"),ingredientList,null);
        guacRecipe.setDescription("GuacRecipe");
        guacRecipe.setPrepTime(15);
        guacRecipe.setCookTime(10);
        guacRecipe.setServings("4 To 5 Servings");
        guacRecipe.setSource("Spicy guac recipe! Quick marinade source");
        guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy/");
        guacRecipe.setDirections("directions for guac recipe");
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setNotes(new Notes(UUID.randomUUID().toString(), "guac recipe note"));
        guacRecipe.addIngredient(new Ingredient(UUID.randomUUID().toString(), "ancho chili powder guac", 2, teaSpoon.get()));
        guacRecipe.addIngredient(new Ingredient(UUID.randomUUID().toString(), "powderguac  ", 3, tableSpoon.get()));
        guacRecipe.getCategories().add(american.get());
        guacRecipe.getCategories().add(italian.get());
        recipeRepo.save(guacRecipe);
    }
}