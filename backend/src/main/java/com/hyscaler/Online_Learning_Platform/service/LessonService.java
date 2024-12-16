package com.hyscaler.Online_Learning_Platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hyscaler.Online_Learning_Platform.entity.Lesson;
import com.hyscaler.Online_Learning_Platform.exception.ResourceNotFound;
import com.hyscaler.Online_Learning_Platform.repository.LessonRepo;
import com.hyscaler.Online_Learning_Platform.util.FileService;


@Service
public class LessonService {

    @Autowired
    private LessonRepo lessonRepo;

    @Autowired
    private FileService fileService;
    
    public Lesson saveLesson(Lesson lesson) {
        return lessonRepo.save(lesson);
    }

    public Lesson getLessonById(Long id) {
        return lessonRepo.findById(id).orElseThrow(() -> new ResourceNotFound());
    }

    public List<Lesson> getAllLessons() {
        return lessonRepo.findAll();
    }

    public List<Lesson> getLessonsByCourseId(Long courseId) {
        return lessonRepo.findByCourseId(courseId);
    }

    public void deleteLesson(Long id) {
        lessonRepo.deleteById(id);
    }

    public Lesson updateLesson(Long id, Lesson updatedLesson) {
        Lesson lesson = lessonRepo.findById(id).orElse(null);
        if (lesson != null) {
            lesson.setTitle(updatedLesson.getTitle());
            lesson.setContent(updatedLesson.getContent());
            lesson.setCourse(updatedLesson.getCourse());
            return lessonRepo.save(lesson);
        }
        return null;
    }

    public Lesson uploadMaterial(Long lessonId, MultipartFile file) {
        Lesson lesson = lessonRepo.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFound("Lesson not found!"));
        String filePath = fileService.saveFile(file);
        lesson.setMaterialPath(filePath);
        return lessonRepo.save(lesson);
    }


}
