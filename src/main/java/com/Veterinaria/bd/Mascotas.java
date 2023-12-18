package com.Veterinaria.bd;

import com.Veterinaria.CRUD.DuenosCRUD;
import com.Veterinaria.principal.Main;

import javax.persistence.*;

@Entity
@Table(name = "Mascotas")
public class Mascotas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name = "nombre")
    private String Nombre;
    @Column(name = "tipo")
    private String Tipo;
    @Column(name = "raza")
    private String Raza;
    @Column(name = "dueno")
    private long Dueno;

    public Mascotas(String text, String text1, String text2, long id) {
        Nombre = text;
        Tipo = text1;
        Raza = text2;
        Dueno = id;
    }

    public Mascotas() {

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


    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        this.Tipo = tipo;
    }


    public String getRaza() {
        return Raza;
    }

    public void setRaza(String raza) {
        this.Raza = raza;
    }


    public long getDueno() {
        return Dueno;
    }

    public void setDueno(long dueno) {
        this.Dueno = dueno;
    }

}
