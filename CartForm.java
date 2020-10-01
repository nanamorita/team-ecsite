package jp.co.internous.grapes.model.form;

import java.io.Serializable;

public class CartForm implements Serializable {
	
	private static final long serialVersionUID = 4854313184990105046L;
	
	private int userId;
	private int productId;
	private int productCount;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
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
	
}