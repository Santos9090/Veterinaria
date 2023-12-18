package com.Veterinaria.bd;


import com.Veterinaria.CRUD.MascotasCRUD;
import com.Veterinaria.principal.Main;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Visitas")
public class Visitas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name = "fecha")
    private java.sql.Date Fecha;
    @Column(name = "motivo")
    private String Motivo;
    @Column(name = "diagnostico")
    private String Diagnostico;
    @Column(name = "mascota")
    private long Mascota;

    public Visitas(Date fecha, String motivo, String diagnostico, long posicionCombox) {
        Fecha = fecha;
        Motivo = motivo;
        Diagnostico = diagnostico;
        MascotasCRUD aux = new MascotasCRUD(Main.BD);
        Mascota = aux.obtenerMascotaPorId(posicionCombox).getId();
    }

    public Visitas() {

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }


    public java.sql.Date getFecha() {
        return Fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.Fecha = fecha;
    }


    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String motivo) {
        this.Motivo = motivo;
    }


    public String getDiagnostico() {
        return Diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.Diagnostico = diagnostico;
    }


    public long getMascota() {
        return Mascota;
    }

    public void setMascota(long mascota) {
        this.Mascota = mascota;
    }

}
