package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOPetition;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValorFloatDTOPeticion extends ValorDTOPeticion {
    @NotNull(message = "valor.float.empty")
    private Float valor;
}