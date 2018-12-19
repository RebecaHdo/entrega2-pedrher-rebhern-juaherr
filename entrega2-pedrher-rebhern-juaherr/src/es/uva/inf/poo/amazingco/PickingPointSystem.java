package es.uva.inf.poo.amazingco;

import java.time.LocalTime;
import java.util.ArrayList;
import es.uva.inf.poo.maps.GPSCoordinate;

//TODO acabar ultimo punto y revisar las otras, por si aca.

/**
 * Administra los PickingPoint, pudiendo añadirlos o eliminarlos, ver los
 * operativos y fuera de servicio, encontrar los que hay en un radio dado, ver
 * cuales tienen taquillas vacias y cuales están completamente llenos.
 * 
 * @author juaherr
 * @author rebhern
 * @author pedrher
 * 
 */
public class PickingPointSystem {

	private ArrayList<PickingPoint> listaPickingPoint;

	private ArrayList<PickingPoint> getListaPickingPoint() {
		return listaPickingPoint;
	}

	/**
	 * Inicializa PickingPointSystem.
	 */
	public PickingPointSystem() {
		listaPickingPoint = new ArrayList<>();
	}

	/**
	 * Añade el pickingPoint a la lista pickingPoints.
	 * 
	 * @param pickingPoint pickingPoint a añadir.
	 * 
	 * @throws IllegalArgumentException si el pickingPoint es nulo.
	 */
	public void addPickingPoint(PickingPoint pickingPoint) {
		if (pickingPoint == null) {
			throw new IllegalArgumentException("El pickingPoint es nulo");
		}
		listaPickingPoint.add(pickingPoint);
	}

