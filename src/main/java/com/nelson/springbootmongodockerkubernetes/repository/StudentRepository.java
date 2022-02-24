package com.nelson.springbootmongodockerkubernetes.repository;

import com.nelson.springbootmongodockerkubernetes.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
