package ro.ubb.catalog.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GunType.class)
public abstract class GunType_ extends ro.ubb.catalog.core.model.BaseEntity_ {

	public static volatile SetAttribute<GunType, Rental> rentalSet;
	public static volatile SingularAttribute<GunType, String> name;
	public static volatile SingularAttribute<GunType, GunProvider> gunProvider;
	public static volatile SingularAttribute<GunType, Category> category;

	public static final String RENTAL_SET = "rentalSet";
	public static final String NAME = "name";
	public static final String GUN_PROVIDER = "gunProvider";
	public static final String CATEGORY = "category";

}

