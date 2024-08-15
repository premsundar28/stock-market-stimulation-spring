package com.example.spring.dto.mapper;

import com.example.spring.dto.request.SignupRequest;
import com.example.spring.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-15T18:15:17+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class SignupRequestMapperImpl extends SignupRequestMapper {

    @Override
    public User toEntity(SignupRequest dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setAvailable_funds( dto.getAvailable_funds() );
        user.setId( dto.getId() );

        user.setFirstName( org.apache.commons.text.WordUtils.capitalizeFully(dto.getFirstName()) );
        user.setLastName( org.apache.commons.text.WordUtils.capitalizeFully(dto.getLastName()) );
        user.setUsername( dto.getUsername().trim().toLowerCase() );
        user.setEmail( dto.getEmail().trim().toLowerCase() );

        setToEntityFields( user, dto );

        return user;
    }
}
