package com.ngola.agenciaviagensbackend.repository;

import com.ngola.agenciaviagensbackend.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

}
