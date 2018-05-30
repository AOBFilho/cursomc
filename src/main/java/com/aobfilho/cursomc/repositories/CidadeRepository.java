package com.aobfilho.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aobfilho.cursomc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

	@Transactional(readOnly=true)
	@Query(value="SELECT obj FROM Cidade obj WHERE obj.estado.id = :idEstado ORDER BY obj.nome")
    public List<Cidade> findCidades(@Param("idEstado") Integer estado_id);
	
}
