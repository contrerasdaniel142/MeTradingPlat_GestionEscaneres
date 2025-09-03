package net.metradingplat.ms_escaner.application.input;

import java.util.List;

import net.metradingplat.ms_escaner.domain.enums.EnumCategoriaFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumFiltro;
import net.metradingplat.ms_escaner.domain.models.CategoriaFiltro;
import net.metradingplat.ms_escaner.domain.models.Filtro;

public interface GestionarFiltroCUIntPort {
    public List<CategoriaFiltro> obtenerCategorias();
    public List<Filtro> obtenerFiltrosPorCategoria(EnumCategoriaFiltro enumCategoria);
    public Filtro obtenerFiltroPorDefecto(EnumFiltro enumFiltro);
    public List<Filtro> obtenerFiltros(Long idEscaner);
    public List<Filtro> guardarFiltros(Long idEscaner, List<Filtro> filtrosEscaner);
    public Boolean eliminarFitroGuardado(Long idFiltro);
}