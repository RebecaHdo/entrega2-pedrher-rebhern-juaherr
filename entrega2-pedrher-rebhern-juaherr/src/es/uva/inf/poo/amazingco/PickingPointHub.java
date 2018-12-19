package es.uva.inf.poo.amazingco;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PickingPointHub extends PickingPoint {

	ArrayList<GroupablePickingPoint> listaPuntos = new ArrayList<>();

	int numPackageLockers = 0;

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
		super(id, ubicacion, horario, 0, operativo);

		if (puntosRecogida.length < 2) {
			throw new IllegalArgumentException(
					"Minimo tiene que haber dos puntos de recogida.");
		}
		for (int i = 0; i < puntosRecogida.length; i++) {
			addPickingPoint(puntosRecogida[i]);
		}
	}

	private ArrayList<GroupablePickingPoint> getListaPuntosInterna() {
		return listaPuntos;
	}

	/**
	 * 
	 * @return
	 */
	public GroupablePickingPoint[] getListaPuntos() {
		ArrayList<GroupablePickingPoint> lista = new ArrayList<>(
				getListaPuntosInterna());
		lista.removeAll(Collections.singleton(null));
		return lista.toArray(new GroupablePickingPoint[0]);
	}

	/**
	 * 
	 * @return
	 */
	public int getNumPuntos() {
		return getListaPuntosInterna().size();
	}

	/**
	 * 
	 * @param idPuntoRecogida
	 * @return
	 */
	public boolean estaPuntoRecogida(String idPuntoRecogida) {
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
	 * 
	 * @param puntoRecogida
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

		if (puntoRecogida.getClass() == PackageLocker.class) {
			setNumPackageLockers(getNumPackageLockers() + 1);

		}
		getListaPuntosInterna().add(numPackageLockers, puntoRecogida);
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
	 * 
	 * @param puntoRecogida
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

	/**
	 * 
	 */
	@Override
	public ArrayList<Package> getPaquetes() {
		ArrayList<Package> paquetes = new ArrayList<>();
		for (int i = 0; i < getListaPuntosInterna().size(); i++) {
			paquetes.addAll(getListaPuntosInterna().get(i).getPaquetes());
		}
		return paquetes;

	}

	@Override
	protected boolean borrable() {
		for (int i = 0; i < getListaPuntosInterna().size(); i++) {

			if (getListaPuntos().length == 0) {
				return true;
			}
		}
		return false;

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

	@Override
	public void asignaPaquete(Package paquete) {
		compruebaOperativo();
		compruebaTam();
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
			if (getListaPuntosInterna().get(i).getNumeroTaquillasVacias() > 0) {
				getListaPuntosInterna().get(i).asignaPaquete(paquete);
				i = getListaPuntosInterna().size();
			}
			i++;
		}
	}

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

	@Override
	public boolean paqueteValido(Package paquete) {
		int i = 0;
		while(i < getListaPuntosInterna().size()) {
			if(getListaPuntosInterna().get(i).paqueteValido(paquete)) {
				return true;
			}
		}
		return false;
	}
}
