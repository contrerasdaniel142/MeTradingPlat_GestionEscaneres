package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import net.metradingplat.ms_escaner.application.input.GestionarFiltroCUIntPort;
import net.metradingplat.ms_escaner.application.output.FuenteMensajesIntPort;
import net.metradingplat.ms_escaner.domain.enums.EnumCategoriaFiltro;
import net.metradingplat.ms_escaner.domain.enums.EnumFiltro;
import net.metradingplat.ms_escaner.domain.models.CategoriaFiltro;
import net.metradingplat.ms_escaner.domain.models.Filtro;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOAnswer.CategoriaDTORespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOAnswer.FiltroDtoRespuesta;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOPetition.FiltroDtoPeticion;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.mappers.FiltroMapperInfraestructuraDominioImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/escaner/filtro")
@RequiredArgsConstructor
@Validated
public class FiltroRestController {

    private final GestionarFiltroCUIntPort objGestionarFiltroCUInt;
    private final FiltroMapperInfraestructuraDominioImpl objMapper;
    private final FuenteMensajesIntPort objFuenteMensajes;

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaDTORespuesta>> obtenerCategorias() {
        List<CategoriaFiltro> categorias = this.objGestionarFiltroCUInt.obtenerCategorias();
        List<CategoriaDTORespuesta> categoriaDTO = this.objMapper.mappearListaDeCategoriaFiltroARespuesta(categorias);
        this.objFuenteMensajes.internacionalizarCategorias(categoriaDTO);
        return ResponseEntity.ok(categoriaDTO);
    }

    @GetMapping
    public ResponseEntity<List<FiltroDtoRespuesta>> obtenerFiltrosPorCategoria(
            @RequestParam("categoria") @NotNull(message = "filtro.categoria.empty") EnumCategoriaFiltro categoria) {
        List<Filtro> filtros = this.objGestionarFiltroCUInt.obtenerFiltrosPorCategoria(categoria);
        List<FiltroDtoRespuesta> filtrosDTO = this.objMapper.mappearListaDeFiltroARespuesta(filtros);
        this.objFuenteMensajes.internacionalizarFiltros(filtrosDTO);
        return ResponseEntity.ok(filtrosDTO);
    }

    @GetMapping("/defecto")
    public ResponseEntity<FiltroDtoRespuesta> obtenerFiltroPorDefecto(
            @RequestParam("filtro") @NotNull(message = "filtro.enum.empty") EnumFiltro enumFiltro) {
        Filtro filtro = this.objGestionarFiltroCUInt.obtenerFiltroPorDefecto(enumFiltro);
        FiltroDtoRespuesta filtroDTO = this.objMapper.mappearDeFiltroARespuesta(filtro);
        this.objFuenteMensajes.internacionalizarFiltro(filtroDTO);
        return ResponseEntity.ok(filtroDTO);
    }

    @GetMapping("/escaner/{idEscaner}")
    public ResponseEntity<List<FiltroDtoRespuesta>> obtenerFiltrosEscaner(
            @PathVariable("idEscaner") @NotNull(message = "escaner.id.empty") @Positive(message = "escaner.id.positive") Long idEscaner) {
        List<Filtro> filtros = this.objGestionarFiltroCUInt.obtenerFiltros(idEscaner);
        List<FiltroDtoRespuesta> filtrosDTO = this.objMapper.mappearListaDeFiltroARespuesta(filtros);
        this.objFuenteMensajes.internacionalizarFiltros(filtrosDTO);
        return ResponseEntity.ok(filtrosDTO);
    }

    @PostMapping("/escaner/{idEscaner}")
    public ResponseEntity<List<FiltroDtoRespuesta>> guardarFiltrosDeEscaner(
            @PathVariable("idEscaner") @NotNull(message = "escaner.id.empty") @Positive(message = "escaner.id.positive") Long idEscaner,
            @RequestBody @Valid @NotNull(message = "filtro.peticion.empty") List<FiltroDtoPeticion> peticiones) {
        List<Filtro> filtros = this.objMapper.mappearListaDePeticionAFiltro(peticiones);
        List<Filtro> guardados = this.objGestionarFiltroCUInt.guardarFiltros(idEscaner, filtros);
        List<FiltroDtoRespuesta> filtrosDTO = this.objMapper.mappearListaDeFiltroARespuesta(guardados);
        this.objFuenteMensajes.internacionalizarFiltros(filtrosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(filtrosDTO);
    }

    @DeleteMapping("/{idFiltro}")
    public ResponseEntity<Boolean> eliminarFiltroGuardado(
            @PathVariable("idFiltro") @NotNull @Positive Long idFiltro) {

        Boolean respuesta = this.objGestionarFiltroCUInt.eliminarFitroGuardado(idFiltro);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}
