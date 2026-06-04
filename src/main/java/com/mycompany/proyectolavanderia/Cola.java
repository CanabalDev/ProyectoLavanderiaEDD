package com.mycompany.proyectolavanderia;

import com.mycompany.proyectolavanderia.estructuras.ColaEstructura;

/**
 *
 * @author lm-carlos
 */
public class Cola implements ColaEstructura {

    private Nodo frente;
    private Nodo fin;
    private int cantidad;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.cantidad = 0;
    }

    @Override
    public void encolar(Object dato) {

        Nodo nuevo = new Nodo(dato);

        if (esVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            nuevo.setAnterior(fin);
            fin = nuevo;
        }

        cantidad++;
    }

    @Override
    public Object desencolar() {

        if (esVacia()) {
            return null;
        }

        Object dato = frente.getDato();

        if (frente == fin) {
            frente = null;
            fin = null;
        } else {
            frente = frente.getSiguiente();
            frente.setAnterior(null);
        }

        cantidad--;

        return dato;
    }

    @Override
    public Object peek() {

        if (esVacia()) {
            return null;
        }

        return frente.getDato();
    }

    @Override
    public int tamanio() {
        return cantidad;
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    @Override
    public boolean contiene(Object dato) {

        Nodo actual = frente;

        while (actual != null) {

            if (actual.getDato().equals(dato)) {
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    @Override
    public void limpiar() {

        frente = null;
        fin = null;
        cantidad = 0;
    }

    @Override
    public void mostrar() {

        Nodo actual = frente;

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }
}
