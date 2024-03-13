package com.jmercado.integradorfase3.peliculas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jmercado.integradorfase3.entity.Genero;
import com.jmercado.integradorfase3.entity.ImagenPelicula;
import com.jmercado.integradorfase3.entity.Pelicula;
import com.jmercado.integradorfase3.model.MapperFiles;
import com.jmercado.integradorfase3.repository.IGeneroRepository;
import com.jmercado.integradorfase3.repository.IPeliculaRepository;

@SpringBootTest
public class RegistroPeliculaTest {
   
    @Autowired
    private IGeneroRepository generoRepository;
    @Autowired
    private MapperFiles mapperFiles;
    @Autowired
    private IPeliculaRepository peliculaRepository;
   
    @Test
    public void validarRegistroPelicula() throws IOException{
        // Precondicio: Los Generos Existen
        Genero genero = new Genero();
        genero.setNombreGenero("genero1");
        Genero genero1 = generoRepository.save(genero);
        
        genero = new Genero();
        genero.setNombreGenero("genero2");
        Genero genero2 = generoRepository.save(genero);

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Titulo01");
        pelicula.setUrlWeb("url web pelicula 01");

        Set<Genero> generos = new HashSet<>();
        generos.add(genero1);
        generos.add(genero2);

        pelicula.setGeneros(generos);

        ImagenPelicula imagenPelicula = new ImagenPelicula();
        imagenPelicula.setNombreArchivo("marioBros.png");
        imagenPelicula.setImagenArchivo(mapperFiles.inputStreamToByteArray(getClass()
                                                    .getResourceAsStream("./resources/marioBros.png")));

        pelicula.setImagenPelicula(imagenPelicula);
        Pelicula peliculaGuardada = peliculaRepository.save(pelicula);

        List<Pelicula> peliculas = peliculaRepository.findAll();
        assertTrue(!peliculas.isEmpty() && peliculas.get(0).getId().equals(peliculaGuardada.getId()));

        peliculaRepository.deleteAll();
        generoRepository.deleteAll();

        assertTrue(peliculaRepository.findAll().isEmpty() && generoRepository.findAll().isEmpty());
    }
}
