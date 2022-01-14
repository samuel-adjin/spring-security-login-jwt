package com.farad.login.Auth.Student;

import com.farad.login.Auth.Role.Role;

import java.util.List;

public interface StudentService {
    Student registerStudent(Student student);
    void addRoleToStudent (String indexNumber, String role);
    List<Student> getAllStudents();
    Role saveRole(Role role);
    List<Role> getAllRoles();
}
