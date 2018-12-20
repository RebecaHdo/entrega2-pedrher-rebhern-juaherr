package es.uva.inf.poo.amazingco;

import java.time.LocalDate;

import java.time.LocalTime;

import es.uva.inf.poo.maps.GPSCoordinate;
/**
 * Almacena paquetes con pago a reembolso o no, calcula el dinero que hay
 * que dar a AmazingCo por los paquetes a reembolso, perimite modificar el
 * número de taquillas del Kiosk creado y sacar paquetes actualizando el
 * estado del paquete a pagado si es necesario.
 * 
 * @author juaherr
 * @author rebhern
 * @author pedrher
 * 
 */
public class Kiosk extends GroupablePickingPoint {
//constantes de error
	private static final String DEMASIADAS_TAQUILLAS_A_BORRAR="No se pueden borrar tantas taquillas.";

	private double dinero = 0;

	/**
	 * @see es.uva.inf.poo.amazingco.PickingPoint#PickingPoint(String,
	 *      GPSCoordinate, LocalTime[][],int)
	 */
	public Kiosk(String id, GPSCoordinate ubicacion, LocalTime[][] horario,
			int numeroTaquillas) {
		super(id, ubicacion, horario, numeroTaquillas);
	}

	/**
	 * @see es.uva.inf.poo.amazingco.PickingPoint#PickingPoint(String,
	 *      GPSCoordinate, LocalTime[][],int, boolean)
	 */
	public Kiosk(String id, GPSCoordinate ubicacion, LocalTime[][] horario,
			int numeroTaquillas, boolean operativo) {
		super(id, ubicacion, horario, numeroTaquillas, operativo);
	}

	private void setDinero(double num) {
		dinero = num;
	}

	/**
	 * Devuelve el dinero que se le debe a AmazingCo.
	 * 
	 * @return dinero que se le debe a AmazingCO.
	 */
	public double getDinero() {
		return dinero;
	}

	/**
	 * 
	 * Paga el dinero debido a AmazingCo.
	 */
	public void dineroAmazingCo() {
		setDinero(0);
	}

	/**
	 * Incrementa/decrementa el número de taquillas dado.
	 * 
	 * @param modificacion modificacion a realizar.
	 * 
	 * @throws IllegalArgumentException si se intentan borrar mas taquillas de
	 *                                  las existentes, o mas taquillas de las
	 *                                  disponibles en el momento.
	 */
	public void modificarNumTaquillas(int modificacion) {
		if (-modificacion >= getNumeroTaquillasVacias()) {
			throw new IllegalArgumentException(
					DEMASIADAS_TAQUILLAS_A_BORRAR);
		}

		setNumeroTaquillas(modificacion + getNumeroTaquillas());
	}

	/**
	 * @see es.uva.inf.poo.amazingco.PickingPoint#sacaPaquete(int, LocalDate)
	 */
	@Override
	public void sacaPaquete(int idTaquilla, LocalDate fechaSacada, int dia,
			LocalTime hora) {
		super.sacaPaquete(idTaquilla, fechaSacada, dia, hora);

		Package paquete = getPaquetesInterno().get(idTaquilla);
		if (!paquete.getPagado()) {
			setDinero(getDinero() + paquete.getPrecio());
			paquete.pagado();
		}
	}

}
