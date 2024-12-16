package com.hyscaler.Online_Learning_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyscaler.Online_Learning_Platform.entity.Progress;

public interface ProgressRepo  extends JpaRepository<Progress, Long>{

    Optional<Progress> findByUserIdAndCourseId(Long userId, Long courseId);
    List<Progress> findByUser_Id(Long userId);
    
}
