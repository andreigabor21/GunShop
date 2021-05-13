package ro.ubb.catalog.core.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ extends ro.ubb.catalog.core.model.BaseEntity_ {

	public static volatile SingularAttribute<Client, Address> address;
	public static volatile SetAttribute<Client, Rental> rentalSet;
	public static volatile SingularAttribute<Client, String> name;
	public static volatile SingularAttribute<Client, LocalDate> dateOfBirth;

	public static final String ADDRESS = "address";
	public static final String RENTAL_SET = "rentalSet";
	public static final String NAME = "name";
	public static final String DATE_OF_BIRTH = "dateOfBirth";

}

