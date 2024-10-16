package lk.ijse.webposspring.dao;

import lk.ijse.webposspring.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersDao extends JpaRepository <Order, String> {
    Optional<Order> findTopByOrderByOrderIdDesc();
}