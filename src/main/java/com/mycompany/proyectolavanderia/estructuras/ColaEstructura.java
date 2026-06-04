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

    public void encolar(Object dato);

    public void desencolar();

    public void peek();

    public void tamanio();

    public void esVacia();

    public void contiene(Object dato);

    public void limpiar();

    public void mostrar();

}