	/**
	 * Devuelve todos los PickingPoint.
	 * 
	 * @return todos los PickingPoint.
	 */
	public PickingPoint[] getTodosPickingPoint() {
		PickingPoint[] vector = new PickingPoint[getListaPickingPoint().size()];
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			vector[i] = getListaPickingPoint().get(i);
		}
		return vector;
	}

	/**
	 * Devuelve el PickingPoint correspondiente a la id introducida.
	 * 
	 * @param id id del PickingPoint que se quiere obtener.
	 * @return PickingPoint con la id dada.
	 * @throws IllegalStateException    si no hay PickingPoint creados.
	 * @throws IllegalArgumentException si no existe ningún PickingPoint con la
	 *                                  id introducida.
	 * 
	 */
	public PickingPoint getPickingPoint(String id) {
		if (getListaPickingPoint().isEmpty()) {
			throw new IllegalStateException("No hay PickingPoints creados.");
		}
		int i = 0;
		PickingPoint pickingPoint = null;
		while (i < getListaPickingPoint().size()) {
			if (id == getListaPickingPoint().get(i).getId()) {
				pickingPoint = getListaPickingPoint().get(i);
				i = getListaPickingPoint().size();
			} else {
				i++;

			}
		}
		if (pickingPoint == null) {
			throw new IllegalArgumentException(
					"No existe ningún PickingPoint con esa id.");

		} else {
			return pickingPoint;
		}
	}

	/**
	 * Devuelve todos los PickingPoints operativos.
	 * 
	 * @return PickingPoints operativos.
	 * @throws IllegalStateException si no hay PickingPoints creados.
	 * 
	 */
	public PickingPoint[] getPickingPointOperativos() {
		// recorre dos veces la lista de PickingPoints creados. Una para
		// conocer el
		// tamaño del vector que se devulve y otra para rellenarlo.
		if (getListaPickingPoint().isEmpty()) {
			throw new IllegalStateException("No hay PickingPoints creados.");
		}
		int contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (getListaPickingPoint().get(i).getOperativo()) {
				contador++;
			}
		}
		PickingPoint[] vector = new PickingPoint[contador];
		contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (getListaPickingPoint().get(i).getOperativo()) {
				vector[contador] = getListaPickingPoint().get(i);
				contador++;
			}
		}
		return vector;
	}

	/**
	 * Devuelve todos los PickingPoints fuera de servicio.
	 * 
	 * @return PickingPoints fuera de servicio.
	 * @throws IllegalStateException si no hay PickingPoints creados.
	 * 
	 */
	public PickingPoint[] getPickingPointFueraDeServicio() {
		// recorre dos veces la lista de PickingPoints creados. Una para
		// conocer el
		// tamaño del vector que se devulve y otra para rellenarlo.
		if (getListaPickingPoint().isEmpty()) {
			throw new IllegalStateException("No hay PickingPoints guardados.");
		}
		int contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (!getListaPickingPoint().get(i).getOperativo()) {
				contador++;
			}
		}
		PickingPoint[] vector = new PickingPoint[contador];
		contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (!getListaPickingPoint().get(i).getOperativo()) {
				vector[contador] = getListaPickingPoint().get(i);
				contador++;
			}
		}
		return vector;
	}

	/**
	 * Devuelve los PackagerLockers en radio dado desde una ubicación dada.
	 * 
	 * @param ubicacion zona desde la que se genera el radio.
	 * @param radio     distancia desde la ubicación que se quiere abarcar.
	 * @return todos los PickingPoints operativos que están en el radio indicado
	 *         desde la ubicación indicada.
	 * @throws IllegalArgumentException si la ubicaón introducida es nula.
	 * @throws IllegalArgumentException si el radio dado es negativo.
	 * @throws IllegalStateException    si no hay PickingPoints creados.
	 */
	public PickingPoint[] getPickingPointEnZona(GPSCoordinate ubicacion,
			double radio) {
		if (ubicacion == null) {
			throw new IllegalArgumentException("La ubicación es nula.");
		}
		if (getListaPickingPoint().isEmpty()) {
			throw new IllegalStateException("No hay PickingPoints creados.");
		}
		if (radio < 0) {
			throw new IllegalArgumentException(
					"El radio no puede ser negativo.");
		}
		int contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			double distancia = getListaPickingPoint().get(i).getUbicacion()
					.getDistanceTo(ubicacion);
			distancia *= 1000;
			if (distancia <= radio) {
				contador++;
			}
		}
		PickingPoint[] vector = new PickingPoint[contador];
		contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			double distancia = getListaPickingPoint().get(i).getUbicacion()
					.getDistanceTo(ubicacion);
			distancia *= 1000;
			if (distancia <= radio) {
				vector[contador] = getListaPickingPoint().get(i);
				contador++;
			}
		}
		return vector;

	}

	/**
	 * Devuelve todos los PickingPoints con alguna taquilla vacia.
	 * 
	 * @return PickingPoints con taquillas vacias.
	 * @throws IllegalStateException si no hay PickingPoints creados.
	 */
	public PickingPoint[] getPickingPointTaquillasVacias() {
		// recorre dos veces la lista de PickingPoints creados. Una para
		// conocer el
		// tamaño del vector que se devulve y otra para rellenarlo.
		if (getListaPickingPoint().isEmpty()) {
			throw new IllegalStateException("No hay PickingPoints creados.");
		}
		int contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (getListaPickingPoint().get(i).getNumeroTaquillasVacias() != 0) {
				contador++;
			}
		}
		PickingPoint[] vector = new PickingPoint[contador];
		contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (getListaPickingPoint().get(i).getNumeroTaquillasVacias() != 0) {
				vector[contador] = getListaPickingPoint().get(i);
				contador++;
			}
		}
		return vector;
	}

	/**
	 * Devuelve todos los PickingPoints operativos con alguna taquilla vacia.
	 * 
	 * @return PickingPoints operativos con taquillas vacias.
	 * @throws IllegalStateException si no hay PickingPoints creados.
	 */
	public PickingPoint[] getPickingPointTaquillasVaciasOperativas() {
		// recorre dos veces la lista de PickingPoints creados. Una para
		// conocer el
		// tamaño del vector que se devulve y otra para rellenarlo.
		if (getListaPickingPoint().isEmpty()) {
			throw new IllegalStateException("No hay PickingPoints creados.");
		}
		int contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (getListaPickingPoint().get(i).getNumeroTaquillasVacias() != 0
					&& getListaPickingPoint().get(i).getOperativo()) {
				contador++;
			}
		}
		PickingPoint[] vector = new PickingPoint[contador];
		contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (getListaPickingPoint().get(i).getNumeroTaquillasVacias() != 0
					&& getListaPickingPoint().get(i).getOperativo()) {
				vector[contador] = getListaPickingPoint().get(i);
				contador++;
			}
		}
		return vector;
	}

	/**
	 * Elimina un PickingPoint por id introducida.
	 * 
	 * @param id del PickingPoint a borrar.
	 * @throws IllegalStateException    si no hay ningún PickingPoint creado.
	 * @throws IllegalStateException    si hay paquetes sin recoger o devolver
	 *                                  en el PickingPoint.
	 * @throws IllegalArgumentException si no existe ningún PickingPoint con la
	 *                                  id introducida.
	 */
	public void eliminarPickingPoint(String id) {
		if (getListaPickingPoint().isEmpty()) {
			throw new IllegalStateException("No hay PickingPoints creado.");
		}
		int i = 0;
		boolean encontrado = false;
		while (i < getListaPickingPoint().size()) {
			PickingPoint pickingPoint = getListaPickingPoint().get(i);
			if (id == pickingPoint.getId()) {
				for (int j = 0; j < pickingPoint.getNumeroTaquillas(); j++) {
					if (pickingPoint.getPaquetes().get(j) != null) {
						throw new IllegalStateException(
								"Todavia hay paquetes en el PickingPoint.");
					}
				}
				getListaPickingPoint().remove(i);
				i = getListaPickingPoint().size();
				encontrado = true;
			} else {
				i++;
			}
		}
		if (!encontrado) {
			throw new IllegalArgumentException(
					"No existe ningún PickingPoint con esa id.");
		}
	}
}
