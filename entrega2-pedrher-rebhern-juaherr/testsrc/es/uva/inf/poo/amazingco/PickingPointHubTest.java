package es.uva.inf.poo.amazingco;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.Arrays;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PickingPointHubTest {

	@Test
	public void testGetPaquetes() {
		fail("Not yet implemented");
	}

	@Test
	public void testBorrable() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);
		
		assertTrue(pph.borrable());
	}

	@Test
	public void testBorrableFalse() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		Package paquete = new Package("0000000000", 0, true);
		kiosk.asignaPaquete(paquete);
		
		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);
		
		assertFalse(pph.borrable());
	}
	
	@Test
	public void testOperatividadAFalse() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1, true);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2, false);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos, true);
		
		pph.operatividad();
		
		assertFalse(pph.getOperativo());
		assertFalse(locker.getOperativo());
		assertFalse(kiosk.getOperativo());

	}

	@Test
	public void testOperatividadATrue() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1, true);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2, false);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos, false);
		
		pph.operatividad();
		
		assertTrue(pph.getOperativo());
		assertTrue(locker.getOperativo());
		assertFalse(kiosk.getOperativo());

	}
	@Test(expected = IllegalStateException.class)
	public void testOperatividadATrueTodosPuntosFalse() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1, false);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2, false);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos, false);
		
		pph.operatividad();


	}
	
	@Test
	public void testPaqueteValido() {
		fail("Not yet implemented");
	}

	@Test
	public void testAsignaPaquete() {
		fail("Not yet implemented");
	}

	@Test
	public void testBorraPaquete() {
		fail("Not yet implemented");
	}

	@Test
	public void testPickingPointHubSinOperatividad() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		assertEquals(3, pph.getNumeroTaquillas());
		assertEquals(0, pph.getNumeroTaquillasLlenas());
		assertTrue(pph.getOperativo());
		assertEquals(2, pph.getNumPuntos());
		assertTrue(pph.estaPuntoRecogida("locker"));
		assertTrue(pph.estaPuntoRecogida("kiosk"));

	}

	@Test
	public void testPickingPointHubSinOperatividadBuenOrden() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);
		PackageLocker locker2 = new PackageLocker("locker2", gps, horario, 1);

		GroupablePickingPoint[] puntos = { locker, kiosk, locker2 };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		assertEquals(4, pph.getNumeroTaquillas());
		assertEquals(0, pph.getNumeroTaquillasLlenas());
		assertTrue(pph.getOperativo());
		assertEquals(3, pph.getNumPuntos());
		assertTrue(pph.estaPuntoRecogida("locker"));
		assertTrue(pph.estaPuntoRecogida("kiosk"));
		assertTrue(pph.estaPuntoRecogida("locker2"));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubSinOperatividadMenosDeDosPuntos() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		GroupablePickingPoint[] puntos = { locker };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

	}

	@Test
	public void testPickingPointHubConOperatividad() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos,
				false);

		assertEquals(3, pph.getNumeroTaquillas());
		assertEquals(0, pph.getNumeroTaquillasLlenas());
		assertFalse(pph.getOperativo());
		assertEquals(2, pph.getNumPuntos());
		assertTrue(pph.estaPuntoRecogida("locker"));
		assertTrue(pph.estaPuntoRecogida("kiosk"));

	}

	@Test
	public void testPickingPointHubConOperatividadBuenOrden() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);
		PackageLocker locker2 = new PackageLocker("locker2", gps, horario, 1);

		GroupablePickingPoint[] puntos = { locker, kiosk, locker2 };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos,
				false);

		assertEquals(4, pph.getNumeroTaquillas());
		assertEquals(0, pph.getNumeroTaquillasLlenas());
		assertFalse(pph.getOperativo());
		assertEquals(3, pph.getNumPuntos());
		assertTrue(pph.estaPuntoRecogida("locker"));
		assertTrue(pph.estaPuntoRecogida("kiosk"));
		assertTrue(pph.estaPuntoRecogida("locker2"));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperatividadMenosDeDosPuntos() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		GroupablePickingPoint[] puntos = { locker };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos,
				false);

	}

	@Test
	public void testGetListaPuntos() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);
		Kiosk kiosk2 = new Kiosk("kiosk2", gps, horario, 2);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		assertEquals(2, pph.getListaPuntos().length);
		assertTrue(Arrays.asList(pph.getListaPuntos()).contains(locker));
		assertTrue(Arrays.asList(pph.getListaPuntos()).contains(kiosk));
		assertFalse(Arrays.asList(pph.getListaPuntos()).contains(kiosk2));

	}

	@Test
	public void testGetNumPuntos() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		assertEquals(2, pph.getNumPuntos());
	}

	@Test
	public void testEstaPuntoRecogida() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);
		Kiosk kiosk2 = new Kiosk("kiosk2", gps, horario, 2);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		assertTrue(pph.estaPuntoRecogida("kiosk"));
		assertTrue(pph.estaPuntoRecogida("locker"));
		assertFalse(pph.estaPuntoRecogida("kiosk2"));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddPickingPointNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		Kiosk kiosk2 = null;

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);
		pph.addPickingPoint(kiosk2);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddPickingPointMalUbicacion() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		GPSCoordinate gps2 = new GPSCoordinate(42.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		Kiosk kiosk2 = new Kiosk("kiosk2", gps2, horario, 2);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);
		pph.addPickingPoint(kiosk2);

		assertEquals(6, pph.getNumeroTaquillas());
		assertEquals(4, pph.getNumPuntos());
		assertTrue(pph.estaPuntoRecogida("locker2"));
		assertTrue(pph.estaPuntoRecogida("kiosk2"));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddPickingPointRepetido() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		Kiosk kiosk2 = new Kiosk("kiosk", gps, horario, 2);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);
		pph.addPickingPoint(kiosk2);

	}

	@Test
	public void testAddPickingPoint() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		PackageLocker locker2 = new PackageLocker("locker2", gps, horario, 1);
		Kiosk kiosk2 = new Kiosk("kiosk2", gps, horario, 2);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);
		pph.addPickingPoint(locker2);
		pph.addPickingPoint(kiosk2);

		assertEquals(6, pph.getNumeroTaquillas());
		assertEquals(4, pph.getNumPuntos());
		assertTrue(pph.estaPuntoRecogida("locker2"));
		assertTrue(pph.estaPuntoRecogida("kiosk2"));

	}

	@Test
	public void testRemovePickingPoints() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);
		
		PackageLocker locker2 = new PackageLocker("locker2", gps, horario, 1);
		Kiosk kiosk2 = new Kiosk("kiosk2", gps, horario, 2);

		GroupablePickingPoint[] puntos = { locker, kiosk, kiosk2, locker2 };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		pph.removePickingPoints("kiosk");
		pph.removePickingPoints("locker");


		assertEquals(3, pph.getNumeroTaquillas());
		assertEquals(2, pph.getNumPuntos());
		assertFalse(pph.estaPuntoRecogida("locker"));
		assertFalse(pph.estaPuntoRecogida("kiosk"));
		assertTrue(pph.estaPuntoRecogida("kiosk2"));
		assertTrue(pph.estaPuntoRecogida("locker2"));


	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemovePickingPointsNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		Kiosk kiosk2 = new Kiosk("kiosk2", gps, horario, 2);

		GroupablePickingPoint[] puntos = { locker, kiosk, kiosk2 };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		pph.removePickingPoints(null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemovePickingPointsIdMal() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		Kiosk kiosk2 = new Kiosk("kiosk2", gps, horario, 2);

		GroupablePickingPoint[] puntos = { locker, kiosk, kiosk2 };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		pph.removePickingPoints("locker2");

	}

	@Test(expected = IllegalStateException.class)
	public void testRemovePickingPointsMenosDeDos() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		pph.removePickingPoints("kiosk");

	}

	@Test(expected = IllegalStateException.class)
	public void testRemovePickingPointsHayPaquetes() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);
		Kiosk kiosk2 = new Kiosk("kiosk2", gps, horario, 2);

		Package paquete = new Package("0000000000", 0, true);
		kiosk.asignaPaquete(paquete);
		GroupablePickingPoint[] puntos = { locker, kiosk, kiosk2 };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		pph.removePickingPoints("kiosk");

	}

}
