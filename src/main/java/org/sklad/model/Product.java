package org.sklad.model;

public class Product {

	public int id;
	public String name;
	public String description;
	public int availableAmount;
	public double pricePerPiece;
	public String imageUrl;
	public boolean visibility = false;

	public Product(
			int id,
			String name,
			String description,
			int availableAmount,
			double pricePerPiece,
			String imageUrl
	){
		this.id = id;
		this.name = name;
		this.description = description;
		this.availableAmount = availableAmount;
		this.pricePerPiece = pricePerPiece;
		this.imageUrl = imageUrl;
	}

	public Product(Product product){
		this.id = product.id;
		this.name = product.name;
		this.description = product.description;
		this.pricePerPiece = product.pricePerPiece;
		this.imageUrl = product.imageUrl;
	}

	public double calculateTotalPrice(){
		return availableAmount * pricePerPiece;
	}
}
