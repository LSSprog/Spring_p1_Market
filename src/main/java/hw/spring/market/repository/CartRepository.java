package hw.spring.market.repository;

import hw.spring.market.model.Cart_v3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart_v3, UUID> {
    @Query("SELECT c FROM Cart_v3 c WHERE c.user.id = ?1")
    Optional<Cart_v3> findByUserId(Long id);
}
