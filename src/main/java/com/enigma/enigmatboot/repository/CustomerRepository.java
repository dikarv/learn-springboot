package com.enigma.enigmatboot.repository;

import com.enigma.enigmatboot.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    public List<Customer> findCustomerByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);
    Page<Customer> findAll(Specification<Customer> customerSpecification, Pageable pageable);

    @Query(value = "SELECT * FROM mst_customer c WHERE c.status = 1", nativeQuery = true)
    List<Customer> findActiveCustomer();

    @Query("SELECT c FROM Customer c WHERE c.status = 0 and c.firstName = ?1 and c.lastName = ?2")
    List<Customer> findNonActiveCustomer(String firstName, String lastName);

    @Modifying
    @Query("UPDATE Customer c SET c.status = 1 WHERE  c.id = :id")
    void updateCustomerStatus(@Param("id")String id);

    Optional<Customer> findByUserName(String username);

    Boolean existsByUserName(String username);
}
