package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.controller;

// import java.util.Locale;

// import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import net.metradingplat.ms_escaner.application.input.GestionarEstadoEscanerCUIntPort;
import net.metradingplat.ms_escaner.domain.models.EstadoEscaner;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOAnswer.EstadoEscanerDTORespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.mapper.EscanerMapperInfraestructuraDominio;

@RestController
@RequestMapping("/api/escaner/estado")
@RequiredArgsConstructor
@Validated
public class EstadoEscanerRestController {

    private final GestionarEstadoEscanerCUIntPort objGestionarEstadoEscanerCUInt;
    private final EscanerMapperInfraestructuraDominio objMapper;

    @PostMapping("/{id}/iniciar")
    public ResponseEntity<EstadoEscanerDTORespuesta> iniciarEscaner(
            @PathVariable("id") @NotNull(message = "escaner.id.empty") @Positive(message = "escaner.id.positive") Long id) {
        EstadoEscaner estado = this.objGestionarEstadoEscanerCUInt.iniciarEscaner(id);
        EstadoEscanerDTORespuesta dto = objMapper.mappearDeEstadoEscanerARespuesta(estado);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/detener")
    public ResponseEntity<EstadoEscanerDTORespuesta> detenerEscaner(
            @PathVariable("id") @NotNull(message = "escaner.id.empty") @Positive(message = "escaner.id.positive") Long id) {
        EstadoEscaner estado = this.objGestionarEstadoEscanerCUInt.detenerEscaner(id);
        EstadoEscanerDTORespuesta dto = objMapper.mappearDeEstadoEscanerARespuesta(estado);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/archivar")
    public ResponseEntity<EstadoEscanerDTORespuesta> archivarEscaner(
            @PathVariable("id") @NotNull(message = "escaner.id.empty") @Positive(message = "escaner.id.positive") Long id) {
        EstadoEscaner estado = this.objGestionarEstadoEscanerCUInt.archivarEscaner(id);
        EstadoEscanerDTORespuesta dto = objMapper.mappearDeEstadoEscanerARespuesta(estado);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/desarchivar")
    public ResponseEntity<EstadoEscanerDTORespuesta> desarchivarEscaner(
            @PathVariable("id") @NotNull(message = "escaner.id.empty") @Positive(message = "escaner.id.positive") Long id) {
        EstadoEscaner estado = this.objGestionarEstadoEscanerCUInt.desarchivarEscaner(id);
        EstadoEscanerDTORespuesta dto = objMapper.mappearDeEstadoEscanerARespuesta(estado);
        return ResponseEntity.ok(dto);
    }
}
