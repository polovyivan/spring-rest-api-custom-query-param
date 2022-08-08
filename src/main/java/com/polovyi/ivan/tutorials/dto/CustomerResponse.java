package com.polovyi.ivan.tutorials.dto;

import com.polovyi.ivan.tutorials.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private String id;

    private String fullName;

    private String phoneNumber;

    private String addressLine;

    private String city;

    private String state;

    private LocalDate createdAt;

    public static CustomerResponse valueOf(CustomerEntity customer) {
        return builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .phoneNumber(customer.getPhoneNumber())
                .addressLine(customer.getAddressLine())
                .city(customer.getCity())
                .state(customer.getState())
                .createdAt(customer.getCreatedAt())
                .build();
    }
}
