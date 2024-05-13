package com.clases;

import java.time.LocalDateTime;

public class Movimiento {
    private LocalDateTime fechaHora;
    private String tipoOperacion;
    private int monto;

    public Movimiento(LocalDateTime fechaHora, String tipoOperacion, int monto) {
        this.fechaHora = fechaHora;
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public int getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "fechaHora=" + fechaHora +
                ", tipoOperacion='" + tipoOperacion + '\'' +
                ", monto=" + monto +
                '}';
    }
}
