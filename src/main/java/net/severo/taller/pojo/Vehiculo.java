package net.severo.taller.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "vehiculo")
public class Vehiculo implements Serializable {
    @Id
    @Column(name = "ID_VEHICULO")
    private int idVehiculo;

    @Column(name = "MATRICULA")
    private String matricula;

    @Column(name = "ELECTRICO")
    private boolean esElectrico;

    @Column(name = "FECHA_MATRICULACION")
    private Date fechaMatriculacion;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "VehiculoMecanico", joinColumns = {@JoinColumn(name = "ID_VEHICULO")}, inverseJoinColumns = {@JoinColumn(name = "ID_MECANICO")})
    private Set<Mecanico> mecanicos = new HashSet();

    public Vehiculo(int idVehiculo, String matricula, boolean esElectrico, Date fechaMatriculacion, Set<Mecanico> mecanicos) {
        this.idVehiculo = idVehiculo;
        this.matricula = matricula;
        this.esElectrico = esElectrico;
        this.fechaMatriculacion = fechaMatriculacion;
        this.mecanicos = mecanicos;
    }

    public Vehiculo(int idVehiculo, String matricula, boolean esElectrico, Date fechaMatriculacion) {
        this.idVehiculo = idVehiculo;
        this.matricula = matricula;
        this.esElectrico = esElectrico;
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public Vehiculo() {

    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean getEsElectrico() {
        return esElectrico;
    }

    public void setEsElectrico(boolean esElectrico) {
        this.esElectrico = esElectrico;
    }

    public Date getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(Date fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public Set<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(Set<Mecanico> mecanicos) {
        this.mecanicos = mecanicos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehiculo)) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return getIdVehiculo() == vehiculo.getIdVehiculo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVehiculo());
    }
}


