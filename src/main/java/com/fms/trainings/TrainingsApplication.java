package com.fms.trainings;

import com.fms.trainings.entities.Category;
import com.fms.trainings.entities.Training;
import com.fms.trainings.service.CategoryService;
import com.fms.trainings.service.TrainingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@SpringBootApplication
public class TrainingsApplication implements CommandLineRunner {

    private final TrainingService trainingService;
    private final CategoryService categoryService;

    public TrainingsApplication(TrainingService trainingService, CategoryService categoryService) {
        this.trainingService = trainingService;
        this.categoryService = categoryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TrainingsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Category data = new Category(null, "Big Data, Data Science & AI", null);
        Category development = new Category(null, "Development", null);
        Category webDevelopment = new Category(null, "Web Development", null);
        Category cloud = new Category(null, "Cloud", null);
        Category network = new Category(null, "Network & Telecommunication", null);
        Category virtualization = new Category(null, "Virtualization", null);
        Category cyberSecurity = new Category(null, "Cyber Security", null);
        Category erp = new Category(null, "ERP", null);

        categoryService.saveCategory(data);
        categoryService.saveCategory(cloud);
        categoryService.saveCategory(development);
        categoryService.saveCategory(webDevelopment);
        categoryService.saveCategory(network);
        categoryService.saveCategory(virtualization);
        categoryService.saveCategory(cyberSecurity);
        categoryService.saveCategory(erp);

        trainingService.saveTraining(new Training(null, "JAVA", "Java Web Developer course", 2800., 1,
                null, true, false, LocalDate.of(2022, 10, 1),
                LocalDate.of(2023, 5, 30), Period.between(LocalDate.of(2022, 10, 1),
                LocalDate.of(2023, 5, 30)).toString(), webDevelopment));
        trainingService.saveTraining(new Training(null, "C++", "Designing GUIs with Qt for C++ Developers", 1800., 1,
                null, true, false, LocalDate.of(2022, 9, 1),
                LocalDate.of(2023, 3, 30), Period.between(LocalDate.of(2022, 9, 1),
                LocalDate.of(2023, 3, 30)).toString(), development));
        trainingService.saveTraining(new Training(null, "Python", "Python Training - Object Programming", 2500., 1,
                null, true, false, LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 6, 30), Period.between(LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 6, 30)).toString(), development));
        trainingService.saveTraining(new Training(null, "C#", "Fundamentals of .Net development in C# under Visual Studio", 1500., 1,
                null, true, false, LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 30), Period.between(LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 30)).toString(), development));
        trainingService.saveTraining(new Training(null, "Web application", "Web Application Developer in Visual Studio", 3500., 1,
                null, true, false, LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 3, 30), Period.between(LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 3, 30)).toString(), webDevelopment));
        trainingService.saveTraining(new Training(null, "Html Css JavaScript", "Fundamentals of GUI development with HTML5, CSS3 and JavaScript", 3700., 1,
                null, true, false, LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 4, 30), Period.between(LocalDate.of(2022, 12, 1),
                LocalDate.of(2023, 4, 30)).toString(), webDevelopment));
        trainingService.saveTraining(new Training(null, "Scala", "Big Data - Scala Programming", 1700., 1,
                null, false, false, LocalDate.of(2022, 12, 1),
                LocalDate.of(2022, 12, 31), Period.between(LocalDate.of(2022, 12, 1),
                LocalDate.of(2022, 12, 31)).toString(), data));
        trainingService.saveTraining(new Training(null, "SQL", "Training Querying databases with the SQL language", 2700., 1,
                null, true, false, LocalDate.of(2022, 10, 1),
                LocalDate.of(2023, 6, 30), Period.between(LocalDate.of(2022, 10, 1),
                LocalDate.of(2023, 6, 30)).toString(), data));
        trainingService.saveTraining(new Training(null, "AWS", "Amazon Web Services (AWS) - Cloud Financial Management, Finops", 4700., 1,
                null, true, false, LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 6, 30), Period.between(LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 6, 30)).toString(), cloud));
        trainingService.saveTraining(new Training(null, "Network", "Computer networks: vocabulary, concepts and technologies for the uninitiated", 2750., 1,
                null, false, false, LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 6, 30), Period.between(LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 6, 30)).toString(), network));
        trainingService.saveTraining(new Training(null, "FullStack", "Design of full JavaScript graphical interfaces with Angular, TypeScript and Bootstrap", 2750., 1,
                null, true, false, LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 9, 30), Period.between(LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 9, 30)).toString(), webDevelopment));
        trainingService.saveTraining(new Training(null, "Angular", "Angular 2+ - Web Application Development", 2750., 1,
                null, true, false, LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 8, 30), Period.between(LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 8, 30)).toString(), webDevelopment));


		List<Training> trainings = trainingService.getAllTrainings();
		trainings.forEach(t->{
			System.out.println(t.getCategory().getName());
		});

    }
}
