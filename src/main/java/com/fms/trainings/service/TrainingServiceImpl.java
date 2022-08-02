package com.fms.trainings.service;

import com.fms.trainings.entities.Training;
import com.fms.trainings.repository.TrainingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService{

    private TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Training saveTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> findByNameContains(String keyWord) {
        return trainingRepository.findByNameContains(keyWord);
    }

    @Override
    public Training getTrainingById(Long id) {
        return trainingRepository.findById(id).get();
    }

    @Override
    public void deleteTrainingById(Long id) {
        trainingRepository.deleteById(id);
    }

    @Override
    public Training updateTraining(Training training, Long id) {
        return null;
    }

    @Override
    public Training getTrainingByName(String name) {
        return trainingRepository.findByName(name);
    }

    @Override
    public Page<Training> getAllTrainingsByPagesAndName(String name, Pageable pageable) {
        return trainingRepository.findByName(name, pageable);
    }

    @Override
    public List<Training> findByCategoryNameContains(String catName) {
        return trainingRepository.findByCategoryNameContains(catName);
    }

    @Override
    public List<Training> geAvailableTrainings(boolean available) {
        return trainingRepository.findAvailableTrainings(available);
    }
}
