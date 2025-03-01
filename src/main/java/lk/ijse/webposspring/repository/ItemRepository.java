package lk.ijse.webposspring.repository;

import lk.ijse.webposspring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository <Item, String>{
}