package es.uva.inf.poo.amazingco;

import java.time.LocalTime;

import es.uva.inf.poo.maps.GPSCoordinate;

public class Kiosk extends GroupablePickingPoint{

	private double monedero = 0;
	
	public Kiosk(String id, GPSCoordinate ubicacion, LocalTime[][] horario,
			int numeroTaquillas) {
		super(id, ubicacion, horario, numeroTaquillas);
	}
	public Kiosk(String id, GPSCoordinate ubicacion, LocalTime[][] horario,
			int numeroTaquillas, boolean operativo) {
		super(id, ubicacion, horario, numeroTaquillas, operativo);
	}
	
	@Override
	public void asignaPaquete(Package paquete) {
		if (paquete == null) {
			throw new IllegalArgumentException("El paquete es null.");
		}
		if (paquete.getCertificado()) {
			throw new IllegalArgumentException("El paquete es certificado.");
		}
		if (paquete.getPagado()) {
			//TODO pago
		}
		super.asignaPaquete(paquete);

	}
}
