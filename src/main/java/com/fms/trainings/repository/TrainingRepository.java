package com.fms.trainings.repository;

import com.fms.trainings.entities.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    public Training findByName(String name);

    public Page<Training> findByName(String name, Pageable pageable);

    public List<Training> findByCategoryNameContains(String catName);

    public List<Training> findByNameContains(String keyWord);

    @Query(value = "SELECT * FROM training t WHERE t.available = :available",
            nativeQuery = true)
    public List<Training> findAvailableTrainings(@Param("available") boolean available);

}
