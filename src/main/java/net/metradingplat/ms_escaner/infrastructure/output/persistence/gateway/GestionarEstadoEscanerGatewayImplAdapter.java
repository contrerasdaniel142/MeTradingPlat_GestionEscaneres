package net.metradingplat.ms_escaner.infrastructure.output.persistence.gateway;

import lombok.RequiredArgsConstructor;
import net.metradingplat.ms_escaner.application.output.GestionarEstadoEscanerGatewayIntPort;
import net.metradingplat.ms_escaner.domain.enums.EnumEstadoEscaner;
import net.metradingplat.ms_escaner.domain.models.Escaner;
import net.metradingplat.ms_escaner.domain.models.EstadoEscaner;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.EscanerEntity;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.EstadoEscanerEntity;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.mappers.EscanerMapperPersistencia;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.repositories.EscanerRepositoryInt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GestionarEstadoEscanerGatewayImplAdapter implements GestionarEstadoEscanerGatewayIntPort {

    private final EscanerRepositoryInt objEscanerRepository;
    private final EscanerMapperPersistencia objMapper;

    @Override
    @Transactional(readOnly = true)
    public EnumEstadoEscaner obtenerEstadoDeEscanerActual(Long id) {
        EscanerEntity entity = this.objEscanerRepository.findById(id).get();
        EnumEstadoEscaner estadoActual = entity.getObjEstado().getEnumEstadoEscaner();
        return estadoActual;
    }

    @Override
    @Transactional
    public EstadoEscaner cambiarEstadoDeEscaner(Escaner escaner, EnumEstadoEscaner nuevoEstadoEnum) {
        
        EscanerEntity escanerEntity = this.objEscanerRepository.findById(escaner.getIdEscaner()).get();

        EstadoEscanerEntity estadoEscanerEntity = escanerEntity.getObjEstado();
        if (estadoEscanerEntity == null) {
            estadoEscanerEntity = new EstadoEscanerEntity();
            estadoEscanerEntity.setIdEscaner(escaner.getIdEscaner());
            estadoEscanerEntity.setObjEscaner(escanerEntity);
            escanerEntity.setObjEstado(estadoEscanerEntity);
        }
        estadoEscanerEntity.setEnumEstadoEscaner(nuevoEstadoEnum);

        EscanerEntity updatedEntity = this.objEscanerRepository.save(escanerEntity);
        return this.objMapper.mappearDeEntityAEscaner(updatedEntity).getObjEstado();
    }
}