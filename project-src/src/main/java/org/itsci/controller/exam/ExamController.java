package org.itsci.controller.exam;

import org.itsci.model.exam.*;
import org.itsci.service.exam.ExamService;
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

    @GetMapping("/teacher/exam/{exam_id}/section/create")
    public String createChapters(@PathVariable("exam_id") long exam_id, Model model) {
        model.addAttribute("title", "Create Section");
        model.addAttribute("exam_id", exam_id);
        model.addAttribute("section", new ExamSection());
        return "teacher/exam-section-form";
    }

    @PostMapping("/teacher/exam/{id}/section/save")
    public String saveChapters(@PathVariable("id") long id,
                               @ModelAttribute("section") ExamSection section,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", messageSource.getMessage("page.error", null, Locale.getDefault()));
            model.addAttribute("section", section);
            return "teacher/exam-section-form";
        } else {
            examService.addExamSection(id, section);
            return "redirect:/teacher/exam/" + id + "/section/list";
        }
    }

    @GetMapping("/teacher/exam/{exam_id}/section/{section_id}/update")
    public String updateSection(@PathVariable("exam_id") long exam_id,
                                @PathVariable("section_id") long section_id,
                                Model model) {
        Exam exam = examService.getExam(exam_id);
        ExamSection section = examService.getExamSection(section_id);
        model.addAttribute("title", "Update Section");
        model.addAttribute("exam_id", exam.getId());
        model.addAttribute("section", section);
        return "teacher/exam-section-update";
    }

    @PostMapping("/teacher/exam/{exam_id}/section/update")
    public String processUpdateSection(@PathVariable("exam_id") long exam_id,
                                       @ModelAttribute("section") ExamSection section,
                                       BindingResult bindingResult,
                                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", messageSource.getMessage("page.error", null, Locale.getDefault()));
            model.addAttribute("section", section);
            return "teacher/exam-section-form";
        } else {
            ExamSection sectionDb = examService.getExamSection(section.getId());
            sectionDb.setCommandNo(section.getCommandNo());
            sectionDb.setCommandText(section.getCommandText());
            examService.saveSection(sectionDb);
            return "redirect:/teacher/exam/" + exam_id + "/section/list";
        }
    }

    @GetMapping("/teacher/exam/{exam_id}/section/{section_id}/add/question-page")
    public String addQuestionForm(@PathVariable("exam_id") long exam_id,
                                  @PathVariable("section_id") long section_id,
                                  Model model) {
        Exam exam = examService.getExam(exam_id);
        ExamSection section = examService.getExamSection(section_id);
        model.addAttribute("exam", exam);
        model.addAttribute("subject_id", section_id);
        model.addAttribute("questions", section.getQuestions());
        return "teacher/exam-section-question-list";
    }

    @GetMapping("/teacher/exam/{exam_id}/section/{section_id}/add/question")
    public String addQuestion(@PathVariable("exam_id") long exam_id,
                              @PathVariable("section_id") long section_id,
                              Model model) {
        Exam exam = examService.getExam(exam_id);
        model.addAttribute("title", "Create Multiple Choice");
        model.addAttribute("exam", exam);
        model.addAttribute("section_id", section_id);
        model.addAttribute("chapters", exam.getSubject().getChapters());
        model.addAttribute("levels", LevelEnum.getLevelOptions(messageSource, Locale.getDefault()));
        model.addAttribute("multiChoiceQuestion", new MultipleChoice());
        return "teacher/exam-section-multi_choice-form";
    }

    @PostMapping("/teacher/exam/{exam_id}/section/{section_id}/add/question")
    public String processAddQuestion(@PathVariable("exam_id") long exam_id,
                                     @PathVariable("section_id") long section_id,
                                     @ModelAttribute("multipleChoice") MultipleChoice multipleChoice,
                                     BindingResult bindingResult,
                                     Model model) {
        Exam exam = examService.getExam(exam_id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", messageSource.getMessage("page.error", null, Locale.getDefault()));
            model.addAttribute("exam_id", exam_id);
            model.addAttribute("section_id", section_id);
            model.addAttribute("chapters", exam.getSubject().getChapters());
            model.addAttribute("multiChoiceQuestion", multipleChoice);
            return "teacher/exam-section-multi_choice-form";
        } else {
            ExamSection sectionDb = examService.getExamSection(section_id);
            multipleChoice.setSubject(exam.getSubject());
            sectionDb.getQuestions().add(multipleChoice);
            examService.saveSection(sectionDb);
            return "redirect:/teacher/exam/" + exam_id + "/section/" + section_id + "/add/question-page";
        }
    }

    @GetMapping("/teacher/exam/{exam_id}/section/{section_id}/question/{question_id}/update")
    public String updateQuestion(@PathVariable("exam_id") long exam_id,
                                 @PathVariable("section_id") long section_id,
                                 @PathVariable("question_id") long question_id,
                                 Model model) {
        Exam exam = examService.getExam(exam_id);
        MultipleChoice multipleChoice = (MultipleChoice) examService.getQuestion(question_id);
        model.addAttribute("title", "Update Multiple Choice");
        model.addAttribute("exam", exam);
        model.addAttribute("section_id", section_id);
        model.addAttribute("chapters", exam.getSubject().getChapters());
        model.addAttribute("choices", multipleChoice.getChoices());
        model.addAttribute("levels", LevelEnum.getLevelOptions(messageSource, Locale.getDefault()));
        model.addAttribute("multiChoiceQuestion", multipleChoice);
        return "teacher/exam-section-multi_choice-update";
    }

    @PostMapping("/teacher/exam/{exam_id}/section/{section_id}/update/question")
    public String processUpdateQuestion(@PathVariable("exam_id") long exam_id,
                                     @PathVariable("section_id") long section_id,
                                     @ModelAttribute("multipleChoice") MultipleChoice multipleChoice,
                                     BindingResult bindingResult,
                                     Model model) {
        Exam exam = examService.getExam(exam_id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", messageSource.getMessage("page.error", null, Locale.getDefault()));
            model.addAttribute("exam_id", exam_id);
            model.addAttribute("section_id", section_id);
            model.addAttribute("chapters", exam.getSubject().getChapters());
            model.addAttribute("multiChoiceQuestion", multipleChoice);
            return "teacher/exam-section-multi_choice-form";
        } else {
            MultipleChoice questionDb = (MultipleChoice) examService.getQuestion(multipleChoice.getId());
            questionDb.setQuestion(multipleChoice.getQuestion());
            questionDb.setPoint(multipleChoice.getPoint());
            questionDb.setLevel(multipleChoice.getLevel());
            questionDb.setChapter(multipleChoice.getChapter());
            questionDb.setCorrectAnswer(multipleChoice.getCorrectAnswer());
            examService.saveQuestion(questionDb);
            return "redirect:/teacher/exam/" + exam_id + "/section/" + section_id + "/add/question-page";
        }
    }

    @GetMapping("/teacher/exam/{exam_id}/section/{section_id}/question/{question_id}/add/choice")
    public String addChoice(@PathVariable("exam_id") long exam_id,
                            @PathVariable("section_id") long section_id,
                            @PathVariable("question_id") long question_id,
                            Model model) {
        MultipleChoice multipleChoice = (MultipleChoice) examService.getQuestion(question_id);
        model.addAttribute("title", "Add Choice");
        model.addAttribute("exam_id", exam_id);
        model.addAttribute("section_id", section_id);
        model.addAttribute("question_id", question_id);
        model.addAttribute("choices", multipleChoice.getChoices());
        model.addAttribute("choice", new Choice());
        return "teacher/exam-section-multi_choice-choice-form";
    }

    @PostMapping("/teacher/exam/{exam_id}/section/{section_id}/question/{question_id}/add/choice")
    public String processAddChoice(@PathVariable("exam_id") long exam_id,
                                   @PathVariable("section_id") long section_id,
                                   @PathVariable("question_id") long question_id,
                                   @ModelAttribute("choice") Choice choice,
                                   BindingResult bindingResult,
                                   Model model) {
        MultipleChoice multipleChoice = (MultipleChoice) examService.getQuestion(question_id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", messageSource.getMessage("page.error", null, Locale.getDefault()));
            model.addAttribute("exam_id", exam_id);
            model.addAttribute("section_id", section_id);
            model.addAttribute("question_id", question_id);
            model.addAttribute("choices", multipleChoice.getChoices());
            model.addAttribute("choice", choice);
            return "teacher/exam-section-multi_choice-choice-form";
        } else {
            multipleChoice.getChoices().add(choice);
            choice.setMultipleChoice(multipleChoice);
            examService.saveQuestion(multipleChoice);
            return "redirect:/teacher/exam/" + exam_id + "/section/" + section_id + "/add/question-page";
        }
    }
}
