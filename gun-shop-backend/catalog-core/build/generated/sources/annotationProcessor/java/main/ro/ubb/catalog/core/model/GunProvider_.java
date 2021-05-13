package ro.ubb.catalog.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GunProvider.class)
public abstract class GunProvider_ extends ro.ubb.catalog.core.model.BaseEntity_ {

	public static volatile SingularAttribute<GunProvider, String> speciality;
	public static volatile ListAttribute<GunProvider, GunType> gunTypes;
	public static volatile SingularAttribute<GunProvider, String> name;
	public static volatile SingularAttribute<GunProvider, Integer> reputation;

	public static final String SPECIALITY = "speciality";
	public static final String GUN_TYPES = "gunTypes";
	public static final String NAME = "name";
	public static final String REPUTATION = "reputation";

}

