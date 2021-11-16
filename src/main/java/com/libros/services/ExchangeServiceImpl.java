package com.libros.services;

import com.libros.entity.ExchangeBean;
import com.libros.jpa.model.TblExchange;
import com.libros.jpa.repository.TblExchangeRepository;
import com.libros.util.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeServiceImpl implements IExchangeService {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeServiceImpl.class);

    @Autowired
    private TblExchangeRepository tblExchangeRepository;

    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    @Override
    public Flux<TblExchange> obtenerTipoCambio(String mndaOrign, String mndaDest, double valorMndaOrigen) throws Exception {
        logger.info("call : obtenerTipoCambio");
        List<TblExchange> exchangeList = tblExchangeRepository.findByMndaOrignAndMndaDestAndIndEstado(mndaOrign, mndaDest, Constantes.IND_ESTADO_ACTIVO);
        return Flux.fromIterable(exchangeList).map(item ->
                {
                    item.setValorMndaOrigen(valorMndaOrigen);
                    calcularTipoCambio(item);
                    return item;
                }
        );
    }

    @Override
    public Flux<TblExchange> listarTiposCambioActivos() throws Exception {
        logger.info("call : obtenerTiposCambioActivos");
        List<TblExchange> exchangeList = tblExchangeRepository.findByIndEstado(Constantes.IND_ESTADO_ACTIVO);
        return Flux.fromIterable(exchangeList);
    }

    @Override
    public Mono<TblExchange> actualizarTipoCambio(ExchangeBean bean) throws Exception {
        logger.info("call : actualizarTipoCambio");

        Optional<TblExchange> tipoCambio = tblExchangeRepository.findById(bean.getId());
        TblExchange tblExchange = null;

        if (tipoCambio.isPresent()) {
            tipoCambio.get().setTasaCambio(bean.getTasaCambio());
            tblExchange = tblExchangeRepository.save(tipoCambio.get());
        } else {
            throw new Exception("No existe el tipo de cambio");
        }

        return Mono.just(tblExchange);
    }

    private void calcularTipoCambio(TblExchange item) {
        switch (item.getFactor()) {
            case Constantes.FACTOR_MULTIPLICAR:
                item.setValorConversion(item.getValorMndaOrigen() * item.getTasaCambio());
                break;
            case Constantes.FACTOR_DIVIDIR:
                item.setValorConversion(item.getValorMndaOrigen() / item.getTasaCambio());
                break;
            default:
                break;
        }
    }

}
