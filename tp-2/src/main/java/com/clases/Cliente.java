package com.clases;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Cliente extends Persona {

    private int id;
    private TipoPersona tipoPersona;
    private String banco;
    private LocalDate fechaAlta;
    private Set<Cuenta> cuentas = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Set<Cuenta> getCuentas() {
        return cuentas;
    }

    // ABM

    public void addCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }

    public void deleteCuenta(Cuenta cuenta) {
        this.cuentas.remove(cuenta);
    }

    public void updateCuenta(String nombre, String apellido, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public void transferir(Cuenta origen, Cuenta destino, int monto) {
        if (origen != null && destino != null && monto > 0) {
            if (origen.getBalance() >= monto) {
                origen.depositarSaldo(monto);
                destino.retirarSaldo(monto);
                System.out.println("Transferencia exitosa de " + monto + " desde cuenta " + origen.getNombre()
                        + " a cuenta " + destino.getNombre());
            } else {
                System.out.println("Error: Fondos insuficientes en la cuenta de origen.");
            }
        } else {
            System.out.println("Error: Cuentas inválidas o monto de transferencia inválido.");
        }
    }

}