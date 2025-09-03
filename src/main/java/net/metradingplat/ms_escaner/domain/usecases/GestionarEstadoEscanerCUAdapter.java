package net.metradingplat.ms_escaner.domain.usecases;

import lombok.RequiredArgsConstructor;
import net.metradingplat.ms_escaner.application.input.GestionarEstadoEscanerCUIntPort;
import net.metradingplat.ms_escaner.application.output.FormateadorResultadosIntPort;
import net.metradingplat.ms_escaner.application.output.GestionarEscanerGatewayIntPort;
import net.metradingplat.ms_escaner.application.output.GestionarEstadoEscanerGatewayIntPort;
import net.metradingplat.ms_escaner.domain.enums.EnumEstadoEscaner;
import net.metradingplat.ms_escaner.domain.models.Escaner;
import net.metradingplat.ms_escaner.domain.models.EstadoEscaner;
import net.metradingplat.ms_escaner.domain.states.GestorEstadoEscaner;
import net.metradingplat.ms_escaner.domain.states.escaner.ResultadoGestorEscaner;

@RequiredArgsConstructor
public class GestionarEstadoEscanerCUAdapter implements GestionarEstadoEscanerCUIntPort {

    private final GestionarEstadoEscanerGatewayIntPort objGestionarEstadoEscanerGatewayIntPort;
    private final GestionarEscanerGatewayIntPort objGestionarEscanerGatewayIntPort;
    private final FormateadorResultadosIntPort objFormateadorResultados;

    @Override
    public EstadoEscaner iniciarEscaner(Long id) {
        Escaner escaner = validarEscanerExistente(id);

        if (escaner.getFiltros() == null || escaner.getFiltros().isEmpty()) {
            this.objFormateadorResultados.errorReglaNegocioViolada("escaner.sinFiltros");
        }

        return cambiarEstado(escaner, EnumEstadoEscaner.INICIADO);
    }

    @Override
    public EstadoEscaner detenerEscaner(Long id) {
        return cambiarEstado(validarEscanerExistente(id), EnumEstadoEscaner.DETENIDO);
    }

    @Override
    public EstadoEscaner archivarEscaner(Long id) {
        return cambiarEstado(validarEscanerExistente(id), EnumEstadoEscaner.ARCHIVADO);
    }

    @Override
    public EstadoEscaner desarchivarEscaner(Long id) {
        return cambiarEstado(validarEscanerExistente(id), EnumEstadoEscaner.DESARCHIVADO);
    }

    private Escaner validarEscanerExistente(Long id) {
        if (!this.objGestionarEscanerGatewayIntPort.existeEscanerPorId(id)) {
            this.objFormateadorResultados.errorEntidadNoExiste("escaner.id.noExiste", id);
        }
        return this.objGestionarEscanerGatewayIntPort.obtenerEscanerPorId(id);
    }

    private EstadoEscaner cambiarEstado(Escaner escaner, EnumEstadoEscaner nuevoEstado) {
        EnumEstadoEscaner estadoActual = escaner.getObjEstado().getEnumEstadoEscaner();
        GestorEstadoEscaner gestorEstado = new GestorEstadoEscaner(estadoActual);
        ResultadoGestorEscaner resultado = gestorEstado.cambiarEstado(nuevoEstado);

        if (!resultado.cambioPermitido()) {
            this.objFormateadorResultados.errorEstadoDenegado(resultado.mensaje());
        }

        return this.objGestionarEstadoEscanerGatewayIntPort.cambiarEstadoDeEscaner(escaner, nuevoEstado);
    }
}
