package vn.com.vuong.dao;

import java.util.List;

import vn.com.vuong.entity.Complex;

public interface ComplexDAO {
	List<Complex> search(String name);
	void save(Complex complex);
	void update(Complex complex);
	void delete(String id);
}
