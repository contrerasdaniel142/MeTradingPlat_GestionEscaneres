package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import net.metradingplat.ms_escaner.domain.models.Escaner;
import net.metradingplat.ms_escaner.domain.models.EstadoEscaner;
import net.metradingplat.ms_escaner.domain.models.Mercado;
import net.metradingplat.ms_escaner.domain.models.TipoEjecucion;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEntidadMercado.DTOAnswer.MercadoDTORespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOAnswer.EscanerDTORespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOAnswer.EstadoEscanerDTORespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOAnswer.TipoEjecucionDTORespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOPetition.EscanerDTOPeticion;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOPetition.MercadoDTOPeticion;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOPetition.TipoEjecucionDTOPeticion;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EscanerMapperInfraestructuraDominio {

    // --- Escaner ---
    Escaner mappearDePeticionAEscaner(EscanerDTOPeticion peticion);
    EscanerDTORespuesta mappearDeEscanerARespuesta(Escaner escaner);
    List<EscanerDTORespuesta> mappearListaDeEscanerARespuesta(List<Escaner> escaneres);

    // --- EstadoEscaner ---
    EstadoEscanerDTORespuesta mappearDeEstadoEscanerARespuesta(EstadoEscaner estado);

    // --- TipoEjecucion ---
    TipoEjecucion mappearDePeticionATipoEjecucion(TipoEjecucionDTOPeticion peticion);
    TipoEjecucionDTORespuesta mappearDeTipoEjecucionARespuesta(TipoEjecucion tipo);
    List<TipoEjecucionDTORespuesta> mappearListaDeTipoEjecucionARespuesta(List<TipoEjecucion> tipos);

    // --- Mercado ---
    Mercado mappearDePeticionAMercado(MercadoDTOPeticion peticion);
    MercadoDTORespuesta mappearDeMercadoARespuesta(Mercado mercado);
    List<MercadoDTORespuesta> mappearListaDeMercadoARespuesta(List<Mercado> mercados);
}
