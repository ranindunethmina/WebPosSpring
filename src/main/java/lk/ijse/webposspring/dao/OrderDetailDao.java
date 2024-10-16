package lk.ijse.webposspring.dao;

import lk.ijse.webposspring.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao extends JpaRepository <OrderDetails, String> {
}
