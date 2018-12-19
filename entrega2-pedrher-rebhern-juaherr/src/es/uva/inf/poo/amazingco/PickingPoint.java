package es.uva.inf.poo.amazingco;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

import es.uva.inf.poo.maps.GPSCoordinate;

public abstract class PickingPoint {

	private String id;
	private GPSCoordinate ubicacion;
	private LocalTime[][] horario;
	private int numeroTaquillas;
	private ArrayList<Package> paquetes;
	private int ocupadas;
	private boolean operativo;

	/**
	 * Inicializa el PickingPoint con la id, ubicación, horario semanal, número
	 * de taquillas y operatividad dados.
	 * 
	 * @param id        id de la taquilla.
	 * @param ubicacion ubicación de la taquilla.
	 * @param horario   forma en la que se representa el día de la semana y la
	 *                  hora de apertura y cierre de cada día. Esquema:
	 *                  [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 * @param operativo indica si el PackageLocker está operativo desde el
	 *                  momento creado o no.
	 * @throws IllegalArgumentException si alguno de los argumentos es null.
	 * @throws IllegalArgumentException si el horario no contiene los 7 dias de
	 *                                  la semana, que uno de los dias sea null,
	 *                                  que un dia no tenga exactamente 2 horas
	 *                                  o que la hora de apertura sea mayor que
	 *                                  la hora de cierre.
	 */
	public PickingPoint(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas, boolean operativo) {

		// Comprueba que los argumentos dados para la inicialización del
		// PackageLocker
		// sean válidos.
		if (id == null || ubicacion == null || horario == null) {
			throw new IllegalArgumentException(
					"Uno de los argumentos es null.");
		}
		// Comprobación de las especificaciones del horario. Esquema:
		// [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
		if (horario.length != 7) {
			throw new IllegalArgumentException(
					"Son necesarios los horarios de los 7 dias de la semana.");
		}
		for (int i = 0; i < 7; i++) {
			if (horario[i] == null) {
				throw new IllegalArgumentException("Uno de los dias es null.");
			}
			if (horario[i].length != 2) {
				throw new IllegalArgumentException(
						"Cada dia tiene que tener horario de apertura y cierre");

			}
			for (int j = 0; j < 2; j++) {
				if (horario[i][j] == null
						|| horario[i][0].isAfter(horario[i][1])) {
					throw new IllegalArgumentException(
							"Horas mal introducidas.");
				}
			}

		}
		if (numeroTaquillas < 1) {
			throw new IllegalArgumentException(
					"Número de taquillas no positivo.");

		}
		this.id = id;
		this.ubicacion = ubicacion;
		this.horario = horario;
		this.operativo = operativo;
		this.numeroTaquillas = numeroTaquillas;
	}

	/**
	 * Inicializa el PickingPoint operativo con la id, ubicación, horario
	 * semanal y número de taquillas.
	 * 
	 * @param id        id de la taquilla.
	 * @param ubicacion ubicación de la taquilla.
	 * @param horario   forma en la que se representa el día de la semana y la
	 *                  hora de apertura y cierre de cada día. Esquema:
	 *                  [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 * @throws IllegalArgumentException si alguno de los argumentos es null.
	 * @throws IllegalArgumentException si el horario no contiene los 7 dias de
	 *                                  la semana, que uno de los dias sea null,
	 *                                  que un dia no tenga exactamente 2 horas
	 *                                  o que la hora de apertura sea mayor que
	 *                                  la hora de cierre.
	 */
	public PickingPoint(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas) {
		this(id, ubicacion, horario, numeroTaquillas, true);
	}

	/**
	 * Devuelve true si el PackageLocker está operativo y false en caso
	 * contrario.
	 * 
	 * @return true si el PackageLocker está operativo y false si no lo está.
	 */
	public boolean getOperativo() {
		return operativo;

	}

	protected void setOperativo(boolean op) {
		operativo = op;
	}

