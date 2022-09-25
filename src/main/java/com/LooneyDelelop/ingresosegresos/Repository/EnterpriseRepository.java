package com.LooneyDelelop.ingresosegresos.Repository;

import com.LooneyDelelop.ingresosegresos.Entities.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository <Enterprise,Long> {
}
