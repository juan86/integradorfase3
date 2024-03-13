package com.jmercado.integradorfase3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmercado.integradorfase3.entity.Usuario;


public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByEmail(String email);

}
