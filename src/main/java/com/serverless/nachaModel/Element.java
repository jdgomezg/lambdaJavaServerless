package com.serverless.nachaModel;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Element {
    private String codigoTransaccion;
    private String cliente;
    private String identificacion;
    private String contryIso;
    private String idBanco;
    private String numCuenta;
    private BigDecimal monto;
    private String beneficiario;
    private String numeroTransaccion;
    private String referencia;
}
