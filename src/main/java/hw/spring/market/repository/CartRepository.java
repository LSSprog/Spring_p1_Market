package hw.spring.market.repository;

import hw.spring.market.beans.Cart_v3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart_v3, UUID> {
}
