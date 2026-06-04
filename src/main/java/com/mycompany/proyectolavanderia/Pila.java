package com.mycompany.proyectolavanderia;

import com.mycompany.proyectolavanderia.estructuras.PilaEstructura;

/**
 *
 * @author lm-carlos
 */
public class Pila implements PilaEstructura {

    private Nodo cima;
    private int cantidad;

    public Pila() {
        this.cima = null;
        this.cantidad = 0;
    }

    @Override
    public void apilar(Object dato) {

        Nodo nuevo = new Nodo(dato);

        if (!esVacia()) {
            nuevo.setSiguiente(cima);
            cima.setAnterior(nuevo);
        }

        cima = nuevo;
        cantidad++;
    }

    @Override
    public Object desapilar() {

        if (esVacia()) {
            return null;
        }

        Object dato = cima.getDato();

        cima = cima.getSiguiente();

        if (cima != null) {
            cima.setAnterior(null);
        }

        cantidad--;

        return dato;
    }

    @Override
    public Object peek() {

        if (esVacia()) {
            return null;
        }

        return cima.getDato();
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

        Nodo actual = cima;

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

        cima = null;
        cantidad = 0;
    }

    @Override
    public void mostrar() {

        Nodo actual = cima;

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    @Override
    public Object buscar(Object dato) {

        Nodo actual = cima;

        while (actual != null) {

            if (actual.getDato().equals(dato)) {
                return actual.getDato();
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

}
