package net.metradingplat.ms_escaner.domain.usecases;

import lombok.RequiredArgsConstructor;
import net.metradingplat.ms_escaner.application.input.GestionarEscanerCUIntPort;
import net.metradingplat.ms_escaner.application.output.FormateadorResultadosIntPort;
import net.metradingplat.ms_escaner.application.output.GestionarEscanerGatewayIntPort;
import net.metradingplat.ms_escaner.application.output.GestionarEstadoEscanerGatewayIntPort;
import net.metradingplat.ms_escaner.domain.enums.EnumEstadoEscaner;
import net.metradingplat.ms_escaner.domain.models.Escaner;

import java.util.List;

@RequiredArgsConstructor
public class GestionarEscanerCUAdapter implements GestionarEscanerCUIntPort {

    private final GestionarEscanerGatewayIntPort objGestionarEscanerGatewayIntPort;
    private final GestionarEstadoEscanerGatewayIntPort objGestionarEstadoEscanerGatewayIntPort;
    private final FormateadorResultadosIntPort objFormateadorResultadosIntPort;

    @Override
    public Escaner crearEscaner(Escaner objEscaner) {
        if (this.objGestionarEscanerGatewayIntPort.existeEscanerPorNombre(objEscaner.getNombre())) {
            this.objFormateadorResultadosIntPort.errorEntidadYaExiste("escaner.nombre.yaExiste", objEscaner.getNombre());
        }
        if (objEscaner.getHoraInicio().isAfter(objEscaner.getHoraFin())) {
            this.objFormateadorResultadosIntPort.errorReglaNegocioViolada("escaner.horas.invalidas",
                    objEscaner.getHoraInicio(), objEscaner.getHoraFin());
        }
        if (objEscaner.getMercados() == null || objEscaner.getMercados().isEmpty()) {
            this.objFormateadorResultadosIntPort.errorEntidadNoExiste("escaner.mercados.empty");
        }
        Escaner escanerGuardado = this.objGestionarEscanerGatewayIntPort.crearEscaner(objEscaner);
        escanerGuardado.setObjEstado(this.objGestionarEstadoEscanerGatewayIntPort
                .cambiarEstadoDeEscaner(escanerGuardado, EnumEstadoEscaner.DETENIDO));
        return escanerGuardado;
    }

    @Override
    public Escaner obtenerEscanerPorId(Long idEscaner) {
        if (!this.objGestionarEscanerGatewayIntPort.existeEscanerPorId(idEscaner)) {
            this.objFormateadorResultadosIntPort.errorEntidadNoExiste("escaner.id.noEncontrado", idEscaner);
        }
        return this.objGestionarEscanerGatewayIntPort.obtenerEscanerPorId(idEscaner);
    }

    @Override
    public List<Escaner> listarEscaneres() {
        return this.objGestionarEscanerGatewayIntPort.obtenerEscaneresDesarchivados();
    }

    @Override
    public List<Escaner> listarEscaneresArchivados() {
        return this.objGestionarEscanerGatewayIntPort.obtenerEscaneresArchivados();
    }

    @Override
    public Escaner actualizarEscaner(Escaner objEscaner) {
        if (!this.objGestionarEscanerGatewayIntPort.existeEscanerPorId(objEscaner.getIdEscaner())) {
            this.objFormateadorResultadosIntPort.errorEntidadNoExiste("escaner.id.noEncontrado", objEscaner.getIdEscaner());
        }
        if (this.objGestionarEscanerGatewayIntPort.existeEscanerPorNombre(objEscaner.getIdEscaner(), objEscaner.getNombre())) {
            this.objFormateadorResultadosIntPort.errorEntidadYaExiste("escaner.nombre.yaExiste", objEscaner.getNombre());
        }
        if (objEscaner.getHoraInicio().isAfter(objEscaner.getHoraFin())) {
            this.objFormateadorResultadosIntPort.errorReglaNegocioViolada("escaner.horas.invalidas",
                    objEscaner.getHoraInicio(), objEscaner.getHoraFin());
        }
        if (objEscaner.getObjTipoEjecucion() == null) {
            this.objFormateadorResultadosIntPort.errorEntidadNoExiste("escaner.tiposEjecucion.empty");
        }
        if (objEscaner.getMercados() == null || objEscaner.getMercados().isEmpty()) {
            this.objFormateadorResultadosIntPort.errorEntidadNoExiste("escaner.mercados.empty");
        }
        return this.objGestionarEscanerGatewayIntPort.actualizarEscaner(objEscaner);
    }

    @Override
    public Boolean eliminarEscaner(Long idEscaner) {
        if (!this.objGestionarEscanerGatewayIntPort.existeEscanerPorId(idEscaner)) {
            this.objFormateadorResultadosIntPort.errorEntidadNoExiste("escaner.id.noEncontrado", idEscaner);
        }
        Boolean respuesta = this.objGestionarEscanerGatewayIntPort.eliminarEscaner(idEscaner);
        return respuesta;
    }
}
