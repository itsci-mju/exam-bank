package org.itsci.controller;

import org.itsci.exam.model.Exam;
import org.itsci.exam.model.Subject;
import org.itsci.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
public class ExamController {
    @Autowired
    private ResourceBundleMessageSource messageSource;

    @Autowired
    private ExamService examService;

    @InitBinder
    public void initBuilder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/teacher/exam/list")
    public String listShop(Model model) {
        model.addAttribute("title", "Exam List");
        model.addAttribute("exams", examService.getExams());
        return "teacher/exam-list";
    }

    @GetMapping("/teacher/exam/create")
    public String showFormForAdd(Model model) {
        model.addAttribute("title", "Create new exam");
        model.addAttribute("exam", new Exam());
        model.addAttribute("subjects", examService.getSubjects());
        return "teacher/exam-form";
    }

    @RequestMapping(path = "/teacher/exam/save", method = RequestMethod.POST)
    public String processForm(@ModelAttribute("exam") Exam exam,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", messageSource.getMessage("page.error", null, Locale.getDefault()));
            model.addAttribute("exam", exam);
            model.addAttribute("subjects", examService.getSubjects());
            return "teacher/exam-form";
        } else {
            examService.saveExam(exam);
            return "redirect:/teacher/exam/list";
        }
    }

    @GetMapping("/teacher/exam/{id}/update")
    public String showFormForUpdate(@PathVariable("id") long id, Model model) {
        Exam exam = examService.getExam(id);
        model.addAttribute("title", "Update subject");
        model.addAttribute("exam", exam);
        model.addAttribute("subjects", examService.getSubjects());
        return "teacher/exam-form";
    }

    @GetMapping("/teacher/exam/{id}/section/list")
    public String listSection(@PathVariable("id") long id,
                              Model model) {
        Exam exam = examService.getExam(id);
        model.addAttribute("title", "List Section");
        model.addAttribute("exam", exam);
        model.addAttribute("sections", exam.getSections());
        return "teacher/exam-section-list";
    }
}
