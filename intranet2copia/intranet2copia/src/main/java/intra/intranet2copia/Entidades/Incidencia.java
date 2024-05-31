package intra.intranet2copia.Entidades;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date fecha;
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "sistema_id")
    @JsonBackReference
    private SistemaArduino sistemaArduino;

    @OneToOne
    @JoinColumn(name = "datos_calidad_aire_id")
    private DatosCalidadAire datosCalidadAire;


    public Incidencia() {
        this.fecha = new Date();
    }

    // Getters y setters
    public Incidencia(SistemaArduino sistemaArduino, DatosCalidadAire datosCalidadAire, String mensaje) {
        this.sistemaArduino = sistemaArduino;
        this.fecha = new Date();
        this.mensaje = mensaje;
        this.datosCalidadAire = datosCalidadAire;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    public SistemaArduino getSistemaArduino() {
        return sistemaArduino;
    }

    public void setSistemaArduino(SistemaArduino sistemaArduino) {
        this.sistemaArduino = sistemaArduino;
    }


    public DatosCalidadAire getDatosCalidadAire() {
        return datosCalidadAire;
    }

    public void setDatosCalidadAire(DatosCalidadAire datosCalidadAire) {
        this.datosCalidadAire = datosCalidadAire;
    }

    @JsonProperty("sistema_id")
    public Long getSistemaId() {
        return this.sistemaArduino != null ? this.sistemaArduino.getId() : null;
    }
}
