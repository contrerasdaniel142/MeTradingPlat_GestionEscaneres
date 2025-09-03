package net.metradingplat.ms_escaner.domain.strategies.validacion;

import java.util.Optional;

import net.metradingplat.ms_escaner.domain.enums.EnumParametro;
import net.metradingplat.ms_escaner.domain.models.Valor;

public interface IValidacionFiltro {
    Optional<ResultadoValidacion> validar(EnumParametro enumParametro, Valor valor);
}