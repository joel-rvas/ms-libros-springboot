package com.libros.controller;

import com.libros.entity.ExchangeBean;
import com.libros.jpa.model.TblExchange;
import com.libros.services.IExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin()
public class ExchangeController {
    private static final Logger logger = LoggerFactory.getLogger(ExchangeController.class);

    @Autowired
    private IExchangeService iUtilService;

    @GetMapping("/tipoCambio/{mndaOrign}/{mndaDest}/{valorMndaOrigen}")
    public Flux<TblExchange> tipoCambio(@PathVariable String mndaOrign, @PathVariable String mndaDest, @PathVariable Double valorMndaOrigen) throws Exception {
        return iUtilService.obtenerTipoCambio(mndaOrign, mndaDest, valorMndaOrigen).log();
    }

    @GetMapping("/tipoCambio")
    public Flux<TblExchange> tipoCambio() throws Exception {
        return iUtilService.listarTiposCambioActivos();
    }

    @PostMapping("/tipoCambio")
    public Mono<TblExchange> actualizarTipoCambio(@RequestBody ExchangeBean tipoCambio) throws Exception {
        return iUtilService.actualizarTipoCambio(tipoCambio);
    }

}
