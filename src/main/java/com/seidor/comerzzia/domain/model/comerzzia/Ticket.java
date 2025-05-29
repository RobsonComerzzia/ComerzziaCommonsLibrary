package com.seidor.comerzzia.domain.model.comerzzia;


import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="D_TICKETS_TBL")
@IdClass(Ticket.pk_ticket.class)
public class Ticket {

	@Id
	@Column(name = "UID_ACTIVIDAD")
	private String uidActividad;
	
	@Id
	@Column(name = "UID_TICKET")
	private String uidTicket;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_ticket implements Serializable {		
		
		private static final long serialVersionUID = -508334720354689932L;
	
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "UID_ACTIVIDAD")
		private String uidActividad;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "UID_TICKET")
		private String uidTicket;
		
		@Column(name = "CODALM")
		private String codalm;
		
		@Column(name = "ID_TICKET")
		private BigInteger idTicket;
		
		@Column(name = "FECHA")
		private LocalDateTime fecha;
		
		@Lob
		@Column(name = "TICKET", nullable = true, columnDefinition = "LONGBLOB")
		private byte[] ticket;
		
		@Column(name = "PROCESADO")
		private String procesado;
		
		@Column(name = "FECHA_PROCESO")
		private LocalDateTime fechaProceso;		
		
		@Column(name = "MENSAJE_PROCESO")
		private String mensajeProceso;
	    
		@Column(name = "CODCAJA")
		private String codcaja;
		
		@Column(name = "ID_TIPO_DOCUMENTO")
		private BigInteger idTipoDocumento;
		
		@Column(name = "COD_TICKET")
		private String codTicket;
		
		@Column(name = "FIRMA")
		private String firma;
		
		@Column(name = "SERIE_TICKET")
		private String serieTicket;
		
		@Column(name = "ID_ACCION_ESTADOS")
		private BigInteger idAccionEstados;
		
		@Column(name = "ID_ESTADO")
		private Integer idEstado;
		
		@Column(name = "LOCATOR_ID")
		private String locatorId;
	}
}
