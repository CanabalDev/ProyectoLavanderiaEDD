/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectolavanderia.estructuras;

/**
 *
 * @author lm-carlos
 */
public interface ListaEstructura {

    void agregar(Object dato);

    void agregarAlInicio(Object dato);

    void agregarEnPosicion(int indice, Object dato);

    Object eliminarPrimero();

    Object eliminarUltimo();

    Object eliminarEnPosicion(int indice);

    Object buscarDato(int indice);

    Object buscarDato(Object dato);

    boolean contiene(Object dato);

    int cuentaElementos();

    void limpiar();

    void mostrarAdelante();

    void mostrarAtras();

}
