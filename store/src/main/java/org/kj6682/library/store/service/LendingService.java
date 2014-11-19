package org.kj6682.library.store.service;

import java.sql.Date;
import java.util.List;

import org.kj6682.library.store.bean.Lending;

public interface LendingService {

	public Lending grant(String item, String user, Date startDate);
	public Lending extend(Long id);
	public Lending terminate(Long id);
	public List<Lending> listLendingsByUser(String user);
	
}
