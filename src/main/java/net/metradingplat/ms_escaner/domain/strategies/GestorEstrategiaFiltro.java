package net.metradingplat.ms_escaner.domain.strategies;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import lombok.Getter;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumCategoriaFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumParametro;
import net.metradingplat.ms_escaner.domain.models.Filtro;
import net.metradingplat.ms_escaner.domain.models.Valor;
import net.metradingplat.ms_escaner.domain.strategies.filtros.IEstrategiaFiltro;
import net.metradingplat.ms_escaner.domain.strategies.validacion.ResultadoValidacion;

@Getter
@Setter
public class GestorEstrategiaFiltro {
    private Map<EnumFiltro, IEstrategiaFiltro> mapEnumFiltroIEstrategiaFiltro;
    private Map<EnumCategoriaFiltro, List<EnumFiltro>> mapCategoriaFiltros;

    public GestorEstrategiaFiltro(Set<IEstrategiaFiltro> filtros) {
        this.mapEnumFiltroIEstrategiaFiltro = new HashMap<>();
        this.mapCategoriaFiltros = new EnumMap<>(EnumCategoriaFiltro.class);

        for (IEstrategiaFiltro filtro : filtros) {
            this.mapEnumFiltroIEstrategiaFiltro.put(filtro.obtenerEnumFiltro(), filtro);
            this.mapCategoriaFiltros
                .computeIfAbsent(filtro.obtenerEnumCategoria(), k -> new ArrayList<>())
                .add(filtro.obtenerEnumFiltro());
        }
    }

    public List<EnumFiltro> obtenerFiltrosPorCategoria(EnumCategoriaFiltro categoria) {
        return this.mapCategoriaFiltros.getOrDefault(categoria, List.of(EnumFiltro.UNKNOWN));
    }

    public Filtro obtenerInfomracionFiltro(EnumFiltro tipoFiltro){
        return this.mapEnumFiltroIEstrategiaFiltro.get(tipoFiltro).obtenerInformacionFiltro();
    }

    public Filtro obtenerFiltroConValoresPorDefecto(EnumFiltro tipoFiltro){      
        return this.mapEnumFiltroIEstrategiaFiltro.get(tipoFiltro).obtenerFiltro();
    }

    public Filtro crearFiltroConValoresSeleccionados(EnumFiltro tipoFiltro, Map<EnumParametro,Valor> valores){
        return this.mapEnumFiltroIEstrategiaFiltro.get(tipoFiltro).obtenerFiltro(valores);

    }

    public Boolean validarEnumFiltro(EnumFiltro tipoFiltro){
        return this.mapEnumFiltroIEstrategiaFiltro.containsKey(tipoFiltro);
    }

    public List<ResultadoValidacion> validarValoresSeleccionados(EnumFiltro tipoFiltro, Map<EnumParametro,Valor> valores){
        return this.mapEnumFiltroIEstrategiaFiltro.get(tipoFiltro).validarValoresSeleccionados(valores);
    }
}
