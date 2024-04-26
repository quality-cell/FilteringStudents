package com.example.FilteringStudents.repository;

import com.example.FilteringStudents.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findByName(String name);
}
