package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEntidadMercado.controller;

import lombok.RequiredArgsConstructor;
import net.metradingplat.ms_escaner.application.input.GestionarMercadoCUIntPort;
import net.metradingplat.ms_escaner.application.output.FuenteMensajesIntPort;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEntidadMercado.DTOAnswer.MercadoDTORespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEntidadMercado.mapper.MercadoMapperInfraestructuraDominio;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/escaner/mercado")
@RequiredArgsConstructor
@Validated
public class MercadoRestController {

    private final GestionarMercadoCUIntPort objGestionarMercadoCUInt;
    private final MercadoMapperInfraestructuraDominio objMapper;
    private final FuenteMensajesIntPort objFuenteMensajes;

    @GetMapping
    public ResponseEntity<List<MercadoDTORespuesta>> listarMercados() {
        List<MercadoDTORespuesta> listaDto = this.objMapper.mappearListaDeMercadoARespuesta(this.objGestionarMercadoCUInt.listarEntidadesMercado());
        listaDto = this.objFuenteMensajes.internacionalizarMercados(listaDto);
        return ResponseEntity.ok(listaDto);
    }

}
