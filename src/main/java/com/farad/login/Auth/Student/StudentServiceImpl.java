package com.farad.login.Auth.Student;

import com.farad.login.Auth.Role.Role;
import com.farad.login.Auth.Role.RoleRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class StudentServiceImpl implements StudentService, UserDetailsService {
    private final StudentRepo studentRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Student registerStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepo.save(student);
    }

    @Override
    public void addRoleToStudent(String indexNumber, String role) {
        Role studentRole = roleRepo.findByRole(role).orElseThrow(
                ()->new UsernameNotFoundException("role does not exist")
        );

        Student student = studentRepo.findByIndexNumber(indexNumber).orElseThrow(
                ()->new UsernameNotFoundException("User does not exist")
        );
        student.getRole().add(studentRole);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String indexNumber) throws UsernameNotFoundException {
        Student student = studentRepo.findByIndexNumber(indexNumber).orElseThrow(
                ()->new UsernameNotFoundException("User not found")
        );
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        student.getRole().forEach(role -> {
           authorities.add(new SimpleGrantedAuthority(role.getRole()));
        });
        return new  User(student.getIndexNumber(),student.getPassword(),authorities);
    }
}
