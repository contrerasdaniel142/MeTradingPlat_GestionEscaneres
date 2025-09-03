package net.metradingplat.ms_escaner.domain.strategies.filtros;

import net.metradingplat.ms_escaner.domain.enums.EnumCategoriaFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumParametro;
import net.metradingplat.ms_escaner.domain.enums.EnumTipoValor;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumCondicional;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumTimeframe;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumTipoVolumen;
import net.metradingplat.ms_escaner.domain.enums.valores.IEnumValores;
import net.metradingplat.ms_escaner.domain.models.CategoriaFiltro;
import net.metradingplat.ms_escaner.domain.models.Filtro;
import net.metradingplat.ms_escaner.domain.models.Parametro;
import net.metradingplat.ms_escaner.domain.models.Valor;
import net.metradingplat.ms_escaner.domain.models.ValorCondicional;
import net.metradingplat.ms_escaner.domain.models.ValorString;
import net.metradingplat.ms_escaner.domain.strategies.ServicioValidacionFiltro;
import net.metradingplat.ms_escaner.domain.strategies.validacion.ResultadoValidacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

public class EstrategiaFiltroVolume implements IEstrategiaFiltro {
    private final EnumFiltro enumFiltro = EnumFiltro.VOLUME;
    private final EnumCategoriaFiltro enumCategoria = EnumCategoriaFiltro.VOLUMEN;

    @Autowired
    private ServicioValidacionFiltro objValidador;

    @Override
    public EnumFiltro obtenerEnumFiltro() {
        return this.enumFiltro;
    }

    @Override
    public EnumCategoriaFiltro obtenerEnumCategoria() {
        return this.enumCategoria;
    }

    @Override
    public Filtro obtenerFiltro() {
        return this.obtenerFiltro(new HashMap<>());
    }

    @Override
    public Filtro obtenerInformacionFiltro(){
        Filtro filtro = new Filtro();
        filtro.setEnumFiltro(this.enumFiltro);
        filtro.setEtiquetaNombre(this.enumFiltro.getEtiquetaNombre());
        filtro.setEtiquetaDescripcion(this.enumFiltro.getEtiquetaDescripcion());
        
        CategoriaFiltro objCategoriaFiltro = new CategoriaFiltro();
        objCategoriaFiltro.setEnumCategoriaFiltro(this.enumCategoria);
        objCategoriaFiltro.setEtiqueta(this.enumCategoria.getEtiqueta());

        filtro.setObjCategoriaFiltro(objCategoriaFiltro);

        return filtro;
    }

    @Override
    public Filtro obtenerFiltro(Map<EnumParametro, Valor> valoresSeleccionados) {
        Filtro filtro = this.obtenerInformacionFiltro();
        
        List<Parametro> parametros = new ArrayList<>();
        parametros.add(this.crearParametroCondicion((ValorCondicional)valoresSeleccionados.get(EnumParametro.CONDICION)));
        parametros.add(this.crearParametroTipoVolumen((ValorString)valoresSeleccionados.get(EnumParametro.TIPO_VOLUMEN)));
        parametros.add(this.crearParametroTimeframe((ValorString)valoresSeleccionados.get(EnumParametro.TIMEFRAME_VOLUME)));

        filtro.setParametros(parametros);
        return filtro;
    }

    private List<Valor> obtenerOpciones(IEnumValores[] enumValores, EnumTipoValor tipoValor) {
        List<Valor> opciones = Arrays.stream(enumValores)
            .map(e -> new Valor(e.getEtiqueta(), tipoValor))
            .collect(Collectors.toList());

        if (opciones.isEmpty()) {
            opciones.add(new Valor("etiqueta.vacia", tipoValor));
        }

        return opciones;
    }

    private Parametro crearParametroCondicion(ValorCondicional valorUsuario) {
        EnumTipoValor enumTipoValor = EnumTipoValor.INTEGER;
        List<Valor> opciones = this.obtenerOpciones(EnumCondicional.values(), enumTipoValor);
        ValorCondicional valor = new ValorCondicional(
            EnumCondicional.MAYOR_QUE.getEtiqueta(),
            enumTipoValor,
            EnumCondicional.MAYOR_QUE,
            valorUsuario != null ? valorUsuario.getValor1() : 100_000,
            valorUsuario != null ? valorUsuario.getValor2() : 5_000_000
        );
        return new Parametro(EnumParametro.CONDICION,EnumParametro.CONDICION.getEtiqueta(), valor, opciones);
    }

    private Parametro crearParametroTipoVolumen(ValorString valorUsuario) {
        EnumTipoValor enumTipoValor = EnumTipoValor.STRING;
        List<Valor> opciones = this.obtenerOpciones(EnumTipoVolumen.values(), enumTipoValor);
        ValorString valor = new ValorString(
            EnumTipoVolumen.CIERRE.getEtiqueta(),
            enumTipoValor,
            valorUsuario != null ? valorUsuario.getValor() : EnumTipoVolumen.CIERRE.name()
        );
        return new Parametro(EnumParametro.TIPO_VOLUMEN, EnumParametro.TIPO_VOLUMEN.getEtiqueta(), valor, opciones);
    }

    private Parametro crearParametroTimeframe(ValorString valorUsuario) {
        EnumTipoValor enumTipoValor = EnumTipoValor.STRING;
        List<Valor> opciones = this.obtenerOpciones(EnumTimeframe.values(), enumTipoValor);
        ValorString valor = new ValorString(
            EnumTimeframe._1D.getEtiqueta(),
            enumTipoValor,
            valorUsuario != null ? valorUsuario.getValor() : EnumTimeframe._1D.name()
        );
        return new Parametro(EnumParametro.TIMEFRAME_VOLUME, EnumParametro.TIMEFRAME_VOLUME.getEtiqueta(), valor, opciones);
    }

    @Override
    public List<ResultadoValidacion> validarValoresSeleccionados(Map<EnumParametro, Valor> valoresSeleccionados) {
        List<ResultadoValidacion> errores = new ArrayList<>();

        this.objValidador.validarCondicional(EnumParametro.CONDICION, valoresSeleccionados.get(EnumParametro.CONDICION), 0.0F,
                1_000_000_000.0F)
                .ifPresent(errores::add);

        this.objValidador.validarString(EnumParametro.TIPO_VOLUMEN, valoresSeleccionados.get(EnumParametro.TIPO_VOLUMEN),
                EnumTipoVolumen.class)
                .ifPresent(errores::add);

        List<EnumTimeframe> allowedTimeframesVolume = Arrays.asList(
            EnumTimeframe._1M, EnumTimeframe._2M, EnumTimeframe._3M, EnumTimeframe._5M, EnumTimeframe._10M,
            EnumTimeframe._15M, EnumTimeframe._30M, EnumTimeframe._45M, EnumTimeframe._1H, EnumTimeframe._2H,
            EnumTimeframe._3H, EnumTimeframe._4H, EnumTimeframe._12H, EnumTimeframe._1D, EnumTimeframe._2D,
            EnumTimeframe._3D
        );
        this.objValidador.validarStringConOpciones(EnumParametro.TIMEFRAME_VOLUME,
                valoresSeleccionados.get(EnumParametro.TIMEFRAME_VOLUME), allowedTimeframesVolume)
                .ifPresent(errores::add);
        
        return errores;
    }
}