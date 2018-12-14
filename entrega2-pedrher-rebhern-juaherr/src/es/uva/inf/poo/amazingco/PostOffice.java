package es.uva.inf.poo.amazingco;

import java.time.LocalDate;
import java.time.LocalTime;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PostOffice extends PickingPoint implements IdentificationRegistry {
	
	private double dinero = 0;
	
	/**
	 * @see es.uva.inf.poo.amazingco.PickingPoint#PickingPoint(String,
	 *      GPSCoordinate, LocalTime[][],int)
	 */
	public PostOffice(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario) {
		super(id, ubicacion, horario, (int) Double.POSITIVE_INFINITY);
	}

	/**
	 * @see es.uva.inf.poo.amazingco.PickingPoint#PickingPoint(String,
	 *      GPSCoordinate, LocalTime[][],int ,boolean)
	 */
	public PostOffice(String id, GPSCoordinate ubicacion, LocalTime[][] horario,
			boolean operativo) {
		super(id, ubicacion, horario, (int) Double.POSITIVE_INFINITY,
				operativo);
	}
	
	private void setDinero(double num) {
		dinero = num;
	}

	/**
	 * Devuelve el dinero que se le debe a AmazingCo
	 * 
	 * @return dinero que se le debe a AmazingCO
	 */
	public double getDinero() {
		return dinero;
	}
	/**
	 * @see es.uva.inf.poo.amazingco.PickingPoint#sacaPaquete(int, LocalDate)
	 */
	@Override
	public void sacaPaquete(int idTaquilla, LocalDate fechaSacada) {
		super.sacaPaquete(idTaquilla, fechaSacada);

		Package paquete = getTaquillasInterno().get(idTaquilla);
		if (!paquete.getPagado()) {
			setDinero(getDinero() + paquete.getPrecio());
			paquete.pagado();
		}
		if (paquete.getCertificado()) {
			//TODO certificados
		}
	}
	
	@Override
	public boolean isPackagePickupRegistered(String packageCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Package getPackageRegistered(String packageCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRegisteredIdFor(String packageCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate getPickupDateFor(String packgeCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerCertifiedPackagePickup(Package p, String dni,
			LocalDate pickupDate) {
		// TODO Auto-generated method stub

	}

}
