package net.metradingplat.ms_escaner.application.input;

import net.metradingplat.ms_escaner.domain.models.Escaner;

import java.util.List;

public interface GestionarEscanerCUIntPort {
    public Escaner crearEscaner(Escaner objEscaner);
    public Escaner obtenerEscanerPorId(Long idEscaner);
    public List<Escaner> listarEscaneres();
    public List<Escaner> listarEscaneresArchivados();
    public Escaner actualizarEscaner(Escaner escaner);
    public Boolean eliminarEscaner(Long idEscaner);
}