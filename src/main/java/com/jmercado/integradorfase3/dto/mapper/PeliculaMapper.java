package com.jmercado.integradorfase3.dto.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jmercado.integradorfase3.dto.PeliculaDTO;
import com.jmercado.integradorfase3.dto.ResumenPeliculaDTO;
import com.jmercado.integradorfase3.entity.Genero;
import com.jmercado.integradorfase3.entity.ImagenPelicula;
import com.jmercado.integradorfase3.entity.Pelicula;
import com.jmercado.integradorfase3.repository.IGeneroRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PeliculaMapper {

    private IGeneroRepository generoRepository;

    public Pelicula peliculaDTOtoPelicula(PeliculaDTO peliculaDTO){
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(pelicula.getTitulo());
        pelicula.setUrlWeb(pelicula.getUrlWeb());
        ImagenPelicula imagenPelicula = new ImagenPelicula();
        imagenPelicula.setNombreArchivo(peliculaDTO.getNombreImagen());
        imagenPelicula.setImagenArchivo(peliculaDTO.getImagenPelicula());
        pelicula.setImagenPelicula(imagenPelicula);
        Set<Genero> generos = new HashSet<>();

        for(String g: peliculaDTO.getGeneros()){
            generos.add(generoRepository.findByNombreGenero(g));
        }

        pelicula.setGeneros(generos);
        return pelicula;
    }

    public ResumenPeliculaDTO peluculaToResumenPeliculaDTO(Pelicula pelicula){
        ResumenPeliculaDTO resumenPeliculaDTO = new ResumenPeliculaDTO();
        resumenPeliculaDTO.setNombreImagen(pelicula.getImagenPelicula().getNombreArchivo());
        String generosString = pelicula.getGeneros().stream()
                                                    .map(g -> g.getNombreGenero())
                                                    .collect(Collectors.joining(" - "));
        resumenPeliculaDTO.setStringGeneros(generosString);
        resumenPeliculaDTO.setTitulo(pelicula.getTitulo());
        resumenPeliculaDTO.setUrlWeb(pelicula.getUrlWeb());
        return resumenPeliculaDTO;
    }

    public PeliculaDTO peliculaToPeliculaDTO(Pelicula pelicula) {
		PeliculaDTO peliculaDTO = new PeliculaDTO();
		List<String> generosString = pelicula.getGeneros().stream()
						.map(g->g.getNombreGenero()).collect(Collectors.toList());
		peliculaDTO.setGeneros(generosString);
		peliculaDTO.setImagenPelicula(pelicula.getImagenPelicula().getImagenArchivo());
		peliculaDTO.setTitulo(pelicula.getTitulo());
		peliculaDTO.setUrlWeb(pelicula.getUrlWeb());
		return peliculaDTO;
	}

}
