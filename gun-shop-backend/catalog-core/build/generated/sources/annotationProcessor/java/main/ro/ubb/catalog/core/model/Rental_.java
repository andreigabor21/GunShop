package ro.ubb.catalog.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rental.class)
public abstract class Rental_ {

	public static volatile SingularAttribute<Rental, GunType> gunType;
	public static volatile SingularAttribute<Rental, Integer> price;
	public static volatile SingularAttribute<Rental, Client> client;

	public static final String GUN_TYPE = "gunType";
	public static final String PRICE = "price";
	public static final String CLIENT = "client";

}

