package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOPetition;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumCondicional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValorCondicionalDTOPeticion extends ValorDTOPeticion {
    @NotNull(message = "valor.condicional.enum.empty")
    private EnumCondicional enumCondicional;

    @NotNull(message = "valor.condicional.valor1.empty")
    private Number valor1;

    private Number valor2;
}