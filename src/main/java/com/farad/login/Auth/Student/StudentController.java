package com.farad.login.Auth.Student;

import com.farad.login.Auth.Role.Role;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
@AllArgsConstructor
public class StudentController {

        private final StudentServiceImpl studentServiceImpl;

        @GetMapping
        public ResponseEntity<List<Student>> getAllStudents()
        {
                return ResponseEntity.ok().body(studentServiceImpl.getAllStudents());
        }

        @PostMapping
        public ResponseEntity<Student> addNewStudent(@RequestBody Student student){
             return ResponseEntity.ok().body(
                     studentServiceImpl.registerStudent(student)
             ) ;
        }

        @PostMapping("/role-to-user")
        public ResponseEntity<?> addRoleToUser(@RequestBody AddRoleToStudent addRoleToStudent){
                studentServiceImpl.addRoleToStudent(addRoleToStudent.getIndexNumber(),addRoleToStudent.getRole());
                return ResponseEntity.ok().build();
        }

        @PostMapping("/save-role")
        public ResponseEntity<Role> saveRole(@RequestBody Role role)
        {
                return ResponseEntity.ok().body(
                        studentServiceImpl.saveRole(role)
                );
        }

        @GetMapping("/all-roles")
        public ResponseEntity<List<Role>> getAllRoles()
        {
                return ResponseEntity.ok().body(
                        studentServiceImpl.getAllRoles()
                );
        }

}
