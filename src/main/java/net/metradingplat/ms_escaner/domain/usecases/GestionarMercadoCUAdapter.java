package net.metradingplat.ms_escaner.domain.usecases;

import lombok.RequiredArgsConstructor;
import net.metradingplat.ms_escaner.application.input.GestionarMercadoCUIntPort;
import net.metradingplat.ms_escaner.domain.enums.EnumMercado;
import net.metradingplat.ms_escaner.domain.models.Mercado;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class GestionarMercadoCUAdapter implements GestionarMercadoCUIntPort {

    @Override
    public List<Mercado> listarEntidadesMercado() {
        List<Mercado> mercados = Arrays.stream(EnumMercado.values())
                .map(enumMercado -> new Mercado(
                        enumMercado.getEtiqueta(),
                        enumMercado
                ))
                .toList();
        return mercados;
    }
}