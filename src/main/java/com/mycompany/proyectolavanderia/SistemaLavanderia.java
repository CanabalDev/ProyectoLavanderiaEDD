package com.mycompany.proyectolavanderia;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author lm-carlos
 */
public class SistemaLavanderia {

    private List<OrdenLavado> listaGeneral;
    private Queue<OrdenLavado> colaPendientes;
    private Deque<OrdenLavado> historialProcesados;
    private Map<String, OrdenLavado> indicePorCodigo;

    private Scanner scanner;

    public SistemaLavanderia() {

        listaGeneral = new ArrayList<>();
        colaPendientes = new LinkedList<>();
        historialProcesados = new ArrayDeque<>();
        indicePorCodigo = new HashMap<>();

        scanner = new Scanner(System.in);
    }

    public void iniciar() {

        int opcion;

        do {

            mostrarMenu();

            opcion = Integer.parseInt(
                    scanner.nextLine()
            );

            switch (opcion) {

                case 1:
                    registrarOrden();
                    break;

                case 2:
                    verTodos();
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
                    buscarPorCliente();
                    break;

                case 8:
                    verPrendasDelicadas();
                    break;

                case 9:
                    ordenarPorCliente();
                    break;

                case 10:
                    verEstadisticas();
                    break;

                case 11:
                    agruparPorPrenda();
                    break;

                case 12:
                    cancelarPendiente();
                    break;

                case 13:
                    deshacerProcesamiento();
                    break;

                case 14:
                    cantidadElementos();
                    break;

                case 15:
                    System.out.println(
                            "\nGracias por utilizar el sistema."
                    );
                    break;

                default:
                    System.out.println(
                            "\nOpción inválida."
                    );
            }

            if (opcion != 15) {

                System.out.println(
                        "\nPresione ENTER para continuar..."
                );

                scanner.nextLine();
            }

        } while (opcion != 15);
    }

    private void mostrarMenu() {

        System.out.println("\n====================================");
        System.out.println("      SISTEMA DE LAVANDERÍA");
        System.out.println("====================================");
        System.out.println("1. Registrar elemento");
        System.out.println("2. Ver todos los elementos registrados");
        System.out.println("3. Ver elementos pendientes");
        System.out.println("4. Procesar siguiente elemento");
        System.out.println("5. Ver historial de elementos procesados");
        System.out.println("6. Buscar elemento por identificador usando Map");
        System.out.println("7. Buscar elemento por otro criterio usando Stream");
        System.out.println("8. Filtrar elementos usando Stream");
        System.out.println("9. Ordenar elementos usando Stream");
        System.out.println("10. Ver estadísticas usando Stream y Map");
        System.out.println("11. Ver agrupamientos usando Stream y Map");
        System.out.println("12. Cancelar elemento pendiente");
        System.out.println("13. Deshacer último procesamiento");
        System.out.println("14. Ver cantidad de elementos");
        System.out.println("15. Salir");
        System.out.print("\nSeleccione una opción: ");
    }

    private OrdenLavado crearOrden() {
        System.out.println("Nombre del cliente");
        String nombre = scanner.nextLine();

        System.out.print("Tipo prenda: ");
        String prenda = scanner.nextLine();

        System.out.print("Peso Kg: ");
        double peso = Double.parseDouble(scanner.nextLine());

        System.out.print("¿Es delicada? (S/N): ");
        boolean delicado
                = scanner.nextLine().equalsIgnoreCase("S");

        OrdenLavado nuevaOrden = new OrdenLavado(nombre, prenda, peso, delicado);

        return nuevaOrden;
    }

    ;
    
    private void registrarOrden() {

        OrdenLavado orden = crearOrden();

        listaGeneral.add(orden);

        colaPendientes.offer(orden);

        indicePorCodigo.put(
                orden.getCodigoOrden(),
                orden
        );

        System.out.println("Orden registrada.");
    }

    private void verTodos() {

        listaGeneral.forEach(System.out::println);
    }

    private void verPendientes() {

        colaPendientes.forEach(System.out::println);
    }

    private void procesarSiguiente() {

        OrdenLavado orden
                = colaPendientes.poll();

        if (orden == null) {

            System.out.println(
                    "No hay pendientes."
            );

            return;
        }

        orden.setEstado("PROCESADA");

        historialProcesados.push(orden);

        System.out.println(
                "Orden procesada."
        );
    }

    private void verHistorial() {

        historialProcesados.forEach(
                System.out::println
        );
    }

    private void buscarPorCodigo() {

        System.out.print("Código: ");

        String codigo
                = scanner.nextLine();

        OrdenLavado orden
                = indicePorCodigo.get(codigo);

        if (orden == null) {

            System.out.println(
                    "No encontrada."
            );

        } else {

            System.out.println(orden);
        }
    }

