package com.example.automarket.repository;

import com.example.automarket.domain.Carro;
import jakarta.persistence.LockModeType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT count(c) FROM Carro c")
    long countWithLock();
}
