package com.uthsob.DaanKori.repository;

import com.uthsob.DaanKori.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByIsActiveTrue();
    List<Campaign> findByCreatorId(Long creatorId);
} 