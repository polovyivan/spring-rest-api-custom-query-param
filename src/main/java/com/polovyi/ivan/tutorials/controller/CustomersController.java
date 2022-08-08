package com.polovyi.ivan.tutorials.controller;

import com.polovyi.ivan.tutorials.dto.CityStateRequestParam;
import com.polovyi.ivan.tutorials.dto.CustomerResponse;
import com.polovyi.ivan.tutorials.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class CustomersController {

    private final CustomerService customerService;

    @GetMapping(path = "/v1/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAllCustomers(
            @RequestParam(required = false, name = "cities") @Valid CityStateRequestParam cityStateRequestParam) {
        return customerService.findAllCustomers(cityStateRequestParam);
    }

}
