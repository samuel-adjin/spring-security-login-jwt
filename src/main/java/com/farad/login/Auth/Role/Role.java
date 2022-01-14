package com.farad.login.Auth.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Role {
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName =  "role_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    private Long id;
    private String role;

    public Role(String role) {
        this.role = role;
    }
}
