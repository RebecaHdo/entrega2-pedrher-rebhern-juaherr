package es.uva.inf.poo.amazingco;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import es.uva.inf.poo.amazingco.Kiosk;
import es.uva.inf.poo.maps.GPSCoordinate;

import org.junit.Test;

public class KioskTest {

	// Test AsignaPaquete()

	@Test
	public void testAsignaPaqueteValidoVarios() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Package paquete0 = new Package("0000000000", 0, true);
		Package paquete1 = new Package("0000000011", 0, false);
		kiosk.asignaPaquete(paquete0);
		kiosk.asignaPaquete(paquete1);
		assertEquals(paquete0,
				kiosk.getPaquete(kiosk.locaclizaPaquete("0000000000")));
		assertEquals(paquete1,
				kiosk.getPaquete(kiosk.locaclizaPaquete("0000000011")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAsignaPaqueteCertificado() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		String[] dni = { "1234" };

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Package paquete0 = new Package("0000000000", 0, false, dni);// paquete
																	// certificado
		kiosk.asignaPaquete(paquete0);

	}

	// Test SacaPaquete()

	@Test
	public void testSacaPaqueteValido() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2,true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
		Package paquete1 = new Package("0000000011", 0, false);// paquete no
																// pagado
		kiosk.asignaPaquete(paquete0);
		kiosk.asignaPaquete(paquete1);
		kiosk.sacaPaquete(kiosk.locaclizaPaquete("0000000000"), LocalDate.now(),
				0, LocalTime.of(8, 1));
		kiosk.sacaPaquete(kiosk.locaclizaPaquete("0000000011"), LocalDate.now(),
				0, LocalTime.of(8, 1));

		assertEquals(1, paquete0.getEstado());
		assertEquals(1, paquete1.getEstado());
	}

	@Test
	public void testKioskStringGPSCoordinateLocalTimeArrayArrayInt() {
		// TODO preguntar a felix si copiamos y pegamos código de la bateria del
		// pickingPoint o no lo hacemos directamente.

	}

	@Test
	public void testKioskStringGPSCoordinateLocalTimeArrayArrayIntBoolean() {
//TODO como el de arriba	
	}

	@Test
	public void testGetDineroValido() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Package paquete0 = new Package("0000000000", 1, false);// paquete no
																// pagado
		Package paquete1 = new Package("0000000011", 2, false);// paquete no
																// pagado

		kiosk.asignaPaquete(paquete0);
		kiosk.sacaPaquete(kiosk.locaclizaPaquete("0000000000"), LocalDate.now(),
				0, LocalTime.of(8, 1));
		assertEquals(1, kiosk.getDinero(), 0.01);

		kiosk.asignaPaquete(paquete1);
		kiosk.sacaPaquete(kiosk.locaclizaPaquete("0000000011"), LocalDate.now(),
				0, LocalTime.of(8, 1));
		assertEquals(3, kiosk.getDinero(), 0.01);
	}

	@Test
	public void testDineroAmazingCo() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Package paquete0 = new Package("0000000000", 1, false);// paquete no
																// pagado
		Package paquete1 = new Package("0000000011", 2, false);// paquete no
																// pagado

		kiosk.asignaPaquete(paquete0);
		kiosk.sacaPaquete(kiosk.locaclizaPaquete("0000000000"), LocalDate.now(),
				0, LocalTime.of(8, 1));
		kiosk.dineroAmazingCo();
		assertEquals(0, kiosk.getDinero(), 0.01);
	}

	@Test
	public void testModificarNumTaquillasValido() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
		kiosk.modificarNumTaquillas(1);
		assertEquals(2,kiosk.getNumeroTaquillas());
		
		kiosk.modificarNumTaquillas(-1);
		assertEquals(1,kiosk.getNumeroTaquillas());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testModificarNumTaquillasMenor1() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
		kiosk.modificarNumTaquillas(-1);
	}

}
