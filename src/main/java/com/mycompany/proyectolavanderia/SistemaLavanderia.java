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

}
