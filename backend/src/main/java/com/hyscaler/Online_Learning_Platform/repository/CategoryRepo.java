package com.hyscaler.Online_Learning_Platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyscaler.Online_Learning_Platform.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
