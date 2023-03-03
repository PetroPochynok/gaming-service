package com.gamingservice.mapper;

import com.gamingservice.model.User;
import com.gamingservice.model.UserProfile;
import com.gamingservice.model.dto.UserAndUserProfileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default User UserAndUserProfileDtoToUser(UserAndUserProfileDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setBalance(dto.getBalance());
        user.setGender(dto.getGender().toUpperCase());
        user.setCreatedAt(LocalDateTime.now());

        UserProfile userProfile = new UserProfile();
        userProfile.setCountry(dto.getCountry());
        userProfile.setCity(dto.getCity());
        userProfile.setStreet(dto.getStreet());

        user.setUserProfile(userProfile);
        userProfile.setUser(user);
        return user;
    }
}
