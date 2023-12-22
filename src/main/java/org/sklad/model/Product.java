package org.sklad.model;

import java.awt.*;
import java.io.Serializable;

public class Product implements Serializable {

	public int id;
	public String name;
	public String description;
	public int availableAmount;
	public double pricePerPiece;
	public String imageUrl;
	public transient Image image;
	public boolean visible = false;
	private static int counter = 0;

	public Product(){
	}

	public Product(
			String name,
			String description,
			int availableAmount,
			double pricePerPiece,
			String imageUrl
	){
		this.id = provideId();
		this.name = name;
		this.description = description;
		this.availableAmount = availableAmount;
		this.pricePerPiece = pricePerPiece;
		this.imageUrl = imageUrl;
	}

	public Product(
			String name,
			String description,
			double pricePerPiece
	) {
		this.id = provideId();
		this.name = name;
		this.description = description;
		this.pricePerPiece = pricePerPiece;
	}

	public Product(Product product){
		this.id = product.id;
		this.name = product.name;
		this.description = product.description;
		this.pricePerPiece = product.pricePerPiece;
		this.imageUrl = product.imageUrl;
		this.image = product.image;
	}

	public Product(Product product, int availableAmount){
		this.id = product.id;
		this.name = product.name;
		this.description = product.description;
		this.pricePerPiece = product.pricePerPiece;
		this.imageUrl = product.imageUrl;
		this.availableAmount = availableAmount;
	}

	public double calculateTotalPrice(){
		return availableAmount * pricePerPiece;
	}

	private static int provideId(){
		return counter++;
	}

	@Override
	public String toString() {
		return name;
	}
}
