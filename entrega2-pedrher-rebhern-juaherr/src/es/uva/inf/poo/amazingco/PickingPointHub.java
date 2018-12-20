package es.uva.inf.poo.amazingco;

import java.time.LocalTime;
import java.util.ArrayList;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PickingPointHub extends PickingPoint {

	private ArrayList<GroupablePickingPoint> listaPuntos;

	private int numPackageLockers = 0;

	/**
	 * 
	 * @param id
	 * @param ubicacion
	 * @param horario
	 * @param puntosRecogida
	 */
	public PickingPointHub(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, GroupablePickingPoint[] puntosRecogida) {
		this(id, ubicacion, horario, puntosRecogida, true);
	}

	/**
	 * 
	 * @param id
	 * @param ubicacion
	 * @param horario
	 * @param puntosRecogida
	 * @param operativo
	 */
	public PickingPointHub(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, GroupablePickingPoint[] puntosRecogida,
			boolean operativo) {
		super(id, ubicacion, horario, 1, operativo);

		setNumeroTaquillas(0);

		if (puntosRecogida.length < 2) {
			throw new IllegalArgumentException(
					"Minimo tiene que haber dos puntos de recogida.");
		}
		listaPuntos = new ArrayList<>();

		for (int i = 0; i < puntosRecogida.length; i++) {
			addPickingPoint(puntosRecogida[i]);
		}

		for (int i = 0; i < getNumeroTaquillas(); i++) {
			getPaquetesInterno().add(null);
		}
	}

	private ArrayList<GroupablePickingPoint> getListaPuntosInterna() {
		return listaPuntos;
	}

	/**
	 * Devuelve un array con todos los PickingPoint.
	 * 
	 * @return array de todos los PickingPoint.
	 */
	public GroupablePickingPoint[] getListaPuntos() {
		return getListaPuntosInterna().toArray(new GroupablePickingPoint[0]);
	}

	/**
	 * Devuelve el número de PickingPoints que hay.
	 * 
	 * @return número de PickingPoints que hay.
	 */
	public int getNumPuntos() {
		return getListaPuntosInterna().size();
	}

	/**
	 * Comprueba si un PickingPoint esta en el hub mediante su Id.
	 * 
	 * @param idPuntoRecogida Id del PickingPoint a comprobar.
	 * @return true si está el PickingPoint y alse si no.
	 * @throws IllegalArgumentException si la id es null.
	 */
	public boolean estaPuntoRecogida(String idPuntoRecogida) {
		if (idPuntoRecogida == null) {
			throw new IllegalArgumentException("La id es null.");
		}
		return (posPuntoRecogida(idPuntoRecogida) != -1);
	}

	private int posPuntoRecogida(String idPuntoRecogida) {
		for (int j = 0; j < getListaPuntosInterna().size(); j++) {
			if (getListaPuntosInterna().get(j).getId() == idPuntoRecogida) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * Añade el GroupablePickingPoint pasado siempre que se pueda.
	 * 
	 * @param puntoRecogida GroupablePickingPoint a añadir.
	 * 
	 * @throws IllegalArgumentException si el punto es null.
	 * @throws IllegalArgumentException si la ubicación no es la misma que la
	 *                                  del Hub.
	 * @throws IllegalArgumentException si ya hay un punto con la Id del punto
	 *                                  pasado.
	 */
	public void addPickingPoint(GroupablePickingPoint puntoRecogida) {
		if (puntoRecogida == null) {
			throw new IllegalArgumentException(
					"Uno de los puntos de recogida es null.");
		}
		if (!getUbicacion().equals(puntoRecogida.getUbicacion())) {
			throw new IllegalArgumentException(
					"Uno de los puntos de recogida no está en la misma ubicación.");
		}

		if (posPuntoRecogida(puntoRecogida.getId()) != -1) {
			throw new IllegalArgumentException(
					"Ya hay un punto de recogida con ese nombre.");
		}

		getListaPuntosInterna().add(numPackageLockers, puntoRecogida);

		if (puntoRecogida.getClass() == PackageLocker.class) {
			setNumPackageLockers(getNumPackageLockers() + 1);

		}
		int tam = getNumeroTaquillas();
		tam += puntoRecogida.getNumeroTaquillas();
		setNumeroTaquillas(tam);

	}

	private int getNumPackageLockers() {
		return numPackageLockers;
	}

	private void setNumPackageLockers(int num) {
		numPackageLockers = num;
	}

	/**
	 * Borra un GroupablePickingPoint mediente Id siempre que sea posible.
	 * 
	 * @param puntoRecogida Id del punto a borrar.
	 * 
	 * @throws IllegalStateException    si al borrar ese punto el Hub se quedára
	 *                                  con menos de dos.
	 * @throws IllegalStateException    si el punto a borrar tiene paquetes
	 *                                  dentro de él.
	 * @throws IllegalArgumentException si la Id es null.
	 * @throws IllegalArgumentException si no existe ningún punto con esa Id.
	 */
	public void removePickingPoints(String idPuntoRecogida) {
		if (idPuntoRecogida == null) {
			throw new IllegalArgumentException("La String es null.");
		}

		if (getNumPuntos() < 3) {
			throw new IllegalStateException(
					"Tiene que haber al menos dos puntos de recogida.");

		}
		int posicion = posPuntoRecogida(idPuntoRecogida);

		if (posicion == -1) {
			throw new IllegalArgumentException(
					"No hay ningún punto de recogida con esa id.");
		}
		if (!getListaPuntosInterna().get(posicion).borrable()) {
			throw new IllegalStateException("La taquilla tiene paquetes.");
		}
		if (getListaPuntosInterna().get(posicion)
				.getClass() == PackageLocker.class) {
			setNumPackageLockers(getNumPackageLockers() - 1);
		}
		getListaPuntosInterna().remove(posicion);
	}

	private void compruebaOperativo() {
		if (!buscaOperativo()) {
			setOperativo(false);
		}
	}

	private void compruebaTam() {
		int tam = 0;
		for (int i = 0; i < getListaPuntosInterna().size(); i++) {
			tam += getListaPuntosInterna().get(i).getNumeroTaquillas();
		}
		setNumeroTaquillas(tam);
	}

	/**
	 * Actualiza el numero de taquillas antes de devolverlo.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#getNumeroTaquillas()
	 */
	@Override
	public int getNumeroTaquillas() {
		compruebaTam();
		return super.getNumeroTaquillas();
	}

	/**
	 * Actualiza la operatividad del Hub antes de devolverlo.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#getOperativo()
	 */
	@Override
	public boolean getOperativo() {
		compruebaOperativo();
		return super.getOperativo();
	}

	/**
	 * Une el getPaquetes() de todos los puntos en el Hub.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#getPaquetes()
	 */
	@Override
	public ArrayList<Package> getPaquetes() {
		ArrayList<Package> paquetes = new ArrayList<>();
		for (int i = 0; i < getListaPuntosInterna().size(); i++) {
			paquetes.addAll(getListaPuntosInterna().get(i).getPaquetes());
		}
		return paquetes;

	}

	/**
	 * Es borrable si todos los puntos del Hub son borrables.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#borrable()
	 */
	@Override
	protected boolean borrable() {
		for (int i = 0; i < getListaPuntosInterna().size(); i++) {

			if (!getListaPuntosInterna().get(i).borrable()) {
				return false;
			}
		}
		return true;

	}

	private boolean buscaOperativo() {
		int i = 0;
		while (i < getListaPuntosInterna().size()) {
			if (getListaPuntosInterna().get(i).getOperativo()) {
				return true;
			}
			i++;
		}
		return false;
	}

	/**
	 * Cambia el estado de todos los puntos internos a no operativo si el Hub se
	 * pasa a no operativo, solo puede ser puesto en operativo de nuevo si uno
	 * de los puntos internos está operativo.
	 * 
	 * @throws IllegalStateException si se intenta poner en operativo cuando
	 *                               todos los puntos internos están en no
	 *                               operativo.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#operatividad()
	 * 
	 */
	@Override
	public void operatividad() {
		if (getOperativo()) {
			setOperativo(false);

			for (int i = 0; i < getListaPuntosInterna().size(); i++) {
				if (getListaPuntosInterna().get(i).getOperativo()) {
					getListaPuntosInterna().get(i).operatividad();
				}
			}
		} else {

			if (!buscaOperativo()) {
				throw new IllegalStateException(
						"Tiene que haber al menos un punto operativo para activar el Hub.");
			}
			setOperativo(true);

		}
	}

	/**
	 * Asigna el paquete a su lista principal como ademas de a un punto
	 * apropiado.
	 * 
	 * @throws IllegalStateException si se intenta asignar un paquete a
	 *                               contrarrembolso y no hay ningun Kiosk
	 *                               activo.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#asignaPaquete(Package)
	 */
	@Override
	public void asignaPaquete(Package paquete) {
		if (!paquete.getPagado()) {
			boolean kioskValido = false;
			for (int i = getNumPackageLockers(); i < getListaPuntosInterna()
					.size(); i++) {

				if (getListaPuntosInterna().get(i)
						.getNumeroTaquillasVacias() > 0) {
					kioskValido = true;

				}
			}
			if (!kioskValido) {
				throw new IllegalStateException(
						"No hay ningún kiosko valido donde guardar el paquete.");

			}
		}
		super.asignaPaquete(paquete);
		int i = 0;
		while (i < getListaPuntosInterna().size()) {
			if (getListaPuntosInterna().get(i).getNumeroTaquillasVacias() > 0
					&& getListaPuntosInterna().get(i).paqueteValido(paquete)) {
				getListaPuntosInterna().get(i).asignaPaquete(paquete);
				i = getListaPuntosInterna().size();
			}
			i++;
		}
	}

	/**
	 * Borra el paquete, mendiante su Id, de su lista principal como ademas del
	 * punto donde está guardado el paquete.
	 * 
	 * @throws IllegalArgumentException si la Id dada no pertenece a ningún
	 *                                  paquete guardado.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#borraPaquete(int)
	 */
	@Override
	public void borraPaquete(int idTaquilla) {
		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(
					"Número de taquilla erroneo. Debe estar comprendido entre 0 y numero de taquillas -1");
		}

		Package paquete = getPaquetesInterno().get(idTaquilla);

		super.borraPaquete(idTaquilla);

		int i = 0;
		while (i < getListaPuntosInterna().size()) {
			if (getListaPuntosInterna().get(i)
					.buscaPaquete(paquete.getId()) > 0) {

				int pos = getListaPuntosInterna().get(i)
						.locaclizaPaquete(paquete.getId());
				getListaPuntosInterna().get(i).borraPaquete(pos);
				i = getListaPuntosInterna().size();
			}
			i++;
		}

	}

	/**
	 * Comprueba si un paquete puede ser almacenado en el Hub.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#paqueteValido(Package)
	 */
	@Override
	public boolean paqueteValido(Package paquete) {
		int i = 0;
		while (i < getListaPuntosInterna().size()) {
			if (getListaPuntosInterna().get(i).paqueteValido(paquete)) {
				return true;
			}

			i++;
		}
		return false;
	}
}
