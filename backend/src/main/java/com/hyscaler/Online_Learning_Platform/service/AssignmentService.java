package com.hyscaler.Online_Learning_Platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyscaler.Online_Learning_Platform.entity.Assignment;
import com.hyscaler.Online_Learning_Platform.repository.AssignmentRepo;

@Service
public class AssignmentService {
    
     @Autowired
    private AssignmentRepo assignmentRepo;

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepo.save(assignment);
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepo.findAll();
    }

    public Assignment getAssignmentById(Long id) {
        return assignmentRepo.findById(id).orElse(null);
    }

    public Assignment updateAssignment(Long id, Assignment updatedAssignment) {
        Assignment assignment = assignmentRepo.findById(id).orElse(null);
        if (assignment != null) {
            assignment.setTitle(updatedAssignment.getTitle());
            assignment.setDescription(updatedAssignment.getDescription());
            assignment.setDueDate(updatedAssignment.getDueDate());
            assignment.setCourse(updatedAssignment.getCourse());
            return assignmentRepo.save(assignment);
        }
        return null;
    }

    public void deleteAssignment(Long id) {
        assignmentRepo.deleteById(id);
    }
}
