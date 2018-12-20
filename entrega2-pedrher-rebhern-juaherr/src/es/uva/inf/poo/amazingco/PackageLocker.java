package es.uva.inf.poo.amazingco;

import java.time.LocalTime;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PackageLocker extends GroupablePickingPoint {
	/**
	 * 
	 * @param id
	 * @param ubicacion
	 * @param horario
	 * @param numeroTaquillas
	 */
	public PackageLocker(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas) {
		super(id, ubicacion, horario, numeroTaquillas);

	}

	public PackageLocker(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas, boolean operativo) {
		super(id, ubicacion, horario, numeroTaquillas, operativo);
	}
	/**
	 *  El paquete no puede ser a contrarrembolso.
	 *  
	 * @see es.uva.inf.poo.amazingco.GroupablePickingPoint#paqueteValido(Package)
	 */
	@Override
	public boolean paqueteValido(Package paquete) {
		return paquete.getPagado() && super.paqueteValido(paquete);
	}

}
