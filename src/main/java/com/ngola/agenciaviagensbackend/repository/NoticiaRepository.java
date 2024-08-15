package com.ngola.agenciaviagensbackend.repository;

import com.ngola.agenciaviagensbackend.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

}
