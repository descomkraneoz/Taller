package net.severo.taller.pojo;

import java.util.Date;
import java.util.Objects;

public class Vehiculo {
    private int idVehiculo;
    private String matricula;
    private boolean esElectrico;
    private Date fechaMatriculacion;

    public Vehiculo(int id, String matricula, boolean esElectrico, Date fechaMatriculacion) {
        this.idVehiculo = id;
        this.matricula = matricula;
        this.esElectrico = esElectrico;
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public Vehiculo() {
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int id) {
        this.idVehiculo = id;
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

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + idVehiculo +
                ", matricula='" + matricula + '\'' +
                ", esElectrico=" + esElectrico +
                ", fechaMatriculacion=" + fechaMatriculacion +
                '}';
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
