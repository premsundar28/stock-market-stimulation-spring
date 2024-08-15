package com.example.spring.repository;

import com.example.spring.entity.Role;
import com.example.spring.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> getReferenceByTypeIsIn(Set<RoleType> types);
}
