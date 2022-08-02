package com.fms.trainings.restController;

import com.fms.trainings.entities.Training;
import com.fms.trainings.service.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/training")
public class TrainingRestController {

    private final TrainingService trainingService;

    public TrainingRestController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Training>> getAllTrainings(){
        return new ResponseEntity<>(trainingService.getAllTrainings(), HttpStatus.OK);
    }

    @GetMapping(value = "/byCategory/{catName}")
    public @ResponseBody ResponseEntity<List<Training>> getTrainingsByCategoryName(@PathVariable String catName){

        return new ResponseEntity<List<Training>>(trainingService.findByCategoryNameContains(catName), HttpStatus.OK);
    }

    @GetMapping(value = "/byKeyWord/{keyWord}")
    public @ResponseBody ResponseEntity<List<Training>> getTrainingsByKeyWord(@PathVariable String keyWord){

        return new ResponseEntity<List<Training>>(trainingService.findByNameContains(keyWord), HttpStatus.OK);
    }

    @GetMapping(value = "/available/{available}")
    public @ResponseBody ResponseEntity<List<Training>> geAvailableTrainings(@PathVariable boolean available){

        return new ResponseEntity<List<Training>>(trainingService.geAvailableTrainings(available), HttpStatus.OK);
    }
}
