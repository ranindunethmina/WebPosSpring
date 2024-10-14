package lk.ijse.webposspring.dao;

import lk.ijse.webposspring.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao extends JpaRepository <OrderDetailEntity, String> {
}
