package es.uva.inf.poo.amazingco;

import java.time.LocalTime;

import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * GroupablePickingPoint que almacena paquetes ya pagados.
 * 
 * Permite asignar, borrar, devolver y sacar paquetes actualizando el estado del
 * paquete si es necesario.
 * 
 * @author juaherr
 * @author rebhern
 * @author pedrher
 * 
 */
public class PackageLocker extends GroupablePickingPoint {
	/**
	 * hola {@inheritDoc}
	 * 
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
	 * Comprueba si un paquete puede ser guardado en el PackageLocker o no.
	 * 
	 * Un paquete es valido siempre que no sea certificado ni a contrarrembolso.
	 * 
	 * @param paquete paquete a comprobar.
	 * @return true si el paquete no es certificado ni a contrarrembolso.
	 * @throws IllegalArgumentException si el paquete es null.
	 * @see es.uva.inf.poo.amazingco.GroupablePickingPoint#paqueteValido(Package)
	 */
	@Override
	public boolean paqueteValido(Package paquete) {
		return paquete.getPagado() && super.paqueteValido(paquete);
	}

}
