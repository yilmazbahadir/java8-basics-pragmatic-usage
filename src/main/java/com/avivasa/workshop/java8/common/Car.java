package com.avivasa.workshop.java8.common;

import java.math.BigDecimal;
import java.util.Map;

public class Car {
	private String brandName;
	private String modelName;
	private int modelYear;
	private Oil oilType;
	private double oilConsumptionPer100Km;
	private BigDecimal price;
	private Map<String, String> owners;

	public Car(String brandName, String modelName, int modelYear, Oil oilType, double oilConsumptionPer100Km,
			BigDecimal price, Map<String, String> owners) {
		super();
		this.brandName = brandName;
		this.modelName = modelName;
		this.modelYear = modelYear;
		this.oilType = oilType;
		this.oilConsumptionPer100Km = oilConsumptionPer100Km;
		this.price = price;
		this.owners = owners;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public Oil getOilType() {
		return oilType;
	}

	public void setOilType(Oil oilType) {
		this.oilType = oilType;
	}

	public double getOilConsumptionPer100Km() {
		return oilConsumptionPer100Km;
	}

	public void setOilConsumptionPer100Km(double oilConsumptionPer100Km) {
		this.oilConsumptionPer100Km = oilConsumptionPer100Km;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Map<String, String> getOwners() {
		return owners;
	}

	public void setOwners(Map<String, String> owners) {
		this.owners = owners;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brandName == null) ? 0 : brandName.hashCode());
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (brandName == null) {
			if (other.brandName != null)
				return false;
		} else if (!brandName.equals(other.brandName))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [brandName=" + brandName + ", modelName=" + modelName + ", modelYear=" + modelYear + ", oilType="
				+ oilType + ", oilConsumptionPer100Km=" + oilConsumptionPer100Km + ", price=" + price + ", owners=" + owners + "]";
	}

}
