package ali.ashique.recipe.controler;

import ali.ashique.recipe.commandObjects.RecipeCommand;
import ali.ashique.recipe.converters.RecipeToRecipeCommand;
import ali.ashique.recipe.exceptions.NotFoundException;
import ali.ashique.recipe.models.Difficulty;
import ali.ashique.recipe.models.Recipe;
import ali.ashique.recipe.repositories.CategoryRepo;
import ali.ashique.recipe.repositories.RecipeRepo;
import ali.ashique.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@Slf4j
public class index {

    private RecipeRepo recipeRepo;
    private RecipeService recipeService;
    private RecipeToRecipeCommand recipeToRecipeCommand;


    public index(RecipeRepo recipeRepo, RecipeService recipeService, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepo = recipeRepo;
        this.recipeService = recipeService;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @RequestMapping({"", "/"})
    public String show(Model model) {
        log.debug("index controller");
        model.addAttribute("recipes", recipeRepo.findAll());
        return "index";
    }

    @RequestMapping("/recipe/showdtl/{id}")
    public String recipeDetail(@PathVariable String id, Model model) {

        Optional<Recipe> recipe = recipeRepo.findById(id);
        model.addAttribute("recipe", recipe.get());
        return "recipe/recipe_detail";
    }

    @RequestMapping(value = "/recipe/add-recipe-ctrlr", method = RequestMethod.GET)
    public String addRecipeView(Model model) {
        RecipeCommand recipeCommand = new RecipeCommand();
        model.addAttribute("rc", recipeCommand);
        model.addAttribute("difficulty", Difficulty.values());

        return "recipe/add-recipe";
    }

    @RequestMapping(value = "/recipe/add-recipe-ctrlr", method = RequestMethod.POST)
    public String addRecipe(@Valid @ModelAttribute("rc") RecipeCommand recipeCommand, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("difficulty", Difficulty.values());
            return "recipe/add-recipe";
        }
        Recipe recipe = recipeService.save(recipeCommand);
        return "redirect:/recipe/showdtl/" + recipe.getId();
    }

    @GetMapping("/recipe/{recipeId}/update")
    public String update(@PathVariable String recipeId, Model model) {
        Optional<Recipe> recipe = recipeRepo.findById(recipeId);
        if (recipe.isEmpty()) {
            throw new NotFoundException("Not Found Page Recipe ID:" + recipeId);
        }
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe.get());
        log.debug("update image");
        model.addAttribute("rc", recipeCommand);
        model.addAttribute("difficulty", Difficulty.values());
        return "recipe/add-recipe";
    }

    @GetMapping("/recipe/{id}/delete")
    public String delRecipe(@PathVariable String id) {
        recipeRepo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/recipe/{id}/changePic")
    public String changePic(@PathVariable String id, Model model) {
        Optional<Recipe> recipe = recipeRepo.findById(id);
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe.get());
        model.addAttribute("recipe", recipeCommand);
        return "recipe/recipe-ch-pic";
    }

    @PostMapping("/recipe/{id}/changePic")
    public String changePic(@PathVariable String id, @RequestParam("pic") MultipartFile image) throws IOException {
        recipeService.saveImage(id, image);
        return "redirect:/recipe/showdtl/" + id;
    }

    @GetMapping("/recipe/{id}/image")
    public void getImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        Optional<Recipe> recipeOptional = recipeRepo.findById(id);
        if (recipeOptional.get().getImage() != null) {
            response.setContentType("image/jpeg");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(recipeOptional.get().getImage());
        }
    }

//    @ExceptionHandler()
//    public String handleNumberFormat(Exception exception,Model model){
//        model.addAttribute("exception",exception);
//        return "404-error";
//    }

}
