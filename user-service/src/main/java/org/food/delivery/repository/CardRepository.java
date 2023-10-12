package org.food.delivery.repository;

import org.food.delivery.entity.CARD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CARD, String> {
}
