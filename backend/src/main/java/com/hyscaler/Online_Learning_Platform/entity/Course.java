package com.hyscaler.Online_Learning_Platform.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String syllabus;
    private int duration;


    //---------------------------
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Lesson> lessons; // Unidirectional relationship

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Quiz> quizzes; // Unidirectional relationship

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Assignment> assignments; // Unidirectional relationship

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "course_category",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories; // Unidirectional relationship

     @ElementCollection
    private List<String> tags; // List of tags for the course

    private boolean published; // New field to track publishing status

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator; // New field to track the course creator

}
    //------------------------------


