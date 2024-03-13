package com.jmercado.integradorfase3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmercado.integradorfase3.entity.Genero;

@Repository
public interface IGeneroRepository extends JpaRepository<Genero, Integer>{
    Genero findByNombreGenero(String nombreGenero);
}
