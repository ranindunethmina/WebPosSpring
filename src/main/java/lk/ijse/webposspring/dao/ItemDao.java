package lk.ijse.webposspring.dao;

import lk.ijse.webposspring.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository <ItemEntity, String>{
}
