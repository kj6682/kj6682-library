package org.kj6682.library.service;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kj6682.library.DevTestConfig;
import org.kj6682.library.bean.Item;
import org.kj6682.library.bean.Item.Status;
import org.kj6682.library.dao.ItemDao;
import org.kj6682.library.service.ItemServiceImpl;
import org.kj6682.library.service.ReservationServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = { DevTestConfig.class })
@ActiveProfiles("dev")
public class ReservationServiceTest {

	ReservationServiceImpl impl;

	private @Mock ItemDao itemDao;
    
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		 
		impl = new ReservationServiceImpl();
        impl.setDao(itemDao);
	}

	@Test
	public void useless(){
		Assert.assertNotNull(impl.getDao());
	}
	
	
	
	@Test
	public void reserve(){
        long id = 708l;
        String user = "Archimede";
        Date startDate = new Date(-1);
		
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.AVAILABLE)).when(itemDao).findById(anyLong());
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.RESERVED)).when(itemDao).update(anyLong(), eq(Status.RESERVED));
		
				
		try {
			
			Item result = impl.makeReservation(id, user, startDate);
		
			Assert.assertNotNull(result);
			Assert.assertEquals(Status.RESERVED, result.getStatus());
			
		} catch (Exception e) {
			Assert.fail();
		}

	}

	@Test
	public void reserveNotExistingItem(){
        long id = 708l;
        String user = "Archimede";
        Date startDate = new Date(-1);
		
		doReturn(null).when(itemDao).findById(anyLong());
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.RESERVED)).when(itemDao).update(anyLong(), eq(Status.RESERVED));
		
				
		try {
			
			Item result = impl.makeReservation(id, user, startDate);
		
			Assert.fail();
	
		} catch (Exception e) {
			
		}

	}
	
	@Test
	public void cancel(){
        long id = 708l;
        String user = "Archimede";
        Date startDate = new Date(-1);
		
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.RESERVED)).when(itemDao).findById(anyLong());
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.AVAILABLE)).when(itemDao).update(anyLong(), eq(Status.AVAILABLE));
		
				
		try {
			
			Item result = impl.cancelReservation(id, user, startDate);
		
			Assert.assertNotNull(result);
			Assert.assertEquals(Status.AVAILABLE, result.getStatus());
			
		} catch (Exception e) {
			Assert.fail();
		}

	}

	@Test
	public void cancelNotExistingItem(){
        long id = 708l;
        String user = "Archimede";
        Date startDate = new Date(-1);
		
		doReturn(null).when(itemDao).findById(anyLong());
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.RESERVED)).when(itemDao).update(anyLong(), eq(Status.RESERVED));
		
				
		try {
			
			Item result = impl.cancelReservation(id, user, startDate);
		
			Assert.fail();
	
		} catch (Exception e) {
			
		}

	}
		
	
}// :)
