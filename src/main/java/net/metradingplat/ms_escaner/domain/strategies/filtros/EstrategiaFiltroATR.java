package net.metradingplat.ms_escaner.domain.strategies.filtros;

import net.metradingplat.ms_escaner.domain.enums.EnumCategoriaFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumParametro;
import net.metradingplat.ms_escaner.domain.enums.EnumTipoValor;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumCondicional;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumModoPromedioMovil;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumTimeframe;
import net.metradingplat.ms_escaner.domain.enums.valores.IEnumValores;
import net.metradingplat.ms_escaner.domain.models.CategoriaFiltro;
import net.metradingplat.ms_escaner.domain.models.Filtro;
import net.metradingplat.ms_escaner.domain.models.Parametro;
import net.metradingplat.ms_escaner.domain.models.Valor;
import net.metradingplat.ms_escaner.domain.models.ValorInteger;
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

public class EstrategiaFiltroATR implements IEstrategiaFiltro {
    private final EnumFiltro enumFiltro = EnumFiltro.ATR;
    private final EnumCategoriaFiltro enumCategoria = EnumCategoriaFiltro.VOLATILIDAD;

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
        parametros.add(this.crearParametroLongitud((ValorInteger)valoresSeleccionados.get(EnumParametro.LONGITUD_ATR)));
        parametros.add(this.crearParametroModoPromedioMovil((ValorString)valoresSeleccionados.get(EnumParametro.MODO_PROMEDIO_MOVIL_ATR)));
        parametros.add(this.crearParametroTimeframe((ValorString)valoresSeleccionados.get(EnumParametro.TIMEFRAME_ATR)));

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
        List<Valor> opciones = this.obtenerOpciones(EnumCondicional.values(), enumTipoValor);
        ValorCondicional valor = new ValorCondicional(
            EnumCondicional.MAYOR_QUE.getEtiqueta(),
            enumTipoValor,
            EnumCondicional.MAYOR_QUE,
            valorUsuario != null ? valorUsuario.getValor1() : -0.10F,
            valorUsuario != null ? valorUsuario.getValor2() : 5.0F
        );
        return new Parametro(EnumParametro.CONDICION, EnumParametro.CONDICION.getEtiqueta(), valor, opciones);
    }

    private Parametro crearParametroLongitud(ValorInteger valorUsuario) {
        EnumTipoValor enumTipoValor = EnumTipoValor.INTEGER;
        List<Valor> opciones = this.obtenerOpciones(new IEnumValores[0], enumTipoValor);
        ValorInteger valor = new ValorInteger(
            "etiqueta.vacia",
            enumTipoValor,
            valorUsuario != null ? valorUsuario.getValor() : 14
        );
        return new Parametro(EnumParametro.LONGITUD_ATR, EnumParametro.LONGITUD_ATR.getEtiqueta(), valor, opciones);
    }

    private Parametro crearParametroModoPromedioMovil(ValorString valorUsuario) {
        EnumTipoValor enumTipoValor = EnumTipoValor.STRING;
        List<Valor> opciones = this.obtenerOpciones(EnumModoPromedioMovil.values(), enumTipoValor);
        ValorString valor = new ValorString(
            EnumModoPromedioMovil.EMA.getEtiqueta(),
            enumTipoValor,
            valorUsuario != null ? valorUsuario.getValor() : EnumModoPromedioMovil.EMA.name()
        );
        return new Parametro(EnumParametro.MODO_PROMEDIO_MOVIL_ATR, EnumParametro.MODO_PROMEDIO_MOVIL_ATR.getEtiqueta(), valor, opciones);
    }

    private Parametro crearParametroTimeframe(ValorString valorUsuario) {
        EnumTipoValor enumTipoValor = EnumTipoValor.STRING;
        List<Valor> opciones = this.obtenerOpciones(EnumTimeframe.values(), enumTipoValor);
        ValorString valor = new ValorString(
            EnumTimeframe._1D.getEtiqueta(),
            enumTipoValor,
            valorUsuario != null ? valorUsuario.getValor() : EnumTimeframe._1D.name()
        );
        return new Parametro(EnumParametro.TIMEFRAME_ATR, EnumParametro.TIMEFRAME_ATR.getEtiqueta(), valor, opciones);
    }

    @Override
    public List<ResultadoValidacion> validarValoresSeleccionados(Map<EnumParametro, Valor> valoresSeleccionados) {
        List<ResultadoValidacion> errores = new ArrayList<>();

        this.objValidador.validarCondicional(EnumParametro.CONDICION, valoresSeleccionados.get(EnumParametro.CONDICION), 0.01f, 50.0f)
                .ifPresent(errores::add);

        this.objValidador.validarInteger(EnumParametro.LONGITUD_ATR, valoresSeleccionados.get(EnumParametro.LONGITUD_ATR), 5, 100)
                .ifPresent(errores::add);

        this.objValidador.validarString(EnumParametro.MODO_PROMEDIO_MOVIL_ATR,
                valoresSeleccionados.get(EnumParametro.MODO_PROMEDIO_MOVIL_ATR), EnumModoPromedioMovil.class)
                .ifPresent(errores::add);

        this.objValidador.validarString(EnumParametro.TIMEFRAME_ATR, valoresSeleccionados.get(EnumParametro.TIMEFRAME_ATR),
                EnumTimeframe.class)
                .ifPresent(errores::add);

        return errores;
    }
}