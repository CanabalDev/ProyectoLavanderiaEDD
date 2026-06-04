/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectolavanderia.estructuras;

/**
 *
 * @author lm-carlos
 */
public interface ColaEstructura {

    void encolar(Object dato);

    Object desencolar();

    Object peek();

    int tamanio();

    boolean esVacia();

    boolean contiene(Object dato);

    void limpiar();

    void mostrar();

}
