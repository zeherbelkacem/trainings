package com.fms.trainings.restController;

import com.fms.trainings.entities.Category;
import com.fms.trainings.entities.Training;
import com.fms.trainings.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

   /* @GetMapping(value = "/byCategory/{catName}")
    public @ResponseBody ResponseEntity<List<Training>> getTrainingsByCategoryName(@PathVariable String catName){

        return new ResponseEntity<List<Training>>(trainingService.findByCategoryNameContains(catName), HttpStatus.OK);
    }*/
}
