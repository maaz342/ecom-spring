package com.maaz.Maaz.Ecommerce.entity;
import com.maaz.Maaz.Ecommerce.enums.UserRole;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name Required")
    private String name;
    @Column(unique = true)
    @NotBlank(message = "Email Required")
    private String email;
    @NotBlank(message = "Password Required")
    private String password;
    @Column(name = "phone_number")
    @NotBlank(message = "Phone number Required")
    private String Phone;
    private  UserRole role;
    @OneToMany(mappedBy ="user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<OrderItem> orderItemlist;
    @OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private Address address;
    @Column(name = "created_at")
    private final LocalDateTime createdAt= LocalDateTime.now();

}
