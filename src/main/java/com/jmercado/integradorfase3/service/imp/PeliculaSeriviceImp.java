package com.jmercado.integradorfase3.service.imp;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmercado.integradorfase3.dto.PeliculaDTO;
import com.jmercado.integradorfase3.dto.ResumenPeliculaDTO;
import com.jmercado.integradorfase3.dto.mapper.PeliculaMapper;
import com.jmercado.integradorfase3.entity.Pelicula;
import com.jmercado.integradorfase3.repository.IPeliculaRepository;
import com.jmercado.integradorfase3.service.IPeliculaService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PeliculaSeriviceImp implements IPeliculaService{
    
    private PeliculaMapper peliculaMapper;
    private IPeliculaRepository peliculaRepository;

    @Override
    public ResumenPeliculaDTO registrarPelicula(String movie, MultipartFile archivoImagen) {
        ResumenPeliculaDTO resumenPeliculaDTO = new ResumenPeliculaDTO();
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        
        // mapeo de los parametros al DTO Pelicula
        try {
            peliculaDTO = objectMapper.readValue(movie, PeliculaDTO.class);
            peliculaDTO.setImagenPelicula(archivoImagen.getBytes());

            // mapeo del DTO a la entidad
            Pelicula pelicula = peliculaMapper.peliculaDTOtoPelicula(peliculaDTO);

            Pelicula peliculaRegistrada = peliculaRepository.save(pelicula);

            resumenPeliculaDTO = peliculaMapper.peluculaToResumenPeliculaDTO(peliculaRegistrada);

        }catch( JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resumenPeliculaDTO;
    }

    @Override
	public List<PeliculaDTO> buscarPorTitulo(String titulo) {
		List<Pelicula> peliculas = peliculaRepository.findByTituloContainingIgnoreCase(titulo);
		System.out.println("el repository se lleva a cabo");
		List<PeliculaDTO> peliculasDTO = peliculas.stream()
				.map(p->{
					PeliculaDTO peliculaDTO = peliculaMapper.peliculaToPeliculaDTO(p);
					return peliculaDTO;
				}).collect(Collectors.toList());
				
		return peliculasDTO;
	}
        
}

