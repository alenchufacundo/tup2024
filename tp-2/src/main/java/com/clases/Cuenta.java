package com.clases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    String nombre;
    LocalDateTime fechaCreacion;
    int balance;

    private List<Movimiento> movimientos;

    public String getNombre() {
        return nombre;
    }

    // Constructor
    public Cuenta() {
        this.movimientos = new ArrayList<>();
    }

    // depositar
    public void depositarSaldo(int monto) {
        if (monto > 0) {
            this.balance += monto;
            registrarMovimiento("Depósito", monto);
            System.out.println("Depósito exitoso. Nuevo saldo: " + this.balance);
        } else {
            System.out.println("Error: El monto del depósito debe ser positivo.");
        }
    }

    public void retirarSaldo(int monto) {
        if (monto > 0 && this.balance >= monto) {
            this.balance -= monto;
            registrarMovimiento("Retiro", -monto);
            System.out.println("Retiro exitoso. Nuevo saldo: " + this.balance);
        }
    }

    public void consultarSaldo() {
        System.out.println("Saldo actual: " + this.balance);
    }

    private void registrarMovimiento(String tipoOperacion, int monto) {
        Movimiento movimiento = new Movimiento(LocalDateTime.now(), tipoOperacion,
                monto);
        this.movimientos.add(movimiento);
    }

    public Cuenta setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Cuenta setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public int getBalance() {
        return balance;
    }

    public Cuenta setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }
}
