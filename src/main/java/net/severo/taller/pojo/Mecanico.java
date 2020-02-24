package net.severo.taller.pojo;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "mecanico")
public class Mecanico implements Serializable {

    @Id
    @Column(name = "ID_MECANICO")
    private int idMecanico;

    @Column(name = "NOMBRE")
    private String nombreMecanico;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    },
            targetEntity = Vehiculo.class)
    @JoinTable(name = "vehiculomecanico",
            inverseJoinColumns = @JoinColumn(name = "IdVehiculo",
                    nullable = false,
                    updatable = false),
            joinColumns = @JoinColumn(name = "IdMecanico",
                    nullable = false,
                    updatable = false),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Set<Vehiculo> vehiculos = new HashSet();

    public Mecanico(int idMecanico, String nombreMecanico, Set<Vehiculo> vehiculos) {
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

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
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
