package net.metradingplat.ms_escaner.infrastructure.output.persistence.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.SubclassMapping;

import net.metradingplat.ms_escaner.domain.models.Filtro;
import net.metradingplat.ms_escaner.domain.models.Parametro;
import net.metradingplat.ms_escaner.domain.models.Valor;
import net.metradingplat.ms_escaner.domain.models.ValorCondicional;
import net.metradingplat.ms_escaner.domain.models.ValorFloat;
import net.metradingplat.ms_escaner.domain.models.ValorInteger;
import net.metradingplat.ms_escaner.domain.models.ValorString;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.FiltroEntity;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.ParametroEntity;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.ValorCondicionalEntity;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.ValorEntity;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.ValorFloatEntity;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.ValorIntegerEntity;
import net.metradingplat.ms_escaner.infrastructure.output.persistence.entitys.ValorStringEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FiltroMapperPersistencia {

    // Entity -> Dominio
    Filtro mappearEntityAFiltro(FiltroEntity entity);
    Parametro mappearEntityAParametro(ParametroEntity entity);

    @SubclassMapping(source = ValorIntegerEntity.class, target = ValorInteger.class)
    @SubclassMapping(source = ValorFloatEntity.class, target = ValorFloat.class)
    @SubclassMapping(source = ValorStringEntity.class, target = ValorString.class)
    @SubclassMapping(source = ValorCondicionalEntity.class, target = ValorCondicional.class)
    Valor mappearEntityAValor(ValorEntity entity);

    ValorInteger mappearEntityAValorInteger(ValorIntegerEntity entity);
    ValorFloat mappearEntityAValorFloat(ValorFloatEntity entity);
    ValorString mappearEntityAValorString(ValorStringEntity entity);
    ValorCondicional mappearEntityAValorCondicional(ValorCondicionalEntity entity);

    // Dominio -> Entity
    @InheritInverseConfiguration(name = "mappearEntityAFiltro")
    FiltroEntity mappearFiltroAEntity(Filtro filtro);

    @InheritInverseConfiguration(name = "mappearEntityAParametro")
    ParametroEntity mappearParametroAEntity(Parametro parametro);

    ValorIntegerEntity mappearValorIntegerAEntity(ValorInteger valor);
    ValorFloatEntity mappearValorFloatAEntity(ValorFloat valor);
    ValorStringEntity mappearValorStringAEntity(ValorString valor);
    ValorCondicionalEntity mappearValorCondicionalAEntity(ValorCondicional valor);

    // Factory: evita return abstract
    default ValorEntity mappearValorAEntity(Valor valor) {
        if (valor instanceof ValorInteger v) return mappearValorIntegerAEntity(v);
        if (valor instanceof ValorFloat v) return mappearValorFloatAEntity(v);
        if (valor instanceof ValorString v) return mappearValorStringAEntity(v);
        if (valor instanceof ValorCondicional v) return mappearValorCondicionalAEntity(v);
        throw new IllegalArgumentException("Tipo de Valor desconocido: " + valor.getClass().getName());
    }

    // Listas
    List<Filtro> mappearListaEntityAFiltro(List<FiltroEntity> entities);
    List<FiltroEntity> mappearListaFiltroAEntity(List<Filtro> filtros);
    List<Parametro> mappearListaEntityAParametro(List<ParametroEntity> entities);
    List<ParametroEntity> mappearListaParametroAEntity(List<Parametro> parametros);
}
