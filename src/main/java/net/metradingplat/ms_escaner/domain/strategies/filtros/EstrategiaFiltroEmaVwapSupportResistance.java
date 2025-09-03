package net.metradingplat.ms_escaner.domain.strategies.filtros;

import net.metradingplat.ms_escaner.domain.enums.EnumCategoriaFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumParametro;
import net.metradingplat.ms_escaner.domain.enums.EnumTipoValor;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumLineaReferencia;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumTipoRol;
import net.metradingplat.ms_escaner.domain.enums.valores.IEnumValores;
import net.metradingplat.ms_escaner.domain.models.CategoriaFiltro;
import net.metradingplat.ms_escaner.domain.models.Filtro;
import net.metradingplat.ms_escaner.domain.models.Parametro;
import net.metradingplat.ms_escaner.domain.models.Valor;
import net.metradingplat.ms_escaner.domain.models.ValorInteger;
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

public class EstrategiaFiltroEmaVwapSupportResistance implements IEstrategiaFiltro {
    private final EnumFiltro enumFiltro = EnumFiltro.EMA_VWAP_SUPPORT_RESISTANCE;
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
        parametros.add(this.crearParametroLineaReferencia((ValorString)valoresSeleccionados.get(EnumParametro.LINEA_REFERENCIA_EMA_VWAP_SUPPORT)));
        parametros.add(this.crearParametroPeriodoEma((ValorInteger)valoresSeleccionados.get(EnumParametro.PERIODO_EMA_EMA_VWAP_SUPPORT)));
        parametros.add(this.crearParametroTipoRol((ValorString)valoresSeleccionados.get(EnumParametro.TIPO_ROL_EMA_VWAP_SUPPORT)));

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

    private Parametro crearParametroLineaReferencia(ValorString valorUsuario) {
        EnumTipoValor enumTipoValor = EnumTipoValor.STRING;
        List<Valor> opciones = this.obtenerOpciones(EnumLineaReferencia.values(), enumTipoValor);
        ValorString valor = new ValorString(
            EnumLineaReferencia.VWAP.getEtiqueta(),
            enumTipoValor,
            valorUsuario != null ? valorUsuario.getValor() : EnumLineaReferencia.VWAP.name()
        );
        return new Parametro(EnumParametro.LINEA_REFERENCIA_EMA_VWAP_SUPPORT, EnumParametro.LINEA_REFERENCIA_EMA_VWAP_SUPPORT.getEtiqueta(), valor, opciones);
    }

    private Parametro crearParametroPeriodoEma(ValorInteger valorUsuario) {
        EnumTipoValor enumTipoValor = EnumTipoValor.INTEGER;
        List<Valor> opciones = this.obtenerOpciones(new IEnumValores[0], enumTipoValor);
        ValorInteger valor = new ValorInteger(
            "etiqueta.vacia",
            enumTipoValor,
            valorUsuario != null ? valorUsuario.getValor() : 0
        );
        return new Parametro(EnumParametro.PERIODO_EMA_EMA_VWAP_SUPPORT, EnumParametro.PERIODO_EMA_EMA_VWAP_SUPPORT.getEtiqueta(), valor, opciones);
    }

    private Parametro crearParametroTipoRol(ValorString valorUsuario) {
        EnumTipoValor enumTipoValor = EnumTipoValor.STRING;
        List<Valor> opciones = this.obtenerOpciones(EnumTipoRol.values(), enumTipoValor);
        ValorString valor = new ValorString(
            EnumTipoRol.RESISTENCIA.getEtiqueta(),
            enumTipoValor,
            valorUsuario != null ? valorUsuario.getValor() : EnumTipoRol.RESISTENCIA.name()
        );
        return new Parametro(EnumParametro.TIPO_ROL_EMA_VWAP_SUPPORT, EnumParametro.TIPO_ROL_EMA_VWAP_SUPPORT.getEtiqueta(), valor, opciones);
    }

    @Override
    public List<ResultadoValidacion> validarValoresSeleccionados(Map<EnumParametro, Valor> valoresSeleccionados) {
        List<ResultadoValidacion> errores = new ArrayList<>();

        this.objValidador.validarString(EnumParametro.LINEA_REFERENCIA_EMA_VWAP_SUPPORT,
                valoresSeleccionados.get(EnumParametro.LINEA_REFERENCIA_EMA_VWAP_SUPPORT),
                EnumLineaReferencia.class)
                .ifPresent(errores::add);

        this.objValidador.validarString(EnumParametro.TIPO_ROL_EMA_VWAP_SUPPORT,
                valoresSeleccionados.get(EnumParametro.TIPO_ROL_EMA_VWAP_SUPPORT), EnumTipoRol.class)
                .ifPresent(errores::add);

        Valor lineaReferenciaValor = valoresSeleccionados.get(EnumParametro.LINEA_REFERENCIA_EMA_VWAP_SUPPORT);
        if (lineaReferenciaValor instanceof ValorString && ((ValorString) lineaReferenciaValor).getValor().equalsIgnoreCase(EnumLineaReferencia.EMA.name())) {
            this.objValidador.validarInteger(EnumParametro.PERIODO_EMA_EMA_VWAP_SUPPORT,
                    valoresSeleccionados.get(EnumParametro.PERIODO_EMA_EMA_VWAP_SUPPORT), 2, 100)
                    .ifPresent(errores::add);
        }

        return errores;
    }
}