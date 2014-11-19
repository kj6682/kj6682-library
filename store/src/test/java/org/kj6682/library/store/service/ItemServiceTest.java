package org.kj6682.library.store.service;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kj6682.library.store.DevConfig;
import org.kj6682.library.store.bean.Item;
import org.kj6682.library.store.bean.Item.Status;
import org.kj6682.library.store.dao.ItemDao;
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
public class ItemServiceTest {

	ItemServiceImpl impl;

	private @Mock ItemDao itemDao;
    
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		 
		impl = new ItemServiceImpl();
        impl.setDao(itemDao);
	}

	@Test
	public void useless(){
		Assert.assertNotNull(impl.getDao());
	}
	
	@Test
	public void create() {
	  
	  long catalogId = 708l;
	  
      doReturn(new Item(Long.MAX_VALUE, catalogId, Status.AVAILABLE)).when(itemDao).create(catalogId);
	  
      Item result = impl.create(catalogId);
     
	  Assert.assertNotNull(result);
      Assert.assertEquals(catalogId, result.getCatalogId());
      
	}
	
	@Test
	public void delete(){
		long id = 708l;
		
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.AVAILABLE)).when(itemDao).findById(anyLong());
		
		doAnswer(new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}
		}).when(itemDao).delete(anyLong());
		
		try {
			
			impl.delete(id);
			
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void deleteNotExistingItem(){
		long id = 708l;
		
		doReturn(null).when(itemDao).findById(anyLong());
		
		doAnswer(new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}
		}).when(itemDao).delete(anyLong());
		
		try {
			
			impl.delete(id);
			
			Assert.fail();
	
		} catch (Exception e) {
			
		}
		
	}
	
	@Test
	public void reserve(){
        long id = 708l;
		
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.AVAILABLE)).when(itemDao).findById(anyLong());
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.RESERVED)).when(itemDao).update(anyLong(), eq(Status.RESERVED));
		
				
		try {
			
			Item result = impl.reserve(id);
			Assert.assertNotNull(result);
			Assert.assertEquals(Status.RESERVED, result.getStatus());
			
		} catch (Exception e) {
			Assert.fail();
		}

	}

	@Test
	public void reserveNotExistingItem(){
        long id = 708l;
		
		doReturn(null).when(itemDao).findById(anyLong());
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.RESERVED)).when(itemDao).update(anyLong(), eq(Status.RESERVED));
		
				
		try {
			
			Item result = impl.reserve(id);
			Assert.fail();
		} catch (Exception e) {
			
		}

	}
	
	@Test
	public void release(){
        long id = 708l;
		
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.LENT)).when(itemDao).findById(anyLong());
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.AVAILABLE)).when(itemDao).update(anyLong(), eq(Status.AVAILABLE));
		
				
		try {
			
			Item result = impl.release(id);
			Assert.assertNotNull(result);
			Assert.assertEquals(Status.AVAILABLE, result.getStatus());
			
		} catch (Exception e) {
			Assert.fail();
		}

	}

	@Test
	public void releaseNotExistingItem(){
        long id = 708l;
		
		doReturn(null).when(itemDao).findById(anyLong());
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.AVAILABLE)).when(itemDao).update(anyLong(), eq(Status.AVAILABLE));
		
				
		try {
			
			Item result = impl.release(id);
			Assert.fail();
		} catch (Exception e) {
			
		}

	}

	@Test
	public void extend(){
        long id = 708l;
		
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.LENT)).when(itemDao).findById(anyLong());
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.LENT)).when(itemDao).update(anyLong(), eq(Status.LENT));
		
				
		try {
			
			Item result = impl.extend(id);
			Assert.assertNotNull(result);
			Assert.assertEquals(Status.LENT, result.getStatus());
			
		} catch (Exception e) {
			Assert.fail();
		}

	}

	@Test
	public void extendNotExistingItem(){
        long id = 708l;
		
		doReturn(null).when(itemDao).findById(anyLong());
		doReturn(new Item(Long.MAX_VALUE, Long.MAX_VALUE, Status.LENT)).when(itemDao).update(anyLong(), eq(Status.LENT));
		
				
		try {
			
			Item result = impl.extend(id);
			Assert.fail();
		} catch (Exception e) {
			
		}

	}

	@Test
	public void listAll(){
		
		List<Item> listOfItems = new LinkedList<Item>();
		listOfItems.add(new Item(1l, Long.MAX_VALUE, Status.LENT));
		listOfItems.add(new Item(2l, Long.MAX_VALUE, Status.AVAILABLE));
		listOfItems.add(new Item(3l, Long.MAX_VALUE, Status.RESERVED));
		listOfItems.add(new Item(4l, Long.MAX_VALUE, Status.LENT));
		listOfItems.add(new Item(5l, Long.MAX_VALUE, Status.ILLEGAL_OR_CURRUPTED));
		
		doReturn(listOfItems).when(itemDao).listAll();
		
		List<Item>  result = impl.listAll();
		
		Assert.assertNotNull(result);
		Assert.assertEquals(5, result.size());
		
	}
	
	@Test
	public void listByCatalog(){
		
		List<Item> listOfItems = new LinkedList<Item>();
		listOfItems.add(new Item(1l, Long.MAX_VALUE, Status.LENT));
		listOfItems.add(new Item(2l, Long.MAX_VALUE, Status.AVAILABLE));
		listOfItems.add(new Item(3l, Long.MAX_VALUE, Status.RESERVED));
		listOfItems.add(new Item(4l, Long.MAX_VALUE, Status.LENT));
		listOfItems.add(new Item(5l, Long.MAX_VALUE, Status.ILLEGAL_OR_CURRUPTED));
		
		
		doReturn(listOfItems).when(itemDao).listByCatalog(anyLong());
		
		List<Item>  result = impl.listByCatalog(2l);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(5, result.size());
		
	}
}// :)
