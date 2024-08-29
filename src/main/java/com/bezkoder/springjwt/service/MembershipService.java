package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Membership;
import com.bezkoder.springjwt.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    public String getMembershipNameByMid(Long mid) {
        return membershipRepository.findById(mid)
                .map(Membership::getMembershipPlan) // Assuming your Membership entity has a getName() method
                .orElse("No Membership"); // Default value if membership not found
    }
}
