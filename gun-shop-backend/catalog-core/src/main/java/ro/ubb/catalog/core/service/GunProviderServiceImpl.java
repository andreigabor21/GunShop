package ro.ubb.catalog.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.GunProvider;
import ro.ubb.catalog.core.model.GunType;
import ro.ubb.catalog.core.model.exceptions.GunShopException;
import ro.ubb.catalog.core.model.validators.GunProviderValidator;
import ro.ubb.catalog.core.repository.GunProviderRepository;

import java.util.List;
import java.util.Objects;

@Service
public class GunProviderServiceImpl implements GunProviderService{

    public static final Logger logger = LoggerFactory.getLogger(GunProviderService.class);

    @Autowired
    private GunProviderValidator validator;

    @Autowired
    private GunProviderRepository gunProviderRepository;

    @Autowired
    private GunTypeService gunTypeService;

    @Override
    public List<GunProvider> getAllGunProviders() {
        logger.trace("getAllGunProviders - method entered");
        return gunProviderRepository.findAll();
    }

    @Override
    public GunProvider addGunProvider(GunProvider gunProvider) {
        logger.trace("addGunProvider - method entered; gunProvider = {}", gunProvider);
        validator.validate(gunProvider);
        GunProvider save = gunProviderRepository.save(gunProvider);
        logger.trace("addGunProvider - method finished; gunProvider = {}", gunProvider);
        return save;
    }

    @Override
    public void deleteGunProvider(Long id) {
        logger.trace("deleteGunProvider - method entered; gunProvider = {}", this.gunProviderRepository.findById(id));
        gunProviderRepository.deleteById(id);
        logger.trace("deleteGunProvider - method finished");
    }

    @Override
    @Transactional
    public GunProvider updateGunProvider(GunProvider gunProvider) {
        logger.trace("updateGunProvider - method entered; gunProvider = {}", gunProvider);
        validator.validate(gunProvider);
        GunProvider gunProviderUpdate = gunProviderRepository.findById(gunProvider.getId()).orElseThrow();
        gunProviderUpdate.setName(gunProvider.getName());
        gunProviderUpdate.setSpeciality(gunProvider.getSpeciality());
        gunProviderUpdate.setReputation(gunProvider.getReputation());
        logger.trace("updateGunProvider - method finished; gunProviderUpdate = {}", gunProviderUpdate);
        return gunProvider;
    }

    @Override
    public GunProvider getGunProviderById(Long id) {
        logger.trace("getGunProviderById - method entered={}", id);
        return gunProviderRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public GunProvider addGunToProvider(Long providerId, Long gunId) {
        GunProvider gunProvider = getGunProviderById(providerId);
        GunType gunType = gunTypeService.getGunTypeById(gunId);
        if(Objects.nonNull(gunType.getGunProvider())){
            throw new GunShopException("Provider already assigned");
        }
        gunProvider.addGunType(gunType);
        gunType.setGunProvider(gunProvider);
        return gunProvider;
    }

    @Override
    @Transactional
    public GunProvider removeGunFromProvider(Long providerId, Long gunId) {
        GunProvider gunProvider = getGunProviderById(providerId);
        GunType gunType = gunTypeService.getGunTypeById(gunId);
        gunProvider.removeGunType(gunType);
        return gunProvider;
    }

    @Override
    public List<GunProvider> getGunProvidersSortedByName() {
        logger.trace("getGunProvidersSortedByName - method entered");
        return gunProviderRepository.findByOrderByNameAsc();
    }

    @Override
    public List<GunProvider> filterBySpecialityAndReputationGreater(String speciality, int reputation) {
        logger.trace("filterBySpecialityAndReputationGreater - method entered with: {} and {}", speciality, reputation);
        return gunProviderRepository.findBySpecialityEqualsAndReputationGreaterThan(speciality, reputation);
    }

    @Override
    public List<GunProvider> getGunProvidersFilteredByReputation(int reputation) {
        logger.trace("getGunProvidersFilteredByReputation - method entered");
        return gunProviderRepository.findAllByReputation(reputation);
    }

}
