package com.clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu() {
        System.out.println("Gestión Bancaria");
        System.out.println("----------------------------------------");
        System.out.println("1. Registrar Cliente");
        System.out.println("2. Crear Cuenta");
        System.out.println("3. Eliminar Cuenta");
        System.out.println("4. Modificar Cuenta");
        System.out.println("5. Realizar Transferencia");
        System.out.println("6. Consultar saldo");
        System.out.println("7. Consultar movimientos");
        System.out.println("8. Salir");
        System.out.println("----------------------------------------");
    }

    public static void ejecutarOpcion(int opcion, Cliente cliente) {
        switch (opcion) {
            case 1:
                registrarCliente(cliente);
                break;
            case 2:
                crearCuenta(cliente);
                break;
            case 3:
                eliminarCuenta(cliente);
                break;
            case 4:
                modificarCuenta(cliente);
                break;
            case 5:
                realizarTransferencia(cliente);
                break;
            case 6:
                consultarSaldo(cliente);
                break;
            case 7:
                consultarMovimientos(cliente);
                break;
            case 8:
                System.out.println("Sesion cerrada");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
        }
    }

    private static void registrarCliente(Cliente cliente) {
        System.out.println("Ingrese los datos del cliente:");
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.next());
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        System.out.print("Apellido: ");
        String apellido = scanner.next();
        System.out.print("DNI: ");
        long dni = scanner.nextLong();
        System.out.print("Fecha de Nacimiento (AAAA-MM-DD): ");
        LocalDate fechaNacimiento = LocalDate.parse(scanner.next());

        cliente.setId(id);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);
        cliente.setFechaNacimiento(fechaNacimiento);

        System.out.println("Cliente registrado correctamente.");
    }

    private static void crearCuenta(Cliente cliente) {
        if (cliente == null) {
            System.out.println("Primero registre un cliente para crear una cuenta.");
            return;
        }

        System.out.println("Ingrese los datos de la cuenta:");
        System.out.print("Nombre de la cuenta: ");
        String nombreCuenta = scanner.next();
        System.out.print("Saldo inicial: ");
        int saldoInicial = scanner.nextInt();

        Cuenta cuenta = new Cuenta()
                .setNombre(nombreCuenta)
                .setFechaCreacion(LocalDateTime.now())
                .setBalance(saldoInicial);

        cliente.addCuenta(cuenta);

        System.out.println("Cuenta creada correctamente.");
    }

    private static void eliminarCuenta(Cliente cliente) {
        if (cliente == null || cliente.getCuentas().isEmpty()) {
            System.out.println("No hay cuentas para eliminar.");
            return;
        }

        System.out.println("Ingrese el nombre de la cuenta a eliminar:");
        String nombreCuenta = scanner.next();

        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.getNombre().equals(nombreCuenta)) {
                cliente.deleteCuenta(cuenta);
                System.out.println("Cuenta eliminada correctamente.");
                return;
            }
        }

        System.out.println("No se encontró ninguna cuenta con ese nombre.");
    }

    private static void realizarTransferencia(Cliente cliente) {
        if (cliente == null || cliente.getCuentas().size() < 2) {
            System.out.println("Debe tener al menos dos cuentas para realizar una transferencia.");
            return;
        }

        System.out.println("Ingrese el nombre de la cuenta de origen:");
        String nombreCuentaOrigen = scanner.next();
        System.out.println("Ingrese el nombre de la cuenta de destino:");
        String nombreCuentaDestino = scanner.next();
        System.out.println("Ingrese el monto a transferir:");
        int monto = scanner.nextInt();

        Cuenta cuentaOrigen = null;
        Cuenta cuentaDestino = null;

        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.getNombre().equals(nombreCuentaOrigen)) {
                cuentaOrigen = cuenta;
            } else if (cuenta.getNombre().equals(nombreCuentaDestino)) {
                cuentaDestino = cuenta;
            }
        }

        if (cuentaOrigen != null && cuentaDestino != null) {
            cliente.transferir(cuentaOrigen, cuentaDestino, monto);
        } else {
            System.out.println("No se encontraron las cuentas especificadas.");
        }
    }

    private static void consultarSaldo(Cliente cliente) {
        if (cliente == null || cliente.getCuentas().isEmpty()) {
            System.out.println("No hay cuentas para consultar saldo.");
            return;
        }

        System.out.println("Ingrese el nombre de la cuenta para consultar saldo:");
        String nombreCuenta = scanner.next();

        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.getNombre().equals(nombreCuenta)) {
                System.out.println("Saldo de la cuenta " + cuenta.getNombre() + ": " + cuenta.getBalance());
                return;
            }
        }

        System.out.println("No se encontró ninguna cuenta con ese nombre.");
    }

    private static void modificarCuenta(Cliente cliente) {
        if (cliente == null || cliente.getCuentas().isEmpty()) {
            System.out.println("No hay cuentas para modificar.");
            return;
        }

        System.out.println("Ingrese el nombre de la cuenta a modificar:");
        String nombreCuenta = scanner.next();

        Cuenta cuentaModificar = null;
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.getNombre().equals(nombreCuenta)) {
                cuentaModificar = cuenta;
                break;
            }
        }

        if (cuentaModificar == null) {
            System.out.println("No se encontró la cuenta especificada.");
            return;
        }

        System.out.println("Ingrese el nuevo nombre para la cuenta:");
        String nuevoNombre = scanner.next();
        cuentaModificar.setNombre(nuevoNombre);
        System.out.println("Nombre de la cuenta modificado correctamente.");
    }

    private static void consultarMovimientos(Cliente cliente) {
        if (cliente == null || cliente.getCuentas().isEmpty()) {
            System.out.println("No hay cuentas para consultar movimientos.");
            return;
        }

        System.out.println("Ingrese el nombre de la cuenta para consultar movimientos:");
        String nombreCuenta = scanner.next();

        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.getNombre().equals(nombreCuenta)) {
                List<Movimiento> movimientos = cuenta.getMovimientos();
                if (movimientos.isEmpty()) {
                    System.out.println("No hay movimientos registrados en esta cuenta.");
                } else {
                    System.out.println("Movimientos de la cuenta " + cuenta.getNombre() + ":");
                    for (Movimiento movimiento : movimientos) {
                        System.out.println(movimiento);
                    }
                }
                return;
            }
        }

        System.out.println("No se encontró ninguna cuenta con ese nombre.");
    }

}
