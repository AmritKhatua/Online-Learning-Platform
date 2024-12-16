package com.hyscaler.Online_Learning_Platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hyscaler.Online_Learning_Platform.entity.Assignment;
import com.hyscaler.Online_Learning_Platform.service.AssignmentService;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH})
@RequestMapping("/assignment")
public class AssignmentController {
    
    @Autowired 
    private AssignmentService assignmentService;
    // Create a new assignment
    @PostMapping("/create")
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        Assignment createdAssignment = assignmentService.createAssignment(assignment);
        return ResponseEntity.ok(createdAssignment);
    }

    // Get all assignments
    @GetMapping("/all")
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        List<Assignment> assignments = assignmentService.getAllAssignments();
        return ResponseEntity.ok(assignments);
    }

    // Get an assignment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        Assignment assignment = assignmentService.getAssignmentById(id);
        if (assignment != null) {
            return ResponseEntity.ok(assignment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an assignment
    @PutMapping("/update/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @RequestBody Assignment updatedAssignment) {
        Assignment assignment = assignmentService.updateAssignment(id, updatedAssignment);
        if (assignment != null) {
            return ResponseEntity.ok(assignment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an assignment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
