package com.uthsob.DaanKori.controller;

import com.uthsob.DaanKori.model.Campaign;
import com.uthsob.DaanKori.model.User;
import com.uthsob.DaanKori.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @PostMapping
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Campaign createdCampaign = campaignService.createCampaign(campaign, userId);
        return ResponseEntity.ok(createdCampaign);
    }

    @GetMapping
    public ResponseEntity<List<Campaign>> getAllActiveCampaigns() {
        List<Campaign> campaigns = campaignService.getAllActiveCampaigns();
        return ResponseEntity.ok(campaigns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long id) {
        Campaign campaign = campaignService.getCampaignById(id);
        return ResponseEntity.ok(campaign);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Campaign>> getUserCampaigns() {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Campaign> campaigns = campaignService.getCampaignsByCreator(userId);
        return ResponseEntity.ok(campaigns);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Long id) {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        campaignService.deleteCampaign(id, userId);
        return ResponseEntity.ok().build();
    }
} 