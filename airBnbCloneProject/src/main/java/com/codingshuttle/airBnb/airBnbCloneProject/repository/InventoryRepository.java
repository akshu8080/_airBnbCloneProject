package com.codingshuttle.airBnb.airBnbCloneProject.repository;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface InventoryRepository extends JpaRepository<Inventory,Long> {

}
