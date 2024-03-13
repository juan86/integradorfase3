package com.jmercado.integradorfase3.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.jmercado.integradorfase3.dto.PeliculaDTO;
import com.jmercado.integradorfase3.dto.ResumenPeliculaDTO;

public interface IPeliculaService {   
    ResumenPeliculaDTO registrarPelicula(String movie, MultipartFile archivoImagen);
    List<PeliculaDTO> buscarPorTitulo(String titulo);
}
