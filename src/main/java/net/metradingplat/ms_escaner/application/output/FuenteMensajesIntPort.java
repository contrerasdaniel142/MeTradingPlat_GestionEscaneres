package net.metradingplat.ms_escaner.application.output;

import java.util.List;
import java.util.Locale;

import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEntidadMercado.DTOAnswer.MercadoDTORespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOAnswer.EscanerDTORespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOAnswer.TipoEjecucionDTORespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOAnswer.CategoriaDTORespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOAnswer.FiltroDtoRespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOAnswer.ParametroDTORespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOAnswer.ValorDTORespuesta;

public interface FuenteMensajesIntPort {

    // Mensajes
    String obtenerMensaje(String llaveMensaje, Locale locale, Object... args);

    // Mercado
    MercadoDTORespuesta internacionalizarMercado(MercadoDTORespuesta objeto);
    List<MercadoDTORespuesta> internacionalizarMercados(List<MercadoDTORespuesta> objetos);

    // Escáner
    EscanerDTORespuesta internacionalizarEscaner(EscanerDTORespuesta objeto);
    List<EscanerDTORespuesta> internacionalizarEscaneres(List<EscanerDTORespuesta> objetos);

    // Tipo de ejecución
    TipoEjecucionDTORespuesta internacionalizarTipoEjecucion(TipoEjecucionDTORespuesta objeto);

    // Filtros
    FiltroDtoRespuesta internacionalizarFiltro(FiltroDtoRespuesta objeto);
    List<FiltroDtoRespuesta> internacionalizarFiltros(List<FiltroDtoRespuesta> objetos);
    CategoriaDTORespuesta internacionalizarCategoria(CategoriaDTORespuesta objeto);
    List<CategoriaDTORespuesta> internacionalizarCategorias(List<CategoriaDTORespuesta> objeto);

    // Parámetros
    ParametroDTORespuesta internacionalizarParametro(ParametroDTORespuesta objeto);
    List<ParametroDTORespuesta> internacionalizarParametros(List<ParametroDTORespuesta> objetos);

    // Valores
    ValorDTORespuesta internacionalizarValor(ValorDTORespuesta objeto);
    List<ValorDTORespuesta> internacionalizarValores(List<ValorDTORespuesta> objetos);
}