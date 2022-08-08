package com.polovyi.ivan.tutorials.service;

import com.polovyi.ivan.tutorials.dto.CityStateRequestParam;
import com.polovyi.ivan.tutorials.dto.CustomerResponse;
import com.polovyi.ivan.tutorials.entity.CustomerEntity;
import com.polovyi.ivan.tutorials.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public record CustomerService(EntityManager entityManager) {

    public List<CustomerResponse> findAllCustomers(CityStateRequestParam cityStateRequestParam) {
        log.info("Fetching all customers from cities {}", cityStateRequestParam);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerEntity> criteriaQuery = criteriaBuilder.createQuery(CustomerEntity.class);
        Root<CustomerEntity> root = criteriaQuery.from(CustomerEntity.class);

        criteriaQuery.select(root);

        Predicate[] cityStatePredicates = Optional.ofNullable(cityStateRequestParam)
                .map(CityStateRequestParam::getCitiesWithStates)
                .orElseGet(Collections::emptySet).stream()
                .map(cityWithState -> {
                    Predicate city = criteriaBuilder.equal(root.get("city"), cityWithState.getCity());
                    Predicate state = criteriaBuilder.equal(root.get("state"), cityWithState.getState());
                    return criteriaBuilder.and(state, city);
                })
                .toArray(Predicate[]::new);

        if (cityStatePredicates.length > 0) {
            Predicate orPredicate = criteriaBuilder.or(cityStatePredicates);
            criteriaQuery.where(orPredicate);
        }

        return entityManager.createQuery(criteriaQuery).getResultList().stream()
                .map(CustomerResponse::valueOf)
                .collect(Collectors.toList());
    }

}
