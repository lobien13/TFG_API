package intra.intranet2copia.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SistemaArduino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ubicacion;

    @OneToMany(mappedBy = "sistemaArduino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DatosCalidadAire> calidadDelAire = new ArrayList<>();

    @OneToMany(mappedBy = "sistemaArduino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> incidencias = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonBackReference
    private Usuario usuario;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<DatosCalidadAire> getCalidadDelAire() {
        return calidadDelAire;
    }

    public void setCalidadDelAire(List<DatosCalidadAire> calidadDelAire) {
        this.calidadDelAire = calidadDelAire;
    }

    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
