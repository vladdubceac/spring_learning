package md.vladdubceac.spring_learning.repositories;

import md.vladdubceac.spring_learning.models.Item;
import md.vladdubceac.spring_learning.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {

    List<Item> findByItemName(String itemName);

    List<Item> findByOwner(Person owner);
}
