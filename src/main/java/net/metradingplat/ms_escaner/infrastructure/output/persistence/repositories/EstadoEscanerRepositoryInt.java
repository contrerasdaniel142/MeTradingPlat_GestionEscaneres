package net.metradingplat.ms_escaner.infrastructure.output.persistence.repositories;

import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.EstadoEscanerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoEscanerRepositoryInt extends JpaRepository<EstadoEscanerEntity, Long> {

}