    private void buscarPorCliente() {

        System.out.print(
                "Cliente: "
        );

        String cliente
                = scanner.nextLine();

        listaGeneral.stream()
                .filter(o
                        -> o.getNombreCliente()
                        .equalsIgnoreCase(cliente))
                .findFirst()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println(
                                "No encontrado"
                        )
                );
    }

    private void verPrendasDelicadas() {

        listaGeneral.stream()
                .filter(
                        OrdenLavado::isDelicado
                )
                .forEach(
                        System.out::println
                );
    }

    private void ordenarPorCliente() {

        listaGeneral.stream()
                .sorted(
                        (a, b)
                        -> a.getNombreCliente()
                                .compareToIgnoreCase(
                                        b.getNombreCliente()
                                )
                )
                .forEach(
                        System.out::println
                );
    }

    private void verEstadisticas() {

        Map<String, Long> estadisticas
                = listaGeneral.stream()
                        .collect(
                                java.util.stream.Collectors
                                        .groupingBy(
                                                OrdenLavado::getEstado,
                                                java.util.stream.Collectors.counting()
                                        )
                        );

        estadisticas.forEach(
                (estado, cantidad)
                -> System.out.println(
                        estado + ": " + cantidad
                )
        );
    }

    private void agruparPorPrenda() {

        Map<String, List<OrdenLavado>> grupos
                = listaGeneral.stream()
                        .collect(
                                java.util.stream.Collectors
                                        .groupingBy(
                                                OrdenLavado::getTipoPrenda
                                        )
                        );

        grupos.forEach((tipo, lista) -> {

            System.out.println(
                    "\n" + tipo
            );

            lista.forEach(
                    System.out::println
            );
        });
    }

    private void cancelarPendiente() {

        System.out.print("Código: ");

        String codigo
                = scanner.nextLine();

        OrdenLavado orden
                = indicePorCodigo.get(codigo);

        if (orden == null) {

            System.out.println(
                    "No encontrada."
            );

            return;
        }

        colaPendientes.remove(orden);

        orden.setEstado("CANCELADA");

        System.out.println(
                "Orden cancelada."
        );
    }

    private void deshacerProcesamiento() {

        if (historialProcesados.isEmpty()) {

            System.out.println(
                    "No hay historial."
            );

            return;
        }

        OrdenLavado orden
                = historialProcesados.pop();

        orden.setEstado("PENDIENTE");

        colaPendientes.offer(orden);

        System.out.println(
                "Procesamiento revertido."
        );
    }

    private void cantidadElementos() {

        System.out.println(
                "Total: "
                + listaGeneral.size()
        );
    }

    private void agregarOrdenPrueba(
            String cliente,
            String prenda,
            double peso,
            boolean delicado,
            String estado) {

        OrdenLavado orden
                = new OrdenLavado(
                        cliente,
                        prenda,
                        peso,
                        delicado
                );

        orden.setEstado(estado);

        listaGeneral.add(orden);

        indicePorCodigo.put(
                orden.getCodigoOrden(),
                orden
        );

        if (estado.equals("PENDIENTE")) {
            colaPendientes.offer(orden);
        }

        if (estado.equals("PROCESADA")) {
            historialProcesados.push(orden);
        }
    }

    public void cargarDatosPrueba() {

        // PROCESADAS Y CANCELADAS
        agregarOrdenPrueba("Lionel Messi", "Camisa", 2.5, false, "PROCESADA");
        agregarOrdenPrueba("Cristiano Ronaldo", "Vestido", 1.2, true, "PROCESADA");
        agregarOrdenPrueba("Kevin De Bruyne", "Camisa", 1.8, false, "CANCELADA");
        agregarOrdenPrueba("Luka Modric", "Vestido", 2.4, true, "CANCELADA");
        agregarOrdenPrueba("Neymar Jr", "Pantalon", 3.0, false, "PROCESADA");
        agregarOrdenPrueba("Kylian Mbappe", "Sabanas", 5.5, false, "PROCESADA");
        agregarOrdenPrueba("Erling Haaland", "Chaqueta", 2.0, true, "PROCESADA");
        agregarOrdenPrueba("Vinicius Junior", "Toallas", 4.0, false, "CANCELADA");

        // PENDIENTES (las más recientes)
        agregarOrdenPrueba("Mohamed Salah", "Pantalon", 2.7, false, "PENDIENTE");
        agregarOrdenPrueba("Harry Kane", "Blusa", 1.1, true, "PENDIENTE");
        agregarOrdenPrueba("Robert Lewandowski", "Camisa", 2.3, false, "PENDIENTE");
        agregarOrdenPrueba("Jude Bellingham", "Vestido", 1.4, true, "PENDIENTE");
        agregarOrdenPrueba("Pedri Gonzalez", "Chaqueta", 3.8, false, "PENDIENTE");
        agregarOrdenPrueba("Lamine Yamal", "Blusa", 1.0, true, "PENDIENTE");
        agregarOrdenPrueba("Rodri Hernandez", "Sabanas", 6.0, false, "PENDIENTE");
        agregarOrdenPrueba("Jamal Musiala", "Camisa", 1.7, false, "PENDIENTE");
        agregarOrdenPrueba("Bukayo Saka", "Pantalon", 2.6, false, "PENDIENTE");
        agregarOrdenPrueba("Cole Palmer", "Vestido", 1.5, true, "PENDIENTE");
        agregarOrdenPrueba("Raphinha", "Toallas", 4.5, false, "PENDIENTE");
        agregarOrdenPrueba("Julian Alvarez", "Chaqueta", 2.9, true, "PENDIENTE");

        System.out.println("Datos de prueba cargados correctamente.");
    }

}
