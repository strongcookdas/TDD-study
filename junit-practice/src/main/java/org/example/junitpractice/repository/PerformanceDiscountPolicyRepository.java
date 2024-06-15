package org.example.junitpractice.repository;

import org.example.junitpractice.domain.entity.ticketing.PerformanceDiscountPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PerformanceDiscountPolicyRepository extends JpaRepository<PerformanceDiscountPolicy, UUID> {
    PerformanceDiscountPolicy findByPerformanceIdAndName(UUID performanceId, String policyName);
}
