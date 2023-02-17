package com.ExcelSheet.Repositry;

import com.ExcelSheet.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface StudentRepo extends MongoRepository<Student , UUID> {
}
