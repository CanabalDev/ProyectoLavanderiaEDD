package com.mycompany.proyectolavanderia;

import java.util.Scanner;

/**
 *
 * @author lm-carlos
 */
public class SistemaLavanderia {

    private Lista listaGeneral;
    private Cola colaPendientes;
    private Pila historialProcesados;
    private Scanner scanner;

    public SistemaLavanderia() {

        listaGeneral = new Lista();
        colaPendientes = new Cola();
        historialProcesados = new Pila();

        scanner = new Scanner(System.in);
    }

    public void iniciar() {

        int opcion;

        do {

            mostrarMenu();

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {

                case 1:
                    registrarOrden();
                    break;

                case 2:
                    verOrdenesRegistradas();
                    break;

                case 3:
                    verPendientes();
                    break;

                case 4:
                    procesarSiguiente();
                    break;

                case 5:
                    verHistorial();
                    break;

                case 6:
                    buscarPorCodigo();
                    break;

                case 7:
                    cancelarPendiente();
                    break;

                case 8:
                    deshacerProcesamiento();
                    break;

                case 9:
                    verCantidadElementos();
                    break;

                case 10:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 10);
    }

    private void mostrarMenu() {

        System.out.println("\n===== LAVANDERIA =====");

        System.out.println("1. Registrar elemento");
        System.out.println("2. Ver todos los elementos registrados");
        System.out.println("3. Ver elementos pendientes");
        System.out.println("4. Procesar siguiente elemento");
        System.out.println("5. Ver historial de elementos procesados");
        System.out.println("6. Buscar elemento por codigo");
        System.out.println("7. Cancelar elemento pendiente");
        System.out.println("8. Deshacer ultimo procesamiento");
        System.out.println("9. Ver cantidad de elementos");
        System.out.println("10. Salir");

        System.out.print("Seleccione una opcion: ");
    }

    private void registrarOrden() {

        System.out.print("Nombre del cliente: ");
        String cliente = scanner.nextLine();

        System.out.print("Tipo de prenda: ");
        String prenda = scanner.nextLine();

        System.out.print("Peso (Kg): ");
        double peso = Double.parseDouble(scanner.nextLine());

        OrdenLavado orden
                = new OrdenLavado(cliente, prenda, peso);

        listaGeneral.agregar(orden);

        colaPendientes.encolar(orden);

        System.out.println("\nOrden registrada:");

        System.out.println(orden);
    }

    private void verOrdenesRegistradas() {

        if (listaGeneral.cuentaElementos() == 0) {

            System.out.println("No existen registros.");
            return;
        }

        listaGeneral.mostrarAdelante();
    }

    private void verPendientes() {

        if (colaPendientes.esVacia()) {

            System.out.println("No hay pendientes.");
            return;
        }

        colaPendientes.mostrar();
    }

    private void procesarSiguiente() {

        if (colaPendientes.esVacia()) {

            System.out.println("No hay ordenes pendientes.");
            return;
        }

        OrdenLavado orden
                = (OrdenLavado) colaPendientes.desencolar();

        orden.setEstado("PROCESADA");

        historialProcesados.apilar(orden);

        System.out.println("Orden procesada:");

        System.out.println(orden);
    }

    private void verHistorial() {

        if (historialProcesados.esVacia()) {

            System.out.println("No hay historial.");
            return;
        }

        historialProcesados.mostrar();
    }

    private void buscarPorCodigo() {

        System.out.print("Codigo: ");

        String codigo = scanner.nextLine();

        boolean encontrado = false;

        for (int i = 0;
                i < listaGeneral.cuentaElementos();
                i++) {

            OrdenLavado orden
                    = (OrdenLavado) listaGeneral.buscarDato(i);

            if (orden.getCodigoOrden()
                    .equalsIgnoreCase(codigo)) {

                System.out.println(orden);

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {

            System.out.println("No existe una orden con ese codigo.");
        }
    }

    private void cancelarPendiente() {

        System.out.print("Codigo a cancelar: ");

        String codigo = scanner.nextLine();

        Cola auxiliar = new Cola();

        boolean encontrado = false;

        while (!colaPendientes.esVacia()) {

            OrdenLavado orden
                    = (OrdenLavado) colaPendientes.desencolar();

            if (orden.getCodigoOrden()
                    .equalsIgnoreCase(codigo)) {

                orden.setEstado("CANCELADA");

                encontrado = true;

            } else {

                auxiliar.encolar(orden);
            }
        }

        while (!auxiliar.esVacia()) {

            colaPendientes.encolar(
                    auxiliar.desencolar()
            );
        }

        if (encontrado) {

            System.out.println("Orden cancelada.");
        } else {

            System.out.println("No se encontro la orden.");
        }
    }

    private void deshacerProcesamiento() {

        if (historialProcesados.esVacia()) {

            System.out.println("No hay operaciones para deshacer.");
            return;
        }

        OrdenLavado orden
                = (OrdenLavado) historialProcesados.desapilar();

        orden.setEstado("PENDIENTE");

        colaPendientes.encolar(orden);

        System.out.println("Operacion revertida.");

        System.out.println(orden);
    }

    private void verCantidadElementos() {

        System.out.println(
                "Total registrados: "
                + listaGeneral.cuentaElementos()
        );
    }

}
