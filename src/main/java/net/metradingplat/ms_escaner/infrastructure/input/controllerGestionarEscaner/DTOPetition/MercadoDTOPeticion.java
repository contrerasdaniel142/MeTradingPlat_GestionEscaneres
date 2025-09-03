package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOPetition;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumMercado;

@Getter
@Setter
public class MercadoDTOPeticion {
    @NotNull(message = "escaner.mercado.enum.empty")
    private EnumMercado enumMercado;
}

