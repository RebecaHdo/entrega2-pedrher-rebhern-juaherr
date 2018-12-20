package es.uva.inf.poo.amazingco;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PostOffice extends PickingPoint implements IdentificationRegistry {

	private double dinero = 0;
	ArrayList<Object[]> registro = new ArrayList<>();
	private static final String NO_EN_EL_REGISTRO = "El paquete no está en el registro.";

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

	private ArrayList<Object[]> getRegistro() {
		return registro;
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
	public void sacaPaquete(int idTaquilla, LocalDate fechaSacada, int dia,
			LocalTime hora) {

		Package paquete = getPaquetesInterno().get(idTaquilla);

		if (paquete == null) {
			throw new IllegalStateException("Esta taquilla está vacía.");
		}

		if (paquete.getCertificado()) {
			throw new IllegalStateException("El paquete está certificado");
		}

		super.sacaPaquete(idTaquilla, fechaSacada, dia, hora);

		if (!paquete.getPagado()) {
			setDinero(getDinero() + paquete.getPrecio());
			paquete.pagado();
		}

	}

	private int buscaRegistro(String packageCode) {
		int i = 0;
		while (i < getRegistro().size()) {
			if (((Package) getRegistro().get(i)[0]).getId() == packageCode) {
				return i;
			}
			i++;
		}
		return -1;
	}

	/**
	 * @see es.uva.inf.poo.amazingco.IdentificationRegistry#isPackagePickupRegistered(String)
	 */
	@Override
	public boolean isPackagePickupRegistered(String packageCode) {
		return (buscaRegistro(packageCode) != -1);
	}

	/**
	 * @see es.uva.inf.poo.amazingco.IdentificationRegistry#getPackageRegistered(String)
	 */
	@Override
	public Package getPackageRegistered(String packageCode) {
		if (!isPackagePickupRegistered(packageCode)) {
			throw new IllegalArgumentException(NO_EN_EL_REGISTRO);
		}
		return (Package) getRegistro().get(buscaRegistro(packageCode))[0];

	}

	/**
	 * @see es.uva.inf.poo.amazingco.IdentificationRegistry#getRegisteredIdFor(String)
	 */
	@Override
	public String getRegisteredIdFor(String packageCode) {
		if (!isPackagePickupRegistered(packageCode)) {
			throw new IllegalArgumentException(NO_EN_EL_REGISTRO);
		}
		return (String) getRegistro().get(buscaRegistro(packageCode))[1];
	}

	/**
	 * @see es.uva.inf.poo.amazingco.IdentificationRegistry#getPickupDateFor(String)
	 */
	@Override
	public LocalDate getPickupDateFor(String packageCode) {
		if (!isPackagePickupRegistered(packageCode)) {
			throw new IllegalArgumentException(NO_EN_EL_REGISTRO);
		}
		return (LocalDate) getRegistro().get(buscaRegistro(packageCode))[2];
	}

	/**
	 * @see es.uva.inf.poo.amazingco.IdentificationRegistry#registerCertifiedPackagePickup(Package,
	 *      String, LocalDate)
	 */
	@Override
	public void registerCertifiedPackagePickup(Package p, String dni,
			LocalDate pickupDate) {

		if (pickupDate == null) {
			throw new IllegalArgumentException("El pickupDate es null.");
		}
		if (pickupDate.isAfter(p.getFecha())) {
			throw new IllegalArgumentException(
					"La fecha de vencimiento ya ha pasado.");
		}
		Object[] resgistroPaquete = { p, dni, pickupDate };
		getRegistro().add(resgistroPaquete);

	}

	@Override
	protected boolean borrable() {
		ArrayList<Package> paq = getPaquetes();
		return paq.isEmpty();

	}

	/**
	 * Todos los paquetes son validos.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#PickingPoint(String,
	 *      GPSCoordinate, LocalTime[][],int, boolean)
	 */
	@Override
	public boolean paqueteValido(Package paquete) {
		if (paquete == null) {
			throw new IllegalArgumentException("El paquete es null.");
		}
		return true;
	}
}
