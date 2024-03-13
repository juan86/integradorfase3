package com.jmercado.integradorfase3.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jmercado.integradorfase3.dto.PeliculaDTO;
import com.jmercado.integradorfase3.dto.mapper.PeliculaMapper;
import com.jmercado.integradorfase3.entity.Genero;
import com.jmercado.integradorfase3.repository.IGeneroRepository;
import com.jmercado.integradorfase3.service.IGeneroService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GeneroServiceImp implements IGeneroService{
	private IGeneroRepository generoRepository;
	private PeliculaMapper peliculaMapper;

	@Override
	public List<PeliculaDTO> obtenerPorGenero(String genero) {
		Genero generoBuscado = generoRepository.findByNombreGenero(genero);
		
		List<PeliculaDTO> peliculas = generoBuscado.getPeliculas().stream().map(
				p->{
					PeliculaDTO dto = peliculaMapper.peliculaToPeliculaDTO(p);
					return dto;
				}).collect(Collectors.toList());		
		return peliculas;
	}

}
