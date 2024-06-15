package org.example.junitpractice.domain.dto;

import lombok.Builder;
import lombok.Data;
import org.example.junitpractice.domain.entity.ticketing.Performance;

import java.util.*;

@Data
@Builder
public class PerformanceInfo {
    private UUID performanceId;
    private String performanceName;
    private String performanceType;
    private int price;
    private Date startDate;
    private String isReserve;
    private List<PerformanceDiscountPolicyInfo> discountablePolicies;

    public static PerformanceInfo of(Performance entity) {
        return PerformanceInfo.builder()
            .performanceId(entity.getId())
            .performanceName(entity.getName())
            .performanceType(convertCodeToName(entity.getType()))
            .price(entity.getPrice())
            .startDate(entity.getStart_date())
            .isReserve(entity.getIsReserve())
            .discountablePolicies(PerformanceDiscountPolicyInfo.getAllDiscountPolicies(entity.getPolicies()))
            .build();
    }

    private static String convertCodeToName(int code){
        return Arrays.stream(PerformanceType.values()).filter(value -> value.getCategory() == code)
            .findFirst()
            .orElse(PerformanceType.NONE)
            .name();
    }

    public boolean canReservation(){
        return isReserve.equalsIgnoreCase("enable");
    }

}
