package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.GunProvider;

import java.util.Collection;
import java.util.List;

public interface GunProviderService {
    List<GunProvider> getAllGunProviders();

    GunProvider addGunProvider(GunProvider gunProvider);

    void deleteGunProvider(Long id);

    GunProvider updateGunProvider(GunProvider gunProvider);

    GunProvider getGunProviderById(Long id);

    List<GunProvider> getGunProvidersSortedByName();

    List<GunProvider> filterBySpecialityAndReputationGreater(String speciality, int reputation);

    List<GunProvider> getGunProvidersFilteredByReputation(int reputation);

    GunProvider addGunToProvider(Long providerId, Long gunId);

    GunProvider removeGunFromProvider(Long providerId, Long gunId);
}
