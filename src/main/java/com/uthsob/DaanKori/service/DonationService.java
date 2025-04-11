package com.uthsob.DaanKori.service;

import com.uthsob.DaanKori.model.Campaign;
import com.uthsob.DaanKori.model.Donation;
import com.uthsob.DaanKori.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private UserService userService;

    @Transactional
    public Donation makeDonation(Long campaignId, Long donorId, double amount) {
        // First deduct the amount from user's balance
        userService.deductBalance(donorId, amount);

        // Update campaign's current amount
        campaignService.updateCampaignAmount(campaignId, amount);

        // Create and save the donation record
        Donation donation = new Donation();
        donation.setCampaignId(campaignId);
        donation.setDonorId(donorId);
        donation.setAmount(amount);
        donation.setDonatedAt(LocalDateTime.now());

        return donationRepository.save(donation);
    }

    public List<Donation> getDonationsByDonor(Long donorId) {
        return donationRepository.findByDonorId(donorId);
    }

    public List<Donation> getDonationsByCampaign(Long campaignId) {
        return donationRepository.findByCampaignId(campaignId);
    }
} 