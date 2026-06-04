package com.mycompany.proyectolavanderia;

import com.mycompany.proyectolavanderia.estructuras.ListaEstructura;

/**
 *
 * @author lm-carlos
 */
public class Lista implements ListaEstructura {

    private Nodo primero;
    private Nodo ultimo;
    private int cantidad;

    public Lista() {
        this.primero = null;
        this.ultimo = null;
        this.cantidad = 0;
    }

    @Override
    public void agregar(Object dato) {

        Nodo nuevo = new Nodo(dato);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
        }

        cantidad++;
    }

    @Override
    public void agregarAlInicio(Object dato) {

        Nodo nuevo = new Nodo(dato);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.setSiguiente(primero);
            primero.setAnterior(nuevo);
            primero = nuevo;
        }

        cantidad++;
    }

    @Override
    public void agregarEnPosicion(int indice, Object dato) {

        if (indice < 0 || indice > cantidad) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }

        if (indice == 0) {
            agregarAlInicio(dato);
            return;
        }

        if (indice == cantidad) {
            agregar(dato);
            return;
        }

        Nodo actual = primero;

        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }

        Nodo nuevo = new Nodo(dato);

        Nodo anterior = actual.getAnterior();

        anterior.setSiguiente(nuevo);
        nuevo.setAnterior(anterior);

        nuevo.setSiguiente(actual);
        actual.setAnterior(nuevo);

        cantidad++;
    }

    @Override
    public Object eliminarPrimero() {

        if (primero == null) {
            return null;
        }

        Object dato = primero.getDato();

        if (primero == ultimo) {
            primero = null;
            ultimo = null;
        } else {
            primero = primero.getSiguiente();
            primero.setAnterior(null);
        }

        cantidad--;

        return dato;
    }

    @Override
    public Object eliminarUltimo() {

        if (ultimo == null) {
            return null;
        }

        Object dato = ultimo.getDato();

        if (primero == ultimo) {
            primero = null;
            ultimo = null;
        } else {
            ultimo = ultimo.getAnterior();
            ultimo.setSiguiente(null);
        }

        cantidad--;

        return dato;
    }

    @Override
    public Object eliminarEnPosicion(int indice) {

        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }

        if (indice == 0) {
            return eliminarPrimero();
        }

        if (indice == cantidad - 1) {
            return eliminarUltimo();
        }

        Nodo actual = primero;

        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }

        Object dato = actual.getDato();

        Nodo anterior = actual.getAnterior();
        Nodo siguiente = actual.getSiguiente();

        anterior.setSiguiente(siguiente);
        siguiente.setAnterior(anterior);

        cantidad--;

        return dato;
    }

    @Override
    public Object buscarDato(int indice) {

        if (indice < 0 || indice >= cantidad) {
            return null;
        }

        Nodo actual = primero;

        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }

        return actual.getDato();
    }

    @Override
    public Object buscarDato(Object dato) {

        Nodo actual = primero;

        while (actual != null) {

            if (actual.getDato().equals(dato)) {
                return actual.getDato();
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    @Override
    public boolean contiene(Object dato) {

        return buscarDato(dato) != null;
    }

    @Override
    public int cuentaElementos() {

        return cantidad;
    }

    @Override
    public void limpiar() {

        primero = null;
        ultimo = null;
        cantidad = 0;
    }

    @Override
    public void mostrarAdelante() {

        Nodo actual = primero;

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    @Override
    public void mostrarAtras() {

        Nodo actual = ultimo;

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getAnterior();
        }
    }

    public Nodo getPrimero() {
        return primero;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public boolean esVacia() {
        return cantidad == 0;
    }

}
