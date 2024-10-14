package lk.ijse.webposspring.dao;

import lk.ijse.webposspring.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository <OrderEntity, String> {
}
