package com.example.spring.dto.mapper;

import com.example.spring.dto.request.SignupRequest;
import com.example.spring.entity.Role;
import com.example.spring.entity.RoleType;
import com.example.spring.entity.User;
import com.example.spring.service.RoleService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

/**
 * Mapper used for mapping SignupRequest fields
 */
@Mapper(componentModel = "spring", uses = {PasswordEncoder.class, RoleService.class, User.class})
public abstract class SignupRequestMapper {

    Double ZERO = 0.0;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Mapping(target = "firstName", expression = "java(org.apache.commons.text.WordUtils.capitalizeFully(dto.getFirstName()))")
    @Mapping(target = "lastName", expression = "java(org.apache.commons.text.WordUtils.capitalizeFully(dto.getLastName()))")
    @Mapping(target = "username", expression = "java(dto.getUsername().trim().toLowerCase())")
    @Mapping(target = "email", expression = "java(dto.getEmail().trim().toLowerCase())")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "available_funds", source = "available_funds")
    public abstract User toEntity(SignupRequest dto);

    @AfterMapping
    void setToEntityFields(@MappingTarget User entity, SignupRequest dto) {
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        final List<RoleType> roleTypes = dto.getRoles().stream()
                .map(RoleType::valueOf)
                .toList();
        final List<Role> roles = roleService.getReferenceByTypeIsIn(new HashSet<>(roleTypes));
        entity.setRoles(new HashSet<>(roles));

        // Set default value for availableFunds if necessary
        if (dto.getAvailable_funds() == null) {
            entity.setAvailable_funds(ZERO); // or whatever default value you need
        }
    }
}
