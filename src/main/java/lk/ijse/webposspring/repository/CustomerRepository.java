package lk.ijse.webposspring.repository;

import lk.ijse.webposspring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, String> {
}