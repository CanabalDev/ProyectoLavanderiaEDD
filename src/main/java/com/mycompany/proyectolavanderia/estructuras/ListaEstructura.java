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

    public void agregar(Object dato);

    public void agregarAlInicio(Object dato);

    public void agregarEnPosicion(int indice, Object dato);

    public void eliminarPrimero();

    public void eliminarUltimo();

    public void eliminarEnPosicion(int indice);

    public void buscarDato(int indice);

    public void buscarDato(Object dato);

    public void contiene(Object dato);

    public void cuentaElementos();

    public void limpiar();

    public void mostrarAdelante();

    public void mostrarAtras();

}
