package com.cscoursesdz.cscoursesdz.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", insertable=false, updatable=false, nullable=false)
    private UUID id;

    @NonNull
    @Column(length=20)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role authority = (Role) o;

        return name.equals(authority.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
