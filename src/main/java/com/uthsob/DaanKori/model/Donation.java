package com.uthsob.DaanKori.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "campaign_id", nullable = false)
    private Long campaignId;

    @Column(name = "donor_id", nullable = false)
    private Long donorId;

    @Column(nullable = false)
    private double amount;

    @Column(name = "donated_at", nullable = false)
    private LocalDateTime donatedAt;
} 