package com.internship.passengerservice.utils;

import com.internship.passengerservice.dto.request.RequestProfileDto;
import com.internship.passengerservice.entity.PassengerProfile;
import com.internship.passengerservice.repo.PassengerProfileRepo;
import com.internship.passengerservice.utils.exceptions.InvalidInputException;
import com.internship.passengerservice.utils.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.internship.passengerservice.utils.exceptions.ExceptionCodes.EMAIL_ALREADY_EXISTS;
import static com.internship.passengerservice.utils.exceptions.ExceptionCodes.PASSENGER_NOT_FOUND;
import static com.internship.passengerservice.utils.exceptions.ExceptionCodes.PHONE_ALREADY_EXISTS;

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
    public void checkProfileToUpdate(RequestProfileDto profileDto){
        if(!profileDto.email().isEmpty()) {
            checkEmailUniqueness(profileDto.email());
        }
        if(!profileDto.phone().isEmpty()){
            checkPhoneNumberUniqueness(profileDto.phone());
        }
    }
}