	private void setOcupadas(int numero) {
		ocupadas = numero;
	}

	protected void setNumeroTaquillas(int numero) {
		numeroTaquillas = numero;
	}

	/**
	 * Devuelve el número de taquillas totales.
	 * 
	 * @return número de taquillas totales.
	 */
	public int getNumeroTaquillas() {
		return numeroTaquillas;
	}

	/**
	 * Devuelve una copia de las taquillas del PackageLocker.
	 * 
	 * @return copia de taquillas del PackageLocker.
	 */
	public ArrayList<Package> getPaquetes() {
		ArrayList<Package> paquetes = new ArrayList<>(getPaquetesInterno());
		paquetes.removeAll(Collections.singleton(null));
		return paquetes;
	}

	protected ArrayList<Package> getPaquetesInterno() {
		return paquetes;
	}

	protected abstract boolean borrable();

	/**
	 * Devuelve la id del PackageLocker.
	 * 
	 * @return id del PackageLocker.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Devuleve la una copia del horario.
	 * 
	 * @return copia del horario.
	 */
	public LocalTime[][] getHorario() {
		LocalTime[][] devolver = new LocalTime[7][2];
		System.arraycopy(horario, 0, devolver, 0, 7);
		return devolver;
	}

	/**
	 * Devuelve el número de taquillas llenas que tiene el PackageLocker.
	 * 
	 * @return número de taquillas llenas.
	 */
	public int getNumeroTaquillasLlenas() {
		return ocupadas;
	}

	/**
	 * Devuelve el número de taquillas vacías que tiene el PackageLocker.
	 * 
	 * @return número de taquillas vacías.
	 */
	public int getNumeroTaquillasVacias() {
		return getNumeroTaquillas() - getNumeroTaquillasLlenas();
	}

	/**
	 * Devuelve la copia de la ubicación geográfica del PackageLocker.
	 * 
	 * @return copia de la ubicación geográfica.
	 */
	public GPSCoordinate getUbicacion() {
		return new GPSCoordinate(ubicacion.getLatitudeGD(),
				ubicacion.getLongitudeGD());
	}

	/**
	 * Cambia el packageLocker a operativo si está fuera de servicio y
	 * viceversa.
	 */
	public void operatividad() {
		setOperativo(!getOperativo());
	}

	/**
	 * 
	 * @param idPaquete
	 * @return
	 */
	public int buscaPaquete(String idPaquete) {
		int i = 0;
		while (i < getPaquetes().size()) {
			if (getPaquetes().get(i).getId() == idPaquete) {
				return i;
			}
			i++;
		}
		return -1;
	}

	/**
	 * Devuelve el número de taquilla en el que se encuentra el paquete
	 * indicado.
	 * 
	 * @param idPaquete id del paquete.
	 * @return Número de la taquilla en la que está el paquete.
	 * @throws IllegalArgumentException Si la id es null
	 * @throws IllegalArgumentException Si no existe el paquete en el
	 *                                  PackageLocker indicado.
	 */
	public int locaclizaPaquete(String idPaquete) {
		if (idPaquete == null) {
			throw new IllegalArgumentException("La id es null.");
		}
		int posicion = buscaPaquete(idPaquete);
		if (posicion == -1) {
			throw new IllegalArgumentException(
					"No existe ese paquete en el PickingPoint.");
		} else {
			return posicion;
		}

	}

