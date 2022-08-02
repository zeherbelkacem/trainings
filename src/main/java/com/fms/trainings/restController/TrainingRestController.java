package com.fms.trainings.restController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fms.trainings.entities.ImageModel;
import com.fms.trainings.entities.Training;
import com.fms.trainings.service.ImageService;
import com.fms.trainings.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/training")
public class TrainingRestController {

    private final TrainingService trainingService;
    @Autowired
    private ImageService imageService;

    public TrainingRestController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    /*TRAINING SAVING*/
    @PostMapping(value = {"/save"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Training saveTraining(@RequestPart("training") Training training, @RequestPart("imageFile") MultipartFile file) throws IOException {

        System.out.println("image: "+file.getOriginalFilename());
        System.out.println("image: "+file.getContentType());
        System.out.println("image: "+file.getBytes().length);
        try {
            ImageModel imageModel = uploadImage(file); // new ImageModel(0, file.getOriginalFilename(), file.getContentType(), file.getBytes());
            //System.out.println("imageModel :"+imageModel);
            imageService.saveImage(imageModel);
            training.setImageModel(imageModel);
            System.out.println("training: "+training);
            return trainingService.saveTraining(training);
        }catch (Exception e){
            e.printStackTrace();
           System.out.println("error: "+e.getMessage());
            return null;
        }
    }
    @PostMapping(value = {"/image"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ImageModel saveImage(@RequestPart("imageFile") MultipartFile file) throws IOException {

        System.out.println("image: "+file.getOriginalFilename());
        System.out.println("image: "+file.getContentType());
        System.out.println("image: "+file.getBytes().length);
        try {
            ImageModel imageModel = uploadImage(file); // new ImageModel(0, file.getOriginalFilename(), file.getContentType(), file.getBytes());
            //System.out.println("imageModel :"+imageModel);
            return imageService.saveImage(imageModel);
        }catch (Exception e){
            e.printStackTrace();
           // System.out.println("error: "+e.getMessage());
            return null;
        }
    }

    public ImageModel uploadImage(MultipartFile file) throws IOException {
        return new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Training>> getAllTrainings() {
        return new ResponseEntity<>(trainingService.getAllTrainings(), HttpStatus.OK);
    }

    @GetMapping(value = "/byCategory/{catName}")
    public @ResponseBody ResponseEntity<List<Training>> getTrainingsByCategoryName(@PathVariable String catName) {

        return new ResponseEntity<List<Training>>(trainingService.findByCategoryNameContains(catName), HttpStatus.OK);
    }

    @GetMapping(value = "/byKeyWord/{keyWord}")
    public @ResponseBody ResponseEntity<List<Training>> getTrainingsByKeyWord(@PathVariable String keyWord) {

        return new ResponseEntity<List<Training>>(trainingService.findByNameContains(keyWord), HttpStatus.OK);
    }

    @GetMapping(value = "/available/{available}")
    public @ResponseBody ResponseEntity<List<Training>> geAvailableTrainings(@PathVariable boolean available) {

        return new ResponseEntity<List<Training>>(trainingService.geAvailableTrainings(available), HttpStatus.OK);
    }
}
