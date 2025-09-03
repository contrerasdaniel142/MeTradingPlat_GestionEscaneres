package net.metradingplat.ms_escaner.application.output;

import java.util.List;

import net.metradingplat.ms_escaner.domain.strategies.validacion.ResultadoValidacion;

public interface FormateadorResultadosIntPort {
    public void errorEntidadYaExiste(String llaveMensaje, Object... args);
    public void errorEntidadNoExiste(String llaveMensaje, Object... args);
    public void errorEstadoDenegado(String llaveMensaje, Object... args);
    public void errorReglaNegocioViolada(String llaveMensaje, Object... args);
    public void errorValidacionFiltro(List<ResultadoValidacion> errorValidaciones);    
}