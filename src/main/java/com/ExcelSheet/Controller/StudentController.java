package com.ExcelSheet.Controller;

import com.ExcelSheet.Model.Student;
import com.ExcelSheet.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/Excel")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> getFile() throws IOException {
        String filename = "students.xlsx";
        ByteArrayResource file = studentService.sheet();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
