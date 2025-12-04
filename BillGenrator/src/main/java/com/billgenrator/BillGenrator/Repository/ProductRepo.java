package com.billgenrator.BillGenrator.Repository;

import com.billgenrator.BillGenrator.Model.Products;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Products, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update tblproducts set quantity = quantity + ?2 where tblproducts.p_id = ?1 ", nativeQuery = true)
    public void updateStock(int pid, int stock);
}
