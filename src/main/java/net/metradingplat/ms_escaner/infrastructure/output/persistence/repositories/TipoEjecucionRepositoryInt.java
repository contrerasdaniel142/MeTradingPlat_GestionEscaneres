package net.metradingplat.ms_escaner.infrastructure.output.persistence.repositories;

import net.metradingplat.ms_escaner.domain.enums.EnumTipoEjecucion;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.TipoEjecucionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoEjecucionRepositoryInt extends JpaRepository<TipoEjecucionEntity, Long> {
    Optional<TipoEjecucionEntity> findByEnumTipoEjecucion(EnumTipoEjecucion enumTipoEjecucion);
}