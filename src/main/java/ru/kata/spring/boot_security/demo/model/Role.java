package ru.kata.spring.boot_security.demo.model;

import lombok.*;

import javax.persistence.*;

//@Getter
//@Setter
@Data
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(name = "name", nullable = false)
    private String name;
}
