package com.enigma.enigmatboot.spesification;

import com.enigma.enigmatboot.dto.CustomerSearchDTO;
import com.enigma.enigmatboot.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpesification {
    public static Specification<Customer> getSpesification(CustomerSearchDTO customerSearchDTO){
        return new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if(!(customerSearchDTO.getSearchCustomerFirstName() == null)){
                    Predicate customerFirstNamePredicate = criteriaBuilder.like(root.get("firstName"), "%" + customerSearchDTO.getSearchCustomerFirstName() + "%");
                    predicates.add(customerFirstNamePredicate);
                }
                if(!(customerSearchDTO.getSearchCustomerLastName() == null)){
                    Predicate customerLastNamePredicate = criteriaBuilder.like(root.get("lastName"), "%" + customerSearchDTO.getSearchCustomerLastName() + "%");
                    predicates.add(customerLastNamePredicate);
                }
                if(!(customerSearchDTO.getSearchCustomerDateOfBirth() == null)){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String modifiedDateFormated = sdf.format(customerSearchDTO.getSearchCustomerDateOfBirth());

                    Predicate createDatePredicate = criteriaBuilder.equal(criteriaBuilder.function("TO_CHAR", String.class, root.get("dateOfBirth"),
                            criteriaBuilder.literal("yyyy-MM-dd")), modifiedDateFormated);
                    predicates.add(createDatePredicate);
                }

                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);

            }
        };
    }
}
