package com.jmercado.integradorfase3.entity;

import java.util.List;

import lombok.Data;

@Data
public class Pelicula {
    private Integer id;
    private String titulo;
    private String urlWeb;
    private List<Genero> generos;
    private ImagenPelicula imagen;
}
