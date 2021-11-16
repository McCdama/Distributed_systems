package io.dis.myaktion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.dis.myaktion.domain.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    
}
