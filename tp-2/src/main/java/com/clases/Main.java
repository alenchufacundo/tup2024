package com.clases;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                Menu.mostrarMenu();
                System.out.print("Ingrese su opción: ");
                int opcion = Integer.parseInt(scanner.next());
                Menu.ejecutarOpcion(opcion, cliente);
            }
        } finally {
            scanner.close();
        }
    }
}