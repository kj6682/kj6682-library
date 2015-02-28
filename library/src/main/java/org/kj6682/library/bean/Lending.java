package org.kj6682.library.bean;

import java.sql.Date;

public class Lending {

	private final long id;
	private final long itemId;
    private final long userId;
    private Date from;
    private Date to;
    
    private Status status;
    
    public enum Status { ACTIVE, EXTENDED, DONE }

	public Lending(long id, long itemId, long userId, Date from, Date to, Status status) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.userId = userId;
		this.from = from;
		this.to = to;
		this.status = status;
	};
	
	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public long getItemId() {
		return itemId;
	}

	public long getUserId() {
		return userId;
	}
    
    
}//:)
