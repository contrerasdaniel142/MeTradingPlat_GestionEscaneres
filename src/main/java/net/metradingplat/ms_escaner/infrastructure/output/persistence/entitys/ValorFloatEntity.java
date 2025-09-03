package net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "valor_float")
@PrimaryKeyJoinColumn(name = "id_valor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValorFloatEntity extends ValorEntity {

    @Column(name = "valor")
    private Float valor;
}