package org.itsci.controller.exam;

import org.itsci.model.exam.Chapter;
import org.itsci.model.exam.Subject;
import org.itsci.service.exam.SubjectService;
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
public class SubjectController {
    @Autowired
    ResourceBundleMessageSource messageSource;

    @Autowired
    SubjectService subjectService;

    @InitBinder
    public void initBuilder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/system/subject/list")
    public String listShop(Model model) {
        model.addAttribute("title", "Subject List");
        model.addAttribute("subjects", subjectService.getSubjects());
        return "system/subject-list";
    }

    @GetMapping("/system/subject/create")
    public String showFormForAdd(Locale locale, Model model) {
        model.addAttribute("title", "Create new subject");
        model.addAttribute("subject", new Subject());
        return "system/subject-form";
    }

    @GetMapping("/system/subject/{id}/update")
    public String showFormForUpdate(@PathVariable("id") long id, Model model) {
        Subject subject = subjectService.getSubject(id);
        model.addAttribute("title", "Update subject");
        model.addAttribute("subject", subject);
        return "system/subject-form";
    }

    @GetMapping("/system/subject/{id}/chapter/list")
    public String listChapters(@PathVariable("id") long id, Model model) {
        Subject subject = subjectService.getSubject(id);
        model.addAttribute("title", "List chapter");
        model.addAttribute("subject", subject);
        model.addAttribute("chapters", subject.getChapters());
        return "system/subject-chapter-list";
    }

    @GetMapping("/system/subject/{id}/chapter/create")
    public String createChapters(@PathVariable("id") int id, Model model) {
        Subject subject = subjectService.getSubject((long) id);
        model.addAttribute("title", "Create chapter");
        model.addAttribute("subject_id", subject.getId());
        model.addAttribute("chapter", new Chapter());
        return "system/subject-chapter-form";
    }

    @GetMapping("/system/subject/{subject_id}/chapter/{chapter_id}/update")
    public String updateChapters(@PathVariable("subject_id") long subject_id,
                                 @PathVariable("chapter_id") long chapter_id,
                                 Model model) {
        Subject subject = subjectService.getSubject(subject_id);
        Chapter chapter = subjectService.getChapter(chapter_id);
        model.addAttribute("title", "Update chapter");
        model.addAttribute("subject_id", subject.getId());
        model.addAttribute("chapter", chapter);
        return "system/subject-chapter-update";
    }

    @PostMapping("/system/subject/{subject_id}/chapter/update")
    public String saveChapters(@PathVariable("subject_id") long subject_id,
                               @ModelAttribute("chapter") Chapter chapter,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", messageSource.getMessage("page.error", null, Locale.getDefault()));
            model.addAttribute("chapter", chapter);
            return "system/subject-chapter-form";
        } else {
            Chapter obj = new Chapter();
            obj.setId(chapter.getId());
            obj.setName(chapter.getName());
            subjectService.saveChaper(obj);
            return "redirect:/system/subject/" + subject_id + "/chapter/list";
        }
    }

    @RequestMapping(path = "/system/subject/{id}/chapter/save", method = RequestMethod.POST)
    public String saveChapterNew(@PathVariable("id") long id,
                                 @ModelAttribute("chapter") Chapter chapter,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", messageSource.getMessage("page.error", null, Locale.getDefault()));
            model.addAttribute("chapter", chapter);
            return "system/subject-chapter-form";
        } else {
            Chapter obj = new Chapter();
            obj.setId(chapter.getId());
            obj.setName(chapter.getName());
            subjectService.addChaper(id, obj);
            return "redirect:/system/subject/" + id + "/chapter/list";
        }
    }

    @RequestMapping(path = "/system/subject/save", method = RequestMethod.POST)
    public String processForm(@ModelAttribute("subject") Subject subject,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", messageSource.getMessage("page.error", null, Locale.getDefault()));
            model.addAttribute("subject", subject);
            return "member/subject-form";
        } else {
            Subject obj = new Subject();
            obj.setId(subject.getId());
            obj.setName(subject.getName());

            subjectService.saveSubject(obj);
            return "redirect:/system/subject/list";
        }
    }

    @GetMapping("/system/subject/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id) {
        subjectService.deleteSubject(id);
        return "redirect:/system/subject/list";
    }
}
