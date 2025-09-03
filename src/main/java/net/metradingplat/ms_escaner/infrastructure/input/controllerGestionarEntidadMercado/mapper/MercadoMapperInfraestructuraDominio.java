package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEntidadMercado.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import net.metradingplat.ms_escaner.domain.models.Mercado;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEntidadMercado.DTOAnswer.MercadoDTORespuesta;

import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOPetition.MercadoDTOPeticion;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MercadoMapperInfraestructuraDominio {

    // --- Petición -> Dominio ---
    Mercado mappearDePeticionAMercado(MercadoDTOPeticion peticion);
    List<Mercado> mappearListaDePeticionAMercado(List<MercadoDTOPeticion> peticiones);

    // --- Dominio -> Respuesta ---
    MercadoDTORespuesta mappearDeMercadoARespuesta(Mercado mercado);
    List<MercadoDTORespuesta> mappearListaDeMercadoARespuesta(List<Mercado> mercados);
}