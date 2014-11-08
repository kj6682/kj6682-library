package org.kj6682.library.store.bean;

/**
 * The Item is the unit of the STORE.
 * The item is attached to the CATALOG via the catalogID and 
 * has a status to indicate its availability 
 * 
 * @author luigi
 *
 */
public class Item {

	private final long id;
	private long catalogId;
    private String title;
    private Status status;
    
    public Item(long id, Status status) {
		super();
		this.id = id;
		this.status = status;
    }
    
	public long getId() {
		return id;
	}
	
	public long getCatalogId() {
		return catalogId;
	}
	
	public String getTitle() {
		return title;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	

}