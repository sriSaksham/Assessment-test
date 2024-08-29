package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.dto.UserProfileDto;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.UserActivityStatusResponse;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MembershipService membershipService;

    public Optional<UserProfileDto> getUserProfileByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserProfileDto userProfileDTO = new UserProfileDto();
            userProfileDTO.setUsername(user.getUsername());
            userProfileDTO.setEmail(user.getEmail());
            userProfileDTO.setFirstName(user.getFirstName());
            userProfileDTO.setLastName(user.getLastName());
            userProfileDTO.setPhoneNumber(user.getPhoneNumber());
            userProfileDTO.setRegistrationDate(user.getRegistrationDate());
            if (user.getPendingAmount() !=0) {
                userProfileDTO.setPendingAmount(user.getPendingAmount());
            } else {
                userProfileDTO.setPendingAmount(0);
            }

            if(user.getValidity() !=null){
                userProfileDTO.setValidity(user.getValidity());
            }else{
                userProfileDTO.setValidity(null);
            }


            if (user.getMembership() != null) {
                Long mid = user.getMembership().getMid();
                String membershipName = membershipService.getMembershipNameByMid(mid);
                userProfileDTO.setMembershipPlan(membershipName);
            } else {
                userProfileDTO.setMembershipPlan("No Membership");
            }

            return Optional.of(userProfileDTO);
        } else {
            return Optional.empty();
        }
    }


    public Optional<UserProfileDto> getUserById(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserProfileDto userProfileDTO = new UserProfileDto();
            userProfileDTO.setUsername(user.getUsername());
            userProfileDTO.setEmail(user.getEmail());
            userProfileDTO.setFirstName(user.getFirstName());
            userProfileDTO.setLastName(user.getLastName());
            userProfileDTO.setPhoneNumber(user.getPhoneNumber());
            userProfileDTO.setRegistrationDate(user.getRegistrationDate());

            return Optional.of(userProfileDTO);
        }

        return Optional.empty();
    }

    public UserActivityStatusResponse getUserActivityStatusByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserActivityStatusResponse(user.getId(), user.getPlanStatus());
    }

}
