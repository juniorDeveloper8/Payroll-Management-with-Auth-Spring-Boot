package com.api.rober.Models;

import com.api.rober.DTO.FichaPago.DatosActualizacionFichaPago;
import com.api.rober.DTO.FichaPago.DatosRegistroFichaPago;
import com.api.rober.Interface.EmpleadoInterface;
import com.api.rober.Models.Enum.EstadoRol;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "fichaPago")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FichaPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ficha", unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(name = "fecha_emision", nullable = false)
    private Date fechaEmision;

    @Column(columnDefinition = "DECIMAL(8,2)")
    private BigDecimal ingresos;

    @Column(columnDefinition = "DECIMAL(8,2)")
    private BigDecimal egresos;

    @Column(columnDefinition = "DECIMAL(8,2)")
    private BigDecimal sueldo;

    @Column(columnDefinition = "DECIMAL(8,2)")
    private BigDecimal sueldoNeto; //sueldo q sale del calculo

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoRol estadoRol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_emp")
    private Empleado empleado;


    // metodo save

    public FichaPago(DatosRegistroFichaPago datosRegistroFichaPago, EmpleadoInterface empleadoInterface) {
        this.codigo = generarCodigoUnico();
        this.empleado = empleadoInterface.findById(datosRegistroFichaPago.idEmp())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado"));
        this.fechaEmision = datosRegistroFichaPago.fechaEmision();
        this.ingresos = datosRegistroFichaPago.ingresos();
        this.egresos = datosRegistroFichaPago.egresos();
        this.sueldo = datosRegistroFichaPago.sueldo();
        this.sueldoNeto = datosRegistroFichaPago.sueldoNeto();
        this.estadoRol = datosRegistroFichaPago.estadoRol();
    }

    // Generar un código único utilizando UUID
    private String generarCodigoUnico() {
        return UUID.randomUUID().toString();
    }

    // Método para calcular el descuento por Aporte al IEES
    public BigDecimal calcularDescuento() {
        if (sueldo == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal porcentajeDescuento = new BigDecimal("0.0935");
        return sueldo.multiply(porcentajeDescuento);
    }

    // Método para calcular el sueldo Neto a Pagar
    public BigDecimal calcularNetoPagar() {
        if (sueldo == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal descuento = calcularDescuento();
        BigDecimal ingresosFinales = ingresos != null ? ingresos : BigDecimal.ZERO;
        BigDecimal egresosFinales = egresos != null ? egresos : BigDecimal.ZERO;

        // Calcular sueldo neto
        sueldoNeto = sueldo.add(ingresosFinales).subtract(descuento).subtract(egresosFinales);
        return sueldoNeto.setScale(2, BigDecimal.ROUND_HALF_UP);
    }


    // Método para guardar y mostrar resultados
    public void save() {
        System.out.println("Código: " + codigo);
        System.out.println("Fecha de Emisión: " + fechaEmision);
        System.out.println("Sueldo: " + sueldo);
        System.out.println("Ingresos: " + ingresos);
        System.out.println("Egresos: " + egresos);
        System.out.println("Descuento IEES: " + calcularDescuento());
        System.out.println("Neto a Pagar: " + calcularNetoPagar());
    }

    // metodo update
    public void update(DatosActualizacionFichaPago datosActualizacionFichaPago) {
        if (datosActualizacionFichaPago == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El objeto datosActualizacionFichaPago no puede ser null.");
        }
        if (datosActualizacionFichaPago.fechaEmision() != null) {
            this.fechaEmision = datosActualizacionFichaPago.fechaEmision();
        }
        if (datosActualizacionFichaPago.ingresos() != null) {
            this.ingresos = datosActualizacionFichaPago.ingresos();
        }
        if (datosActualizacionFichaPago.egresos() != null) {
            this.egresos = datosActualizacionFichaPago.egresos();
        }
        if (datosActualizacionFichaPago.sueldo() != null) {
            this.sueldo = datosActualizacionFichaPago.sueldo();
        }
        if (datosActualizacionFichaPago.estadoRol() != null) {
            this.estadoRol = datosActualizacionFichaPago.estadoRol();
        }
    }


}
