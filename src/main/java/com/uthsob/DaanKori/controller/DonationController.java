package com.uthsob.DaanKori.controller;

import com.uthsob.DaanKori.model.Donation;
import com.uthsob.DaanKori.model.User;
import com.uthsob.DaanKori.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @PostMapping
    public ResponseEntity<Donation> makeDonation(@RequestBody Map<String, Object> donationRequest) {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Long campaignId = Long.parseLong(donationRequest.get("campaignId").toString());
        double amount = Double.parseDouble(donationRequest.get("amount").toString());

        Donation donation = donationService.makeDonation(campaignId, userId, amount);
        return ResponseEntity.ok(donation);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Donation>> getUserDonations() {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Donation> donations = donationService.getDonationsByDonor(userId);
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<Donation>> getCampaignDonations(@PathVariable Long campaignId) {
        List<Donation> donations = donationService.getDonationsByCampaign(campaignId);
        return ResponseEntity.ok(donations);
    }
} 