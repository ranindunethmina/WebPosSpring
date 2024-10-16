package lk.ijse.webposspring.dao;

import lk.ijse.webposspring.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDao extends JpaRepository <Order, String> {
}
