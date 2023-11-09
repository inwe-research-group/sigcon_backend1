package uni.isw.sigconbackend.model;
import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name="persona")
public class Persona {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_persona;    
    private String apellido_paterno;
    private String apellido_materno;
    private String nombres;    
    private Date fecha_nacimiento;
    private Integer id_tipo_documento;
    private String ndocumento;
    private String direccion;
    private String idubigeo;    

    public Long getId_persona() {
        return id_persona;
    }

    
    @Override
    public String toString() {
        return "Persona{" + "id_persona=" + id_persona + ", apellido_paterno=" + apellido_paterno + ", apellido_materno=" + apellido_materno + ", nombres=" + nombres + ", fecha_nacimiento=" + fecha_nacimiento + ", id_tipo_documento=" + id_tipo_documento + ", ndocumento=" + ndocumento + ", direccion=" + direccion + ", idubigeo=" + idubigeo + '}';
    }
}
