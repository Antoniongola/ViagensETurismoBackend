package com.ngola.agenciaviagensbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String texto;
    @Transient
    private List<Media> medias = new ArrayList<>();
}