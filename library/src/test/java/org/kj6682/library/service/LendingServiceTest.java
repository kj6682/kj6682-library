package org.kj6682.library.service;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kj6682.library.DevConfig;
import org.kj6682.library.bean.Item;
import org.kj6682.library.bean.Lending;
import org.kj6682.library.bean.Item.Status;
import org.kj6682.library.dao.ItemDao;
import org.kj6682.library.dao.LendingDao;
import org.kj6682.library.service.ItemServiceImpl;
import org.kj6682.library.service.LendingServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = { DevConfig.class })
@ActiveProfiles("dev")
public class LendingServiceTest {

	LendingServiceImpl impl;

	private @Mock LendingDao lendingDao;
	private @Mock ItemDao itemDao;
	private Date today_minus30;
	private Date today;
	private Date today_plus30;
	Lending oldLending;
	Lending newLending;
	Lending extendedLending;
	Lending deletedLending;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);

		impl = new LendingServiceImpl();
		impl.setLendingDao(lendingDao);
		impl.setItemDao(itemDao);

		today_minus30 = new Date(DateTime.now().minusDays(30).getMillis());
		today = new Date(DateTime.now().getMillis());
		today_plus30 = new Date(DateTime.now().plusDays(30).getMillis());
		oldLending = new Lending(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE, today_minus30, today,
				org.kj6682.library.bean.Lending.Status.ACTIVE);
		newLending = new Lending(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE, today, today_plus30,
				org.kj6682.library.bean.Lending.Status.ACTIVE);
		
		extendedLending = new Lending(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE, today_minus30, today_plus30,
				org.kj6682.library.bean.Lending.Status.EXTENDED);
		deletedLending = new Lending(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE, today_minus30, today,
				org.kj6682.library.bean.Lending.Status.DONE);

	}

	@Test
	public void useless() {
		Assert.assertNotNull(impl.getItemDao());
		Assert.assertNotNull(impl.getLendingDao());
	}

	@Test
	public void grant() {
	
		doReturn(null).when(lendingDao).findById(anyLong());
		doReturn(newLending).when(lendingDao).update(anyLong(), anyLong(), anyLong(), eq(today), eq(today_plus30),
				eq(org.kj6682.library.bean.Lending.Status.ACTIVE));

		try {

			Lending result = impl.grant(Long.MAX_VALUE, Long.MAX_VALUE, today);

			Assert.assertNotNull(result);
			Assert.assertEquals(org.kj6682.library.bean.Lending.Status.ACTIVE, result.getStatus());
			Assert.assertEquals(today, result.getFrom());
			Assert.assertEquals(today_plus30, result.getTo());

			Interval interval = new Interval(new DateTime(result.getFrom()), new DateTime(result.getTo()));
			Assert.assertTrue(interval.toDuration().getStandardDays() > 20);
		} catch (Exception e) {
			Assert.fail();
		}

	}

	@Test
	public void grant_already_existing() {
	
		doReturn(newLending).when(lendingDao).findByItemAndUser(anyLong(), anyLong());
		doReturn(newLending).when(lendingDao).update(anyLong(), anyLong(), anyLong(), eq(today), eq(today_plus30),
				eq(org.kj6682.library.bean.Lending.Status.ACTIVE));

		try {

			Lending result = impl.grant(Long.MAX_VALUE, Long.MAX_VALUE, today);
			Assert.fail();
			
				} catch (Exception e) {
		}

	}
	
	@Test
	public void extend() {
		long id = 708l;

		doReturn(oldLending).when(lendingDao).findById(anyLong());
		doReturn(extendedLending).when(lendingDao).update(anyLong(), anyLong(), anyLong(), eq(today_minus30), eq(today_plus30),
				eq(org.kj6682.library.bean.Lending.Status.EXTENDED));

		try {

			Lending result = impl.extendLending(id);

			Assert.assertNotNull(result);
			Assert.assertEquals(org.kj6682.library.bean.Lending.Status.EXTENDED, result.getStatus());
			Assert.assertEquals(today_minus30, result.getFrom());
			Assert.assertEquals(today_plus30, result.getTo());

			Interval interval = new Interval(new DateTime(result.getFrom()), new DateTime(result.getTo()));
			Assert.assertTrue(interval.toDuration().getStandardDays() > 50);
		} catch (Exception e) {
			Assert.fail();
		}

	}

	@Test
	public void extendNotExistingLending() {
		long id = 708l;

		doReturn(null).when(lendingDao).findById(anyLong());
		doReturn(extendedLending).when(lendingDao).update(anyLong(), anyLong(), anyLong(), eq(today_minus30), eq(today_plus30),
				eq(org.kj6682.library.bean.Lending.Status.EXTENDED));

		try {

			impl.extendLending(id);
			Assert.fail();

		} catch (Exception e) {
		}

	}

	@Test
	public void delete() {
		long id = 708l;

		doReturn(oldLending).when(lendingDao).findById(anyLong());
		doReturn(deletedLending).when(lendingDao).update(anyLong(), anyLong(), anyLong(), eq(today_minus30), any(Date.class),
				eq(org.kj6682.library.bean.Lending.Status.DONE));

		try {

			Lending result = impl.closeLending(id);

			Assert.assertNotNull(result);
			Assert.assertEquals(org.kj6682.library.bean.Lending.Status.DONE, result.getStatus());
			Assert.assertEquals(today_minus30, result.getFrom());
			Assert.assertEquals(today, result.getTo());

		} catch (Exception e) {
			Assert.fail();
		}

	}

		
}// :)
