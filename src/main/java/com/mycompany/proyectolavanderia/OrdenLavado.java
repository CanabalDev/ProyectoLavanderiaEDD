package com.mycompany.proyectolavanderia;

import java.util.Objects;

/**
 *
 * @author lm-carlos
 */
public class OrdenLavado {

    private static int consecutivo = 1;

    private String codigoOrden;
    private String nombreCliente;
    private String tipoPrenda;
    private double pesoKg;
    private String estado;

    public OrdenLavado() {
        this.codigoOrden = String.format("ORD%03d", consecutivo++);
    }

    public OrdenLavado(String nombreCliente, String tipoPrenda, double pesoKg ) {
        this.codigoOrden = String.format("ORD%03d", consecutivo++);
        this.nombreCliente = nombreCliente;
        this.tipoPrenda = tipoPrenda;
        this.pesoKg = pesoKg;
        this.estado = "PENDIENTE";
    }

    public String getCodigoOrden() {
        return codigoOrden;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTipoPrenda() {
        return tipoPrenda;
    }

    public void setTipoPrenda(String tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {

        return "Codigo: " + codigoOrden
                + "\nCliente: " + nombreCliente
                + "\nPrenda: " + tipoPrenda
                + "\nPeso: " + pesoKg
                + "\nEstado: " + estado;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OrdenLavado)) {
            return false;
        }

        OrdenLavado otra = (OrdenLavado) obj;

        return this.codigoOrden.equals(otra.codigoOrden);
    }

}
