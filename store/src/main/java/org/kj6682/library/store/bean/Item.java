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
	private final long catalogId;
    private final Status status;
    public enum Status {
    	
    	AVAILABLE, RESERVED, LENT, ILLEGAL_OR_CURRUPTED
    	
    }
    
	public Item(long id, long catalogId, Status status) {
		super();
		this.id = id;
		this.catalogId = catalogId;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public long getCatalogId() {
		return catalogId;
	}

	public Status getStatus() {
		return status;
	}

	

}