package com.internship.passenger_service.utils;

import com.internship.passenger_service.dto.ProfileDto;
import com.internship.passenger_service.entity.PassengerProfile;
import com.internship.passenger_service.repo.PassengerProfileRepo;
import com.internship.passenger_service.utils.exceptions.InvalidInputException;
import com.internship.passenger_service.utils.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.internship.passenger_service.utils.exceptions.ExceptionCodes.EMAIL_ALREADY_EXISTS;
import static com.internship.passenger_service.utils.exceptions.ExceptionCodes.PASSENGER_ID_IS_NULL;
import static com.internship.passenger_service.utils.exceptions.ExceptionCodes.PASSENGER_NOT_FOUND;
import static com.internship.passenger_service.utils.exceptions.ExceptionCodes.PHONE_ALREADY_EXISTS;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class ProfileValidationManager {
    private final PassengerProfileRepo passengerProfileRepo;

    public void checkIfProfileExists(Long profileId) {
        if (!passengerProfileRepo.existsById(profileId)) {
            throw new ResourceNotFoundException(PASSENGER_NOT_FOUND.getCode());
        }
    }
    public PassengerProfile getProfileByIdIfExists(Long profileId) {
        return passengerProfileRepo.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException(PASSENGER_NOT_FOUND.getCode()));
    }
    public void checkEmailUniqueness(String email) {
        if(passengerProfileRepo.existsByEmailIgnoreCase(email)) {
            throw new InvalidInputException(EMAIL_ALREADY_EXISTS.getCode());
        }
    }
    public void checkPhoneNumberUniqueness(String phoneNumber) {
        if(passengerProfileRepo.existsByPhoneIgnoreCase(phoneNumber)) {
            throw new InvalidInputException(PHONE_ALREADY_EXISTS.getCode());
        }
    }
    public void checkProfileToUpdate(ProfileDto profileDto){
        if(isNull(profileDto.profileId())){
            throw new InvalidInputException(PASSENGER_ID_IS_NULL.getCode());
        }
        if(!profileDto.email().isEmpty()) {
            checkEmailUniqueness(profileDto.email());
        }
        if(!profileDto.phone().isEmpty()){
            checkPhoneNumberUniqueness(profileDto.phone());
        }
    }
}
