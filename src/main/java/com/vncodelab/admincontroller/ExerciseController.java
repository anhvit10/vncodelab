package com.vncodelab.admincontroller;

import com.vncodelab.entity.Exercise;
import com.vncodelab.entity.ExerciseSelect;
import com.vncodelab.entity.Lab;
import com.vncodelab.respository.ExerciseRepository;
import com.vncodelab.respository.LabRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class ExerciseController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private LabRespository labRespository;

    @GetMapping(value = "/exercise/select")
    public String selectExcercise(Model model) {
        List<Lab> labs = labRespository.findAll();
        model.addAttribute("labs", labs);
        model.addAttribute("exerciseSelect", new ExerciseSelect());
        return "admin2/exercise-select";
    }

    @PostMapping(value = "/exercise")
    public String getExercise(@ModelAttribute("exerciseSelect") ExerciseSelect exerciseSelect, Model model) {
        List<Exercise> exercises = exerciseRepository.findAllByLabNameAndDate(exerciseSelect.getLabName(), exerciseSelect.getDate());
        model.addAttribute("exercises", exercises);
        return "admin2/exercise";
    }
}