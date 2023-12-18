package com.Veterinaria.bd;

import javax.persistence.*;

@Entity
@Table(name = "Duenos")
public class Duenos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name = "nombre")
    private String Nombre;
    @Column(name = "Direccion")
    private String Direccion;
    @Column(name = "telefono")
    private long Telefono;

    public Duenos(String nombre, String direccion, long telefono) {
        Nombre = nombre;
        Direccion = direccion;
        Telefono = telefono;
    }

    public Duenos() {

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }


    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }


    public long getTelefono() {
        return Telefono;
    }

    public void setTelefono(long telefono) {
        this.Telefono = telefono;
    }

    @Override
    public String toString() {
        return "Duenos{" +
                "id=" + Id +
                ", nombre='" + Nombre + '\'' +
                ", direccion='" + Direccion + '\'' +
                ", telefono=" + Telefono +
                '}';
    }
}
