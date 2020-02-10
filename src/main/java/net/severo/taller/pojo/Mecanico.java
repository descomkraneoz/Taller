package net.severo.taller.pojo;

import java.util.List;
import java.util.Objects;

public class Mecanico {
    private int idMecanico;
    private String nombreMecanico;
    private List<Vehiculo> vehiculos;

    public Mecanico(int idMecanico, String nombreMecanico, List<Vehiculo> vehiculos) {
        this.idMecanico = idMecanico;
        this.nombreMecanico = nombreMecanico;
        this.vehiculos = vehiculos;
    }

    public Mecanico(int idMecanico, String nombreMecanico) {
        this.idMecanico = idMecanico;
        this.nombreMecanico = nombreMecanico;
    }

    public Mecanico(){

    }

    public int getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }

    public String getNombreMecanico() {
        return nombreMecanico;
    }

    public void setNombreMecanico(String nombreMecanico) {
        this.nombreMecanico = nombreMecanico;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    @Override
    public String toString() {
        return "Mecanico{" +
                "idMecanico=" + idMecanico +
                ", nombreMecanico='" + nombreMecanico + '\'' +
                ", vehiculos=" + vehiculos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mecanico)) return false;
        Mecanico mecanico = (Mecanico) o;
        return getIdMecanico() == mecanico.getIdMecanico();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdMecanico());
    }
}
