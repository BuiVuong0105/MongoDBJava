package vn.com.vuong.main;

import java.util.ArrayList;
import java.util.List;

import vn.com.vuong.dao.ComplexDAO;
import vn.com.vuong.dao.impl.ComplexDAOImpl;
import vn.com.vuong.db.DBLoader;
import vn.com.vuong.entity.Building;
import vn.com.vuong.entity.Complex;

public class Application {
	
	static {
		DBLoader.init();
	}
	
	public static ComplexDAO complexDAO = new ComplexDAOImpl();

	public static void search() {
		System.out.println("Search Complex");
		List<Complex> listComplex = complexDAO.search(null);
		System.out.println("Size: " + listComplex.size());
		listComplex.forEach(complex -> {
			System.out.println("Complex Name: " + complex.getName());
			List<Building> listBuilding = complex.getBuildings();
			for (Building building : listBuilding) {
				System.out.println("Building Name: " + building.getName());
			}
		});
	}

	public static void save() {
		System.out.println("Save Complex");
		Complex complex = new Complex();
		List<Building> listBuilding = new ArrayList<>();
		complex.setName("C19");
		complex.setAddress("Ha Noi");
		complex.setRooms(5000);
		complex.setBuildings(listBuilding);
		Building building = new Building(null, "A3");
		listBuilding.add(building);
		complexDAO.save(complex);
	}
	
	public static void update () {
		List<Complex> listComplex = complexDAO.search(null);
		Complex complexUpdate = listComplex.get(listComplex.size() - 1);
		complexUpdate.setName("xxxxx");
		complexDAO.update(complexUpdate);
	}
	
	public static void delete () {
		List<Complex> listComplex = complexDAO.search(null);
		Complex complex = listComplex.get(listComplex.size() - 1);
		complexDAO.delete(complex.getId());
	}

	public static void main(String[] args) {
		search();
//		save();
//		update();
		delete();
		search();
	}
}
