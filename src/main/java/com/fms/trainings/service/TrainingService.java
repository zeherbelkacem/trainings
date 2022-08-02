package com.fms.trainings.service;

import com.fms.trainings.entities.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrainingService {

    public Training saveTraining(Training training);
    public List<Training> getAllTrainings();
    public List<Training> findByNameContains(String keyWord);

    public Training getTrainingById(Long id);

    public void deleteTrainingById(Long id);

    public Training updateTraining(Training training, Long id);

    public Training getTrainingByName(String name);

    public Page<Training> getAllTrainingsByPagesAndName(String name, Pageable pageable);

    List<Training> findByCategoryNameContains(String catName);

    public List<Training> geAvailableTrainings(boolean available);
}
