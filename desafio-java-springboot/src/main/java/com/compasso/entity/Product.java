package com.compasso.entity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

	private @Id @GeneratedValue long id;
	private String name;
	private String description;
	private double price;

	
	public Product(){}

	public  Product(String name, String description, double price){
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public long getId(){ return this.id;}
	public String getName(){ return this.name;}
	public String getDescription(){ return this.description;}
	public double getPrice(){return this.price;}

	public void setId(long id){ this.id = id;}
	public void setName(String name){ this.name = name;}
	public void setDescription(String description){this.description = description;}
	public void setPrice(double price){this.price = price;}

	@Override 
	public boolean equals(Object o) {

		if (this == o)
		  return true;
		if (!(o instanceof Product))
		  return false;
		Product product = (Product) o;
		return Objects.equals(this.id, product.id) && Objects.equals(this.name, product.name)
			&& Objects.equals(this.description, product.description) && Objects.equals(this.price,product.price);
	  }
	
	  @Override
	  public int hashCode() {
		return Objects.hash(this.id, this.name, this.description, this.price);
	  }
	
	  @Override
	  public String toString() {
		return "Product{" + "id=" + this.id + ", name='" + this.name + '\'' + ", description='" + this.description + '\'' + ", price='" + this.price + '\'' + '}';
	  }

	  public  static   List<Product> search(List<Product> list, String min_price, String max_price,String query){

		 return list.stream()
				  .filter(c -> c.getPrice() >= Double.parseDouble(min_price) &&  c.getPrice() <= Double.parseDouble( max_price))
				  .collect(Collectors.toList());

	  }
}
