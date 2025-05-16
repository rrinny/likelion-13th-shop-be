package com.likelion13th.shop.repository;

import com.likelion13th.shop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndItemId(Long cartItemID, Long itemID);
}
