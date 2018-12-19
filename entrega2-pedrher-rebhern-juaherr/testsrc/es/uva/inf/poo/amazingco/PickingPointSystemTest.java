package es.uva.inf.poo.amazingco;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.Arrays;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PickingPointSystemTest {

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
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		assertEquals(1, pps.getTodosPickingPoint().length);
		assertEquals(kiosk, pps.getPickingPoint("0"));

		pps.addPickingPoint(locker);
		assertEquals(2, pps.getTodosPickingPoint().length);
		assertEquals(locker, pps.getPickingPoint("1"));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddPickingPointNull() {
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddPickingPointMismaId() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		PackageLocker locker = new PackageLocker("0", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		pps.addPickingPoint(locker);
	}

	@Test
	public void testGetTodosPickingPoint() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		pps.addPickingPoint(locker);
		assertEquals(2, pps.getTodosPickingPoint().length);
		assertTrue(Arrays.asList(pps.getTodosPickingPoint()).contains(kiosk));
		assertTrue(Arrays.asList(pps.getTodosPickingPoint()).contains(locker));
	}

	@Test
	public void testGetPickingPoint() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		pps.addPickingPoint(locker);
		assertEquals(2, pps.getTodosPickingPoint().length);
		assertEquals(kiosk, pps.getPickingPoint("0"));
		assertEquals(locker, pps.getPickingPoint("1"));

	}

	@Test(expected = IllegalStateException.class)
	public void testGetPickingPointSinPickingPointCreado() {
		PickingPointSystem pps = new PickingPointSystem();
		pps.getPickingPoint("0");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPickingPointIdNoValida() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();
		pps.addPickingPoint(kiosk);
		pps.addPickingPoint(locker);
		pps.getPickingPoint("a");

	}

	@Test
	public void testGetPickingPointOperativos() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, false);// no operativo
		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		pps.addPickingPoint(locker);
		assertEquals(1, pps.getPickingPointOperativos().length);
		assertFalse(
				Arrays.asList(pps.getPickingPointOperativos()).contains(kiosk));
		assertTrue(Arrays.asList(pps.getPickingPointOperativos())
				.contains(locker));
	}

	@Test(expected = IllegalStateException.class)
	public void testGetPickingPointOperativosPickingPointsNoCreados() {
		PickingPointSystem pps = new PickingPointSystem();
		pps.getPickingPointOperativos();
	}

	@Test
	public void testGetPickingPointFueraDeServicio() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, false);// no operativo
		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		pps.addPickingPoint(locker);
		assertEquals(1, pps.getPickingPointFueraDeServicio().length);
		assertFalse(
				Arrays.asList(pps.getPickingPointFueraDeServicio()).contains(kiosk));
		assertTrue(Arrays.asList(pps.getPickingPointFueraDeServicio())
				.contains(locker));
	}

	@Test(expected = IllegalStateException.class)
	public void testGetPickingPointFueraDeServicioPickingPointsNoCreados() {
		PickingPointSystem pps = new PickingPointSystem();
		pps.getPickingPointOperativos();
	}
	@Test
	public void testGetPickingPointEnZona() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPickingPointTaquillasVacias() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, false);// no operativo
		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		pps.addPickingPoint(locker);
		assertEquals(1, pps.getPickingPointTaquillasVacias().length);
		assertFalse(
				Arrays.asList(pps.getPickingPointTaquillasVacias()).contains(kiosk));
		assertTrue(Arrays.asList(pps.getPickingPointTaquillasVacias())
				.contains(locker));
	}

	@Test(expected = IllegalStateException.class)
	public void testGetPickingPointTaquillasVaciasPickingPointsNoCreados() {
		PickingPointSystem pps = new PickingPointSystem();
		pps.getPickingPointTaquillasVacias();
	}

	@Test
	public void testGetPickingPointTaquillasVaciasOperativas() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPickingPointEnZonaValidas() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminarPickingPoint() {
		fail("Not yet implemented");
	}

}
