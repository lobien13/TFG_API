// LogArduino.java
package intra.intranet2copia.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "logs_arduino")
public class LogArduino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @Column(name = "fecha_hora")
    private Date fechaHora;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "sistemaId")
    @JsonBackReference
    private SistemaArduino sistemaId;
}