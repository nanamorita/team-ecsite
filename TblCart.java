package jp.co.internous.grapes.model.domain;

import java.sql.Timestamp;

import jp.co.internous.grapes.model.form.CartForm;

public class TblCart {
	
	private int id;
	private int userId;
	private int productId;
	private int productCount;
	private Timestamp createdAt;
	private Timestamp updatedAp;
	
	public TblCart(CartForm form) {
		this.userId = form.getUserId();
		this.productId = form.getProductId();
		this.productCount = form.getProductCount();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getProductCount() {
		return productCount;
	}
	
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public Timestamp getUpdatedAp() {
		return updatedAp;
	}
	
	public void setUpdatedAp(Timestamp updatedAp) {
		this.updatedAp = updatedAp;
	}
	
}
