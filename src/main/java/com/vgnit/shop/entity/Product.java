package com.vgnit.shop.entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Double price;
	private String description;
	private String stock;
	private String color;
	private String imageName;
	private String imageName2;
	private String imageName3;
	private String imageName4;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category;
	
	// ZO-RAJANI
	private Double discountedPrice;
	private Double discountPersent;
	private Double saveAmount; // This refers to the Discount Amount after providing some percentage of discount on any selected products !! 

	private String brand;
	
	@Embedded
	@ElementCollection
	private Set<Size> sizes = new HashSet<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Rating> rating = new ArrayList<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> review = new ArrayList<>();
	
	private int numberRatings;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public String getImageName2() {
		return imageName2;
	}

	public void setImageName2(String imageName2) {
		this.imageName2 = imageName2;
	}

	public String getImageName3() {
		return imageName3;
	}

	public void setImageName3(String imageName3) {
		this.imageName3 = imageName3;
	}

	public String getImageName4() {
		return imageName4;
	}

	public void setImageName4(String imageName4) {
		this.imageName4 = imageName4;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public Double getDiscountPersent() {
		return discountPersent;
	}

	public void setDiscountPersent(Double discountPersent) {
		this.discountPersent = discountPersent;
	}
	
	public Double getSaveAmount() {
		return saveAmount;
	}

	public void setSaveAmount(Double saveAmount) {
		this.saveAmount = saveAmount;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Set<Size> getSizes() {
		return sizes;
	}

	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}

	public List<Rating> getRating() {
		return rating;
	}

	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public int getNumberRatings() {
		return numberRatings;
	}

	public void setNumberRatings(int numberRatings) {
		this.numberRatings = numberRatings;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, String name, Double price, String description, String stock, String color, String imageName,
			String imageName2, String imageName3, String imageName4, Category category, Double discountedPrice, 
			Double discountPersent, Double saveAmount, String brand, Set<Size> sizes, List<Rating> rating,
			List<Review> review, int numberRatings) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stock = stock;
		this.color = color;
		this.imageName = imageName;
		this.imageName2 = imageName2;
		this.imageName3 = imageName3;
		this.imageName4 = imageName4;
		this.category = category;
		this.discountedPrice = discountedPrice;
		this.discountPersent = discountPersent;
		this.saveAmount = saveAmount;
		this.brand = brand;
		this.sizes = sizes;
		this.rating = rating;
		this.review = review;
		this.numberRatings = numberRatings;
	}
	
}
