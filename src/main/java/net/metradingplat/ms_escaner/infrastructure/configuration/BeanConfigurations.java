package net.metradingplat.ms_escaner.infrastructure.configuration;

import net.metradingplat.ms_escaner.application.output.FormateadorResultadosIntPort;
import net.metradingplat.ms_escaner.application.output.GestionarEscanerGatewayIntPort;
import net.metradingplat.ms_escaner.application.output.GestionarEstadoEscanerGatewayIntPort;
import net.metradingplat.ms_escaner.application.output.GestionarFiltroGatewayIntPort;
import net.metradingplat.ms_escaner.domain.strategies.GestorEstrategiaFiltro;
import net.metradingplat.ms_escaner.domain.strategies.ServicioValidacionFiltro;
import net.metradingplat.ms_escaner.domain.strategies.filtros.IEstrategiaFiltro;
import net.metradingplat.ms_escaner.domain.usecases.GestionarEscanerCUAdapter;
import net.metradingplat.ms_escaner.domain.usecases.GestionarEstadoEscanerCUAdapter;
import net.metradingplat.ms_escaner.domain.usecases.GestionarFiltroCUAdapter;
import net.metradingplat.ms_escaner.domain.usecases.GestionarMercadoCUAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurations {

    // Input Port Beans (Use Cases)
    @Bean
    public GestionarEscanerCUAdapter gestionarEscanerCUIntPort(GestionarEscanerGatewayIntPort objGestionarEscanerGatewayIntPort, GestionarEstadoEscanerGatewayIntPort objGestionarEstadoEscanerGateWayIntPort, FormateadorResultadosIntPort objFormateadorResultadosIntPort) {
        return new GestionarEscanerCUAdapter(objGestionarEscanerGatewayIntPort, objGestionarEstadoEscanerGateWayIntPort, objFormateadorResultadosIntPort);
    }

    @Bean
    public GestionarEstadoEscanerCUAdapter gestionarEstadoEscanerCUIntPort(GestionarEstadoEscanerGatewayIntPort objGestionarEstadoEscanerGatewayIntPort, GestionarEscanerGatewayIntPort objGestionarEscanerGatewayIntPort, FormateadorResultadosIntPort objFormateadorResultados) {
        return new GestionarEstadoEscanerCUAdapter(objGestionarEstadoEscanerGatewayIntPort, objGestionarEscanerGatewayIntPort, objFormateadorResultados);
    }

    @Bean
    public GestionarFiltroCUAdapter gestionarFiltroCUIntPort(GestionarFiltroGatewayIntPort objGestionarFiltroGatewayIntPort,  GestionarEscanerGatewayIntPort objGestionarEscanerGatewayIntPort, GestorEstrategiaFiltro objGestorFactoryFiltro, FormateadorResultadosIntPort objFormateadorResultadosIntPort) {
        return new GestionarFiltroCUAdapter(objGestionarFiltroGatewayIntPort, objGestionarEscanerGatewayIntPort, objGestorFactoryFiltro, objFormateadorResultadosIntPort);
    }

    @Bean
    public GestionarMercadoCUAdapter gestionarMercadoCUIntPort() {
        return new GestionarMercadoCUAdapter();
    }

    
    @Bean
    public GestorEstrategiaFiltro gestorEstrategiaFiltro(java.util.Set<IEstrategiaFiltro> filtros) {
        return new GestorEstrategiaFiltro(filtros);
    }

    @Bean
    public ServicioValidacionFiltro servicioValidacionFiltro() {
        return new ServicioValidacionFiltro();
    }
}