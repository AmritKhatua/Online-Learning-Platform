package com.hyscaler.Online_Learning_Platform.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    // @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<Course> coursesTaught; // For instructors

    // @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    // private List<Course> coursesEnrolled; // For students

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id")
    private List<Course> coursesTaught; // Unidirectional relationship for instructors

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_course",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> coursesEnrolled; // Unidirectional relationship for students

}
