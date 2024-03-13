package com.jmercado.integradorfase3.service;

import java.util.List;

import com.jmercado.integradorfase3.dto.PeliculaDTO;


public interface IGeneroService {
	List<PeliculaDTO> obtenerPorGenero(String genero);

}