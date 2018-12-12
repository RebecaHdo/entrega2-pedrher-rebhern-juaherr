package es.uva.inf.poo.amazingco;

import java.time.LocalTime;

import es.uva.inf.poo.maps.GPSCoordinate;

public abstract class GroupablePickingPoint extends PickingPoint {
	/**
	 * Inicializa el GroupablePickingPoint operativo con la id, ubicación,
	 * horario semanal y número de taquillas.
	 * 
	 * @param id              id de la taquilla.
	 * @param ubicacion       ubicación de la taquilla.
	 * @param horario         forma en la que se representa el día de la semana
	 *                        y la hora de apertura y cierre de cada día.
	 *                        Esquema:
	 *                        [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 * @param numeroTaquillas número de taquillas que tiene el PackageLocker.
	 * @throws IllegalArgumentException si alguno de los argumentos es null.
	 * @throws IllegalArgumentException si el horario no contiene los 7 dias de
	 *                                  la semana, que uno de los dias sea null,
	 *                                  que un dia no tenga exactamente 2 horas
	 *                                  o que la hora de apertura sea mayor que
	 *                                  la hora de cierre.
	 * @throws IllegalArgumentException si el numeroTaquillas es negativo.
	 */
	public GroupablePickingPoint(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas) {
		super(id, ubicacion, horario);
		if (numeroTaquillas < 1) {
			throw new IllegalArgumentException(
					"Número de taquillas no positivo.");

		}
		setNumeroTaquillas(numeroTaquillas);
	}

	/**
	 * Inicializa el GroupablePickingPoint con la id, ubicación, horario
	 * semanal, número de taquillas y operatividad dados.
	 * 
	 * @param id              id de la taquilla.
	 * @param ubicacion       ubicación de la taquilla.
	 * @param horario         forma en la que se representa el día de la semana
	 *                        y la hora de apertura y cierre de cada día.
	 *                        Esquema:
	 *                        [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 * @param operativo       indica si el PackageLocker está operativo desde el
	 *                        momento creado o no.
	 * @param numeroTaquillas número de taquillas que tiene el PackageLocker.
	 * @throws IllegalArgumentException si alguno de los argumentos es null.
	 * @throws IllegalArgumentException si el horario no contiene los 7 dias de
	 *                                  la semana, que uno de los dias sea null,
	 *                                  que un dia no tenga exactamente 2 horas
	 *                                  o que la hora de apertura sea mayor que
	 *                                  la hora de cierre.
	 * @throws IllegalArgumentException si el numeroTaquillas es negativo.
	 */
	public GroupablePickingPoint(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas, boolean operativo) {
		super(id, ubicacion, horario, operativo);
		if (numeroTaquillas < 1) {
			throw new IllegalArgumentException(
					"Número de taquillas no positivo.");

		}
		setNumeroTaquillas(numeroTaquillas);

	}

}
