package com.ExcelSheet.Service;

import com.ExcelSheet.Model.Student;
import com.ExcelSheet.Repositry.StudentRepo;
import com.ExcelSheet.Sheet.ExcelSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public ByteArrayResource sheet() throws IOException {
        List<Student> students = studentRepo.findAll();

        ByteArrayResource in = ExcelSheet.studentsToExcel(students);
        return in;
    }



}
