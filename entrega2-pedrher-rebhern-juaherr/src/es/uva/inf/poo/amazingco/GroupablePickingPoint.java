package es.uva.inf.poo.amazingco;

import java.time.LocalTime;
import java.util.ArrayList;

import es.uva.inf.poo.maps.GPSCoordinate;

public abstract class GroupablePickingPoint extends PickingPoint {

	/**
	 * @see es.uva.inf.poo.amazingco.PickingPoint#PickingPoint(String,
	 *      GPSCoordinate, LocalTime[][],int)
	 */
	public GroupablePickingPoint(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas) {
		super(id, ubicacion, horario, numeroTaquillas);
	}

	/**
	 * El paquete no puede ser certificado.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#PickingPoint(String,
	 *      GPSCoordinate, LocalTime[][],int, boolean)
	 */
	public GroupablePickingPoint(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas, boolean operativo) {
		super(id, ubicacion, horario, numeroTaquillas, operativo);
	}

	/**
	 * Un paquete es valido siempre que no sea certificado.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#paqueteValido(Package)
	 */
	@Override
	public boolean paqueteValido(Package paquete) {
		if (paquete == null) {
			throw new IllegalArgumentException("El paquete es null.");
		}
		return !paquete.getCertificado();
	}

	@Override
	protected boolean borrable() {
		ArrayList<Package> paq = getPaquetes();
		return paq.isEmpty();

	}

}
