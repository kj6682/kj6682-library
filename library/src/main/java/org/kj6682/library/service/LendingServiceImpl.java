package org.kj6682.library.service;

import java.sql.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.kj6682.library.bean.Item;
import org.kj6682.library.bean.Lending;
import org.kj6682.library.bean.Lending.Status;
import org.kj6682.library.dao.ItemDao;
import org.kj6682.library.dao.LendingDao;
import org.springframework.beans.factory.annotation.Autowired;

import static org.kj6682.library.bean.Item.Status.*;

public class LendingServiceImpl implements LendingService{

	
	LendingDao lendingDao;
	ItemDao itemDao;
	
	
	@Override
	public Lending grant(Long item, Long user, Date startDate) {
		Lending lending = lendingDao.findByItemAndUser(item, user);
		if(lending != null) throw new RuntimeException("lending.does.already.exist");
		Date to = new Date((new DateTime(startDate)).plusDays(30).getMillis());
		Long id = lendingDao.create(item, user, startDate, to, Status.ACTIVE ); 
		Lending result = new Lending(id, item, user, startDate, to, Status.ACTIVE);
		return result;
	}

	@Override
	public Lending extendLending(Long id) {
		Lending lending = lendingDao.findById(id);
		if(lending == null) throw new RuntimeException("lending.does.not.exist");
		return lendingDao.update(id, lending.getItemId(), lending.getUserId(), lending.getFrom(), new Date((new DateTime(lending.getTo())).plusDays(30).getMillis()), Status.EXTENDED );
		
	}

	@Override
	public Lending closeLending(Long id) {
		Lending lending = lendingDao.findById(id);
		if(lending == null) throw new RuntimeException("lending.does.not.exist");
		return lendingDao.update(id, lending.getItemId(), lending.getUserId(), lending.getFrom(), new Date((new DateTime()).getMillis()), Status.DONE );
	}

	@Override
	public List<Lending> listLendingsByUser(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	public LendingDao getLendingDao() {
		return lendingDao;
	}

	public void setLendingDao(LendingDao lendingDao) {
		this.lendingDao = lendingDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

}
