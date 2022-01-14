package com.farad.login.Auth.Student;

import com.farad.login.Auth.Role.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static javax.persistence.FetchType.EAGER;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Student {
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName =  "student_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String firstName;
    private String otherName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String indexNumber;
    @Column(nullable = false)
    private String level;
    @Column(nullable = false)
    private Date dob;
    @ManyToMany(fetch = EAGER)
    private Collection<Role> role = new ArrayList<>();
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public Student(String firstName, String otherName, String lastName, String password, String email, String indexNumber, String level, Date dob) {
        this.firstName = firstName;
        this.otherName = otherName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.indexNumber = indexNumber;
        this.level = level;
        this.dob = dob;
    }
}
