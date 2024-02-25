package com.nicolasMorales.IncomeService.repository;

import com.nicolasMorales.IncomeService.models.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IIncomeRepository extends JpaRepository<Income, UUID> {


}
