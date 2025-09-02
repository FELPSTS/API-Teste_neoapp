package com.example.ApiTeste.repository;

import com.example.ApiTeste.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    
    @Query("SELECT c FROM Cliente c WHERE " +
           "LOWER(c.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "c.cpf LIKE CONCAT('%', :termo, '%') OR " +
           "LOWER(c.telefone) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Cliente> search(@Param("termo") String termo, Pageable pageable);
}