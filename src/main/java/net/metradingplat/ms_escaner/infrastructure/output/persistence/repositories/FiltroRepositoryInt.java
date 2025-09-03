package net.metradingplat.ms_escaner.infrastructure.output.persistence.repositories;

import java.util.List;

import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.FiltroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FiltroRepositoryInt extends JpaRepository<FiltroEntity, Long> {
       @Query("""
              SELECT f
              FROM FiltroEntity f
              JOIN f.objEscaner e
              WHERE e.idEscaner = :idEscaner
              """)
       List<FiltroEntity> findFiltrosByEscanerId(@Param("idEscaner") Long idEscaner);
}
