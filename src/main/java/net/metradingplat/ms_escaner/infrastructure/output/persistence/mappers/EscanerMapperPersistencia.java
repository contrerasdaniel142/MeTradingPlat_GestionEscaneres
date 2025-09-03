package net.metradingplat.ms_escaner.infrastructure.output.persistence.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import net.metradingplat.ms_escaner.domain.models.Escaner;
import net.metradingplat.ms_escaner.domain.models.EstadoEscaner;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.EstadoEscanerEntity;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.EscanerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EscanerMapperPersistencia {

    Escaner mappearDeEntityAEscaner(EscanerEntity entity);

    @Mapping(target = "filtros", ignore = true)
    EscanerEntity mappearDeEscanerAEntity(Escaner escaner);

    @Mapping(target = "idEscaner", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "objEstado", ignore = true)
    @Mapping(target = "filtros", ignore = true)
    void actualizarEntityDesdeEscaner(Escaner escaner, @MappingTarget EscanerEntity entity);

    List<Escaner> mappearListaDeEntityAEscaner(List<EscanerEntity> entities);

    EstadoEscaner mappearDeEstadoEntityAEstado(EstadoEscanerEntity entity);

    EstadoEscanerEntity mappearDeEstadoAEstadoEntity(EstadoEscaner estado);
}
