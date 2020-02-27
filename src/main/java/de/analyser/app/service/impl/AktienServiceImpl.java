package de.analyser.app.service.impl;

import de.analyser.app.service.AktienService;
import de.analyser.app.domain.Aktien;
import de.analyser.app.repository.AktienRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Aktien}.
 */
@Service
@Transactional
public class AktienServiceImpl implements AktienService {

    private final Logger log = LoggerFactory.getLogger(AktienServiceImpl.class);

    private final AktienRepository aktienRepository;

    public AktienServiceImpl(AktienRepository aktienRepository) {
        this.aktienRepository = aktienRepository;
    }

    /**
     * Save a aktien.
     *
     * @param aktien the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Aktien save(Aktien aktien) {
        log.debug("Request to save Aktien : {}", aktien);
        return aktienRepository.save(aktien);
    }

    /**
     * Get all the aktiens.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Aktien> findAll() {
        log.debug("Request to get all Aktiens");
        return aktienRepository.findAll();
    }

    /**
     * Get one aktien by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Aktien> findOne(Long id) {
        log.debug("Request to get Aktien : {}", id);
        return aktienRepository.findById(id);
    }

    /**
     * Delete the aktien by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Aktien : {}", id);
        aktienRepository.deleteById(id);
    }
}
