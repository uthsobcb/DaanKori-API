package com.uthsob.DaanKori.service;

import com.uthsob.DaanKori.model.Campaign;
import com.uthsob.DaanKori.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public Campaign createCampaign(Campaign campaign, Long creatorId) {
        campaign.setCreatorId(creatorId);
        campaign.setCurrentAmount(0.0);
        campaign.setCreatedAt(LocalDateTime.now());
        campaign.setActive(true);
        return campaignRepository.save(campaign);
    }

    public List<Campaign> getAllActiveCampaigns() {
        return campaignRepository.findByIsActiveTrue();
    }

    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
    }

    public List<Campaign> getCampaignsByCreator(Long creatorId) {
        return campaignRepository.findByCreatorId(creatorId);
    }

    public void deleteCampaign(Long id, Long userId) {
        Campaign campaign = getCampaignById(id);
        if (!campaign.getCreatorId().equals(userId)) {
            throw new RuntimeException("Not authorized to delete this campaign");
        }
        campaignRepository.delete(campaign);
    }

    public void updateCampaignAmount(Long campaignId, double amount) {
        Campaign campaign = getCampaignById(campaignId);
        campaign.setCurrentAmount(campaign.getCurrentAmount() + amount);
        if (campaign.getCurrentAmount() >= campaign.getTargetAmount()) {
            campaign.setActive(false);
        }
        campaignRepository.save(campaign);
    }
} 