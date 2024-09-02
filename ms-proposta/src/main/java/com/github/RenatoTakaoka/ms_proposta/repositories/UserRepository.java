package com.github.RenatoTakaoka.ms_proposta.repositories;

import com.github.RenatoTakaoka.ms_proposta.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.propostas")
    List<User> findAllWithPropostas();

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.propostas WHERE u.id = :id")
    Optional<User> findByIdWithPropostas(@Param("id") Long id);


}
