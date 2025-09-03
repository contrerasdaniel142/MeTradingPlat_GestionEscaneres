package net.metradingplat.ms_escaner.domain.strategies.filtros;

import net.metradingplat.ms_escaner.domain.enums.EnumCategoriaFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumParametro;
import net.metradingplat.ms_escaner.domain.enums.EnumTipoValor;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumCondicional;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumPuntoReferenciaPullback;
import net.metradingplat.ms_escaner.domain.enums.valores.IEnumValores;
import net.metradingplat.ms_escaner.domain.models.CategoriaFiltro;
import net.metradingplat.ms_escaner.domain.models.Filtro;
import net.metradingplat.ms_escaner.domain.models.Parametro;
import net.metradingplat.ms_escaner.domain.models.Valor;
import net.metradingplat.ms_escaner.domain.models.ValorCondicional;
import net.metradingplat.ms_escaner.domain.models.ValorFloat;
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

public class EstrategiaFiltroPercentagePullbackHighsLows implements IEstrategiaFiltro {
    private final EnumFiltro enumFiltro = EnumFiltro.PERCENTAGE_PULLBACK_HIGHS_LOWS;
    private final EnumCategoriaFiltro enumCategoria = EnumCategoriaFiltro.PRECIO_Y_MOVIMIENTO;

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
        parametros.add(this.crearParametroPuntoReferencia((ValorString)valoresSeleccionados.get(EnumParametro.PUNTO_REFERENCIA_PULLBACK)));
        parametros.add(this.crearParametroPorcentajeRetroceso((ValorFloat)valoresSeleccionados.get(EnumParametro.PORCENTAJE_RETROCESO_PULLBACK)));

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
        EnumTipoValor enumTipoValor = EnumTipoValor.FLOAT;
        List<Valor> opciones = this.obtenerOpciones(EnumCondicional.values(),enumTipoValor);
        ValorCondicional valor = new ValorCondicional(
            EnumCondicional.MAYOR_QUE.getEtiqueta(),
            enumTipoValor,
            EnumCondicional.MAYOR_QUE,
            valorUsuario != null ? valorUsuario.getValor1() : 10.0F,
            valorUsuario != null ? valorUsuario.getValor2() : 50.0F
        );
        return new Parametro(EnumParametro.CONDICION,EnumParametro.CONDICION.getEtiqueta(), valor, opciones);
    }

    private Parametro crearParametroPuntoReferencia(ValorString valorUsuario) {
        EnumTipoValor enumTipoValor = EnumTipoValor.STRING;
        List<Valor> opciones = this.obtenerOpciones(EnumPuntoReferenciaPullback.values(), enumTipoValor);
        ValorString valor = new ValorString(
            EnumPuntoReferenciaPullback.ALTO.getEtiqueta(),
            enumTipoValor,
            valorUsuario != null ? valorUsuario.getValor() : EnumPuntoReferenciaPullback.ALTO.name()
        );
        return new Parametro(EnumParametro.PUNTO_REFERENCIA_PULLBACK, EnumParametro.PUNTO_REFERENCIA_PULLBACK.getEtiqueta(), valor, opciones);
    }

    private Parametro crearParametroPorcentajeRetroceso(ValorFloat valorUsuario) {
        EnumTipoValor enumTipoValor = EnumTipoValor.FLOAT;
        List<Valor> opciones = this.obtenerOpciones(EnumCondicional.values(),enumTipoValor);
        ValorFloat valor = new ValorFloat(
            "etiqueta.vacia",
            enumTipoValor,
            valorUsuario != null ? valorUsuario.getValor() : 5.0F
        );
        return new Parametro(EnumParametro.PORCENTAJE_RETROCESO_PULLBACK, EnumParametro.PORCENTAJE_RETROCESO_PULLBACK.getEtiqueta(), valor, opciones);
    }

    @Override
    public List<ResultadoValidacion> validarValoresSeleccionados(Map<EnumParametro, Valor> valoresSeleccionados) {
        List<ResultadoValidacion> errores = new ArrayList<>();

        this.objValidador.validarCondicional(EnumParametro.CONDICION, valoresSeleccionados.get(EnumParametro.CONDICION), 5.0F, 90.0F)
                .ifPresent(errores::add);

        this.objValidador.validarString(EnumParametro.PUNTO_REFERENCIA_PULLBACK,
                valoresSeleccionados.get(EnumParametro.PUNTO_REFERENCIA_PULLBACK),
                EnumPuntoReferenciaPullback.class)
                .ifPresent(errores::add);

        this.objValidador.valdiarFloat(EnumParametro.PORCENTAJE_RETROCESO_PULLBACK,
                valoresSeleccionados.get(EnumParametro.PORCENTAJE_RETROCESO_PULLBACK), 5.0F, 90.0F)
                .ifPresent(errores::add);

        return errores;
    }
}