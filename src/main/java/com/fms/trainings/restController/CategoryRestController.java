package com.fms.trainings.restController;

import com.fms.trainings.entities.Category;
import com.fms.trainings.entities.Order;
import com.fms.trainings.entities.Training;
import com.fms.trainings.service.CategoryService;
import com.fms.trainings.service.OrderService;
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

    @GetMapping("/category/{id}")
    public @ResponseBody ResponseEntity<Category> getCategoryById(@PathVariable long id){
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }


    @PostMapping("/save")
    public @ResponseBody ResponseEntity<Category> saveCategory(@RequestBody Category category){
        return new ResponseEntity<Category>(categoryService.saveCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody void deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
    }
}
