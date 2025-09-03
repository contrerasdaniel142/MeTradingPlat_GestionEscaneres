package net.metradingplat.ms_escaner.domain.strategies.filtros;

import java.util.List;
import java.util.Map;

import net.metradingplat.ms_escaner.domain.enums.EnumCategoriaFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumParametro;
import net.metradingplat.ms_escaner.domain.models.Filtro;
import net.metradingplat.ms_escaner.domain.models.Valor;
import net.metradingplat.ms_escaner.domain.strategies.validacion.ResultadoValidacion;

public interface IEstrategiaFiltro {
    public EnumFiltro obtenerEnumFiltro();
    public EnumCategoriaFiltro obtenerEnumCategoria();
    public Filtro obtenerFiltro();
    public Filtro obtenerInformacionFiltro();
    public Filtro obtenerFiltro(Map<EnumParametro, Valor> valoresSeleccionados);
    public List<ResultadoValidacion> validarValoresSeleccionados(Map<EnumParametro, Valor> valoresSeleccionados);
}