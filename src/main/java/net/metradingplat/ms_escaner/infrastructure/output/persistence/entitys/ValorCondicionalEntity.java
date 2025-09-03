package net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumCondicional;

@Entity
@Table(name = "valor_condicional")
@PrimaryKeyJoinColumn(name = "id_valor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValorCondicionalEntity extends ValorEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "enum_condicional", length = 50)
    private EnumCondicional enumCondicional;

    @Column(name = "valor1")
    private Number valor1;

    @Column(name = "valor2")
    private Number valor2;
}
