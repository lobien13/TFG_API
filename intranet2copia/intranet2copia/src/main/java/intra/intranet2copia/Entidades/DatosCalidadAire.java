package intra.intranet2copia.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class DatosCalidadAire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double co2;
    private double pm25;
    private double pm10;
    private double o3;
    private double no2;
    private double so2;
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "sistema_id", referencedColumnName = "id")
    @JsonBackReference
    private SistemaArduino sistemaArduino;

    // Getters y setters
    public Long getId() {
        return id;
    }   

    public void setId(Long id) {
        this.id = id;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getPm25() {
        return pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public double getO3() {
        return o3;
    }

    public void setO3(double o3) {
        this.o3 = o3;
    }

    public double getNo2() {
        return no2;
    }

    public void setNo2(double no2) {
        this.no2 = no2;
    }

    @JsonProperty("sistema_id")
    public Long getSistemaId() {
        return this.sistemaArduino != null ? this.sistemaArduino.getId() : null;
    }
    public double getSo2() {
        return so2;
    }

    public void setSo2(double so2) {
        this.so2 = so2;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public SistemaArduino getSistemaArduino() {
        return sistemaArduino;
    }

    public void setSistemaArduino(SistemaArduino sistemaArduino) {
        this.sistemaArduino = sistemaArduino;
    }

    public boolean isValid() {
        return co2 >= 0 &&
                pm25 >= 0 &&
                pm10 >= 0 &&
                o3 >= 0 &&
                no2 >= 0 &&
                so2 >= 0;
    }
}
