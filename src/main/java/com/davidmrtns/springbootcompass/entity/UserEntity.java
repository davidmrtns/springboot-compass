package com.davidmrtns.springbootcompass.entity;

import com.davidmrtns.springbootcompass.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
@Entity
@Table(name = "CPS_USER")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    public UserEntity(UserDTO dto) {
        BeanUtils.copyProperties(dto, this);
    }

    public UserEntity() { }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