	/**
	 * Asigna el paquete dado a una taquilla.
	 * 
	 * @param paquete paquete a guardar.
	 * @throws IllegalArgumentException Si el paquete es null.
	 * @throws IllegalStateException    Si el PackageLocker está lleno.
	 * @throws IllegalStateException    Si hay otro paquete con la misma id.
	 */
	public void asignaPaquete(Package paquete) {

		if (paquete == null) {
			throw new IllegalArgumentException("El paquete es null.");
		}
		if (!getOperativo()) {
			throw new IllegalStateException("PickingPoint no operativo.");
		}
		if (getNumeroTaquillasLlenas() == getNumeroTaquillas()) {
			throw new IllegalStateException("PickingPoint lleno.");
		}
		// comprueba que no haya un paquete con la misma id en el PackageLocker.

		if (buscaPaquete(paquete.getId()) != -1) {
			throw new IllegalStateException("Hay otro paquete con la misma id");
		}

		// guarda el paquete en la primera taquilla libre.
		int i = 0;
		while (i < getNumeroTaquillas()) {
			if (getPaquetesInterno().get(i) == null) {
				getPaquetesInterno().set(i, paquete);
				i = getNumeroTaquillas();
			} else {
				i++;
			}
		}

		setOcupadas(getNumeroTaquillasLlenas() + 1);

	}

	/**
	 * Muestra el paquete de la taquilla dada.
	 * 
	 * @param idTaquilla id de la taquilla de la que sacar el paquete.
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia.
	 * @return paquete que estaba en la taquilla indicada.
	 */
	public Package getPaquete(int idTaquilla) {

		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(
					"Número de taquilla erroneo. Debe estar comprendido entre 0 y numero de taquillas -1");
		}

		if (getPaquetesInterno().get(idTaquilla) == null) {
			throw new IllegalStateException("Esta taquilla está vacía.");
		}

		return getPaquetesInterno().get(idTaquilla);
	}

	/**
	 * Borra el paquete de la taquilla dada.
	 * 
	 * @param idTaquilla id de la taquilla de la que sacar el paquete.
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia.
	 */
	public void borraPaquete(int idTaquilla) {

		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(
					"Número de taquilla erroneo. Debe estar comprendido entre 0 y numero de taquillas -1");
		}

		if (getPaquetesInterno().get(idTaquilla) == null) {
			throw new IllegalStateException("Esta taquilla está vacía.");
		}
		getPaquetesInterno().set(idTaquilla, null);
		setOcupadas(getNumeroTaquillasLlenas() - 1);

	}

	/**
	 * Modifica el estado del paquete a sacado de la taquilla dada.
	 * 
	 * @param idTaquilla  id de la taquilla de la que sacar el paquete.
	 * @param fechaSacada fecha en la que se saca el paquete.
	 * @param dia         dia de la semana que se saca, lunes = 0.
	 * @param hora        hora en la que se saca.
	 * 
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia o si la fecha
	 *                                  de entrega ha sido superada.
	 */
	public void sacaPaquete(int idTaquilla, LocalDate fechaSacada, int dia,
			LocalTime hora) {
		if (!getOperativo()) {
			throw new IllegalStateException(
					"El PickingPoint no está operativo.");

		}
		if (!getHorario()[dia][0].isAfter(hora)
				&& !getHorario()[dia][1].isBefore(hora)) {
			throw new IllegalArgumentException(
					"El PickingPoint está cerrado a esta hora del dia.");
		}
		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(
					"Número de taquilla erroneo. Debe estar comprendido entre 0 y numero de taquillas -1");
		}
		if (fechaSacada == null) {
			throw new IllegalArgumentException("La fecha es nula.");

		}
		if (getPaquetesInterno().get(idTaquilla) == null) {
			throw new IllegalStateException("Esta taquilla está vacía.");

		}
		getPaquetesInterno().get(idTaquilla).recogido(fechaSacada);
	}

	/**
	 * Modifica el estado del paquete a devuelto de la taquilla dada.
	 * 
	 * @param idTaquilla id de la taquilla de la que se devuelve el paquete.
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia.
	 */
	public void devuelvePaquete(int idTaquilla) {
		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(
					"Número de taquilla erroneo. Debe estar comprendido entre 0 y numero de taquillas -1");
		}
		if (getPaquetesInterno().get(idTaquilla) == null) {
			throw new IllegalStateException("Esta taquilla está vacía.");
		}
		getPaquetesInterno().get(idTaquilla).devuelto();
	}
}
