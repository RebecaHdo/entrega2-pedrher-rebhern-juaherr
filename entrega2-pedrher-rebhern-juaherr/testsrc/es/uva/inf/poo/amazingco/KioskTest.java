package es.uva.inf.poo.amazingco;

import static org.junit.Assert.*;

import java.time.LocalTime;

import es.uva.inf.poo.amazingco.Kiosk;
import es.uva.inf.poo.maps.GPSCoordinate;

import org.junit.Test;

public class KioskTest {

	// Test AsignaPaquete()

	@Test
	public void testAsignaPaqueteValidoVarios() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Package paquete0 = new Package("0000000000", 0, true);
		Package paquete1 = new Package("0000000011", 0, false);
		kiosk.asignaPaquete(paquete0);
		kiosk.asignaPaquete(paquete1);
		assertEquals(kiosk.getPaquete(kiosk.locaclizaPaquete("0000000000")), paquete0);
		assertEquals(kiosk.getPaquete(kiosk.locaclizaPaquete("0000000011")), paquete1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAsignaPaqueteCertificado() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		String[] dni = { "1234" };

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Package paquete0 = new Package("0000000000", 0, false, dni);//paquete certificado
		kiosk.asignaPaquete(paquete0);

	}

	// Test SacaPaquete()

	@Test
	public void testSacaPaqueteValido() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Package paquete0 = new Package("0000000000", 0, true);//paquete pagado
		Package paquete1 = new Package("0000000000", 0, false);//paquete no pagado
	}
	
	

	@Test
	public void testKioskStringGPSCoordinateLocalTimeArrayArrayInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testKioskStringGPSCoordinateLocalTimeArrayArrayIntBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDinero() {
		fail("Not yet implemented");
	}

	@Test
	public void testDineroAmazingCo() {
		fail("Not yet implemented");
	}

	@Test
	public void testModificarNumTaquillas() {
		fail("Not yet implemented");
	}

}