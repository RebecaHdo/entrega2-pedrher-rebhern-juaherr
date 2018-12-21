package es.uva.inf.poo.amazingco;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.Arrays;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PickingPointHubTest {

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

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1,
				true);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2, false);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos,
				true);

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

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1,
				true);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2, false);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos,
				false);

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

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1,
				false);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2, false);

		GroupablePickingPoint[] puntos = { locker, kiosk };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos,
				false);

		pph.operatividad();

	}

	@Test
	public void testAsignaPaquete() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);

		Package paquete = new Package("0000000000", 0, true);
		Package paquete2 = new Package("0000000011", 0, false);

		GroupablePickingPoint[] puntos = { kiosk, locker };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);
		pph.asignaPaquete(paquete);
		pph.asignaPaquete(paquete2);

		assertEquals(paquete,
				pph.getPaquete(pph.locaclizaPaquete("0000000000")));
		assertEquals(paquete2,
				pph.getPaquete(pph.locaclizaPaquete("0000000011")));
		assertEquals(paquete2,
				kiosk.getPaquete(kiosk.locaclizaPaquete("0000000011")));
		assertEquals(paquete,
				locker.getPaquete(locker.locaclizaPaquete("0000000000")));
		assertEquals(2, pph.getNumeroTaquillasLlenas());
		assertEquals(0, pph.getNumeroTaquillasVacias());
	}

	@Test(expected = IllegalStateException.class)
	public void testAsignaPaqueteNoKioskPaqueteContrarrembolso() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		PackageLocker locker2 = new PackageLocker("locker2", gps, horario, 1);

		Package paquete = new Package("0000000000", 0, false);

		GroupablePickingPoint[] puntos = { locker2, locker };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);
		pph.asignaPaquete(paquete);
	}

	@Test
	public void testGetPaquetes() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);

		Package paquete = new Package("0000000000", 0, true);
		Package paquete2 = new Package("0000000011", 0, false);

		GroupablePickingPoint[] puntos = { kiosk, locker };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);
		pph.asignaPaquete(paquete);
		pph.asignaPaquete(paquete2);

		assertTrue(pph.getPaquetes().contains(paquete));
		assertTrue(pph.getPaquetes().contains(paquete2));

	}
	@Test
	public void testGetOperativo() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);

		GroupablePickingPoint[] puntos = { kiosk, locker };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos, false);


		assertFalse(pph.getOperativo());

	}
	@Test
	public void testGetId() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);

		GroupablePickingPoint[] puntos = { kiosk, locker };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos, false);


		assertEquals("0",pph.getId());

	}
	
	@Test
	public void testGetNumeroTaquillas() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);

		GroupablePickingPoint[] puntos = { kiosk, locker };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);


		assertEquals(2,pph.getNumeroTaquillas());

	}
	@Test
	public void testGetNumeroTaquillasLLenas() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);

		GroupablePickingPoint[] puntos = { kiosk, locker };

		Package paquete = new Package("0000000000", 0, true);
		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		
		assertEquals(0,pph.getNumeroTaquillasLlenas());

		pph.asignaPaquete(paquete);

		assertEquals(1,pph.getNumeroTaquillasLlenas());

	}
	@Test
	public void testGetNumeroTaquillasVacias() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);

		GroupablePickingPoint[] puntos = { kiosk, locker };

		Package paquete = new Package("0000000000", 0, true);
		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		
		assertEquals(2,pph.getNumeroTaquillasVacias());

		pph.asignaPaquete(paquete);

		assertEquals(1,pph.getNumeroTaquillasVacias());

	}
	@Test
	public void testGetUbicacion() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);

		GroupablePickingPoint[] puntos = { kiosk, locker };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos, false);


		assertEquals(gps,pph.getUbicacion());

	}
	@Test
	public void testPaqueteValido() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);
		String[] dni = { "dni 100% real." };
		Package paquete = new Package("0000000000", 0, true);
		Package paquete2 = new Package("0000000011", 0, false);
		Package paquete3 = new Package("0000000022", 0, true, dni);
		Package paquete4 = new Package("0000000033", 0, false, dni);

		GroupablePickingPoint[] puntos = { kiosk, locker };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		assertTrue(pph.paqueteValido(paquete));
		assertTrue(pph.paqueteValido(paquete2));
		assertFalse(pph.paqueteValido(paquete3));
		assertFalse(pph.paqueteValido(paquete4));

	}

	@Test
	public void testBorraPaquete() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);

		Package paquete = new Package("0000000000", 0, true);
		Package paquete2 = new Package("0000000011", 0, false);

		GroupablePickingPoint[] puntos = { kiosk, locker };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);
		pph.asignaPaquete(paquete);
		pph.asignaPaquete(paquete2);

		pph.borraPaquete(pph.locaclizaPaquete("0000000000"));
		assertEquals(paquete2,
				pph.getPaquete(pph.locaclizaPaquete("0000000011")));
		assertEquals(paquete2,
				kiosk.getPaquete(kiosk.locaclizaPaquete("0000000011")));

		assertEquals(1, pph.getNumeroTaquillasLlenas());
		assertEquals(1, pph.getNumeroTaquillasVacias());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBorraPaqueteFueraDeRengoArriba() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);

		Package paquete = new Package("0000000000", 0, true);

		GroupablePickingPoint[] puntos = { kiosk, locker };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);
		pph.asignaPaquete(paquete);

		pph.borraPaquete(2);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testBorraPaqueteFueraDeRengoAbajo() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PackageLocker locker = new PackageLocker("locker", gps, horario, 1);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);

		Package paquete = new Package("0000000000", 0, true);

		GroupablePickingPoint[] puntos = { kiosk, locker };

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);
		pph.asignaPaquete(paquete);

		pph.borraPaquete(-1);

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

	@SuppressWarnings("unused")
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

	/*
	 * Pruebas no válidas del constructor con opción de operatividad.
	 */
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadIdNull() {
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

		PickingPointHub pph = new PickingPointHub(null, gps, horario, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadGpsNull() {
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

		PickingPointHub pph = new PickingPointHub("0", null, horario, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadHorarioNull() {
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0000000000", gps, null, 1, true);
	}

	/*
	 * Pruebas no válida del constructor con opción de operatividad donde se
	 * analiza el horario como un conjunto, por tanto se prueban los errores en
	 * los extremos.
	 */
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadOchoDias() {
		LocalTime[][] horarioMal = {
				{ LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) } };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadSeisDias() {
		LocalTime[][] horarioMal = {
				{ LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) } };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadPrimerDiaNull() {
		LocalTime[][] horarioMal = { null,
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadUltimoDiaNull() {
		LocalTime[][] horarioMal = {
				{ LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, null };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadHoraAperturaPrimerDiaNull() {
		LocalTime[][] horarioMal = { { null, LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadHoraCierrePrimerDiaNull() {
		LocalTime[][] horarioMal = { { LocalTime.of(8, 0), null },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadHoraAperturaUltimoDiaNull() {
		LocalTime[][] horarioMal = {
				{ LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ null, LocalTime.of(23, 00) } };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadHoraCierreUltimoDiaNull() {
		LocalTime[][] horarioMal = {
				{ LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), null } };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadPrimerDiaTresHoras() {
		LocalTime[][] horarioMal = {
				{ LocalTime.of(3, 30), LocalTime.of(8, 0),
						LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadPrimerDiaUnaHora() {
		LocalTime[][] horarioMal = { { LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadUltimoDiaTresHoras() {
		LocalTime[][] horarioMal = {
				{ LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(1, 0), LocalTime.of(2, 15),
						LocalTime.of(23, 00) } };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadUltimoDiaUnaHora() {
		LocalTime[][] horarioMal = {
				{ LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15) } };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadPrimerDiaHorasMalIntroducidas() {
		LocalTime[][] horarioMal = {
				{ LocalTime.of(23, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPickingPointHubConOperativadUltimoDiaHorasMalIntroducidas() {
		LocalTime[][] horarioMal = {
				{ LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(23, 15), LocalTime.of(23, 00) } };
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

		PickingPointHub pph = new PickingPointHub("0", gps, horarioMal, puntos,
				false);
	}

	/*
	 * Pruebas no válidas del constructor con opción de operativiad respecto al
	 * número de GroupablePickingPoints.
	 */

	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
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
	public void testEstaPuntoRecogidaNull() {
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

		pph.estaPuntoRecogida(null);

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

		GroupablePickingPoint[] puntos = { locker, kiosk };

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

	@Test
	public void testGetHorario() {
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

		assertArrayEquals(horario, pph.getHorario());
	}

	@Test
	public void testLocalizaPaquete() {
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

		Package paquete = new Package("0000000000", 0, true);

		PickingPointHub pph = new PickingPointHub("0", gps, horario, puntos);

		pph.asignaPaquete(paquete);
		assertEquals(0, pph.locaclizaPaquete("0000000000"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLocalizaPaqueteIdNull() {
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

		pph.locaclizaPaquete(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLocalizaPaqueteHubVacio() {
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

		pph.locaclizaPaquete("0000000000");
	}
}
