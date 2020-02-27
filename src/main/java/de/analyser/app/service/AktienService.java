package de.analyser.app.service;

import de.analyser.app.domain.Aktien;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Aktien}.
 */
public interface AktienService {

    /**
     * Save a aktien.
     *
     * @param aktien the entity to save.
     * @return the persisted entity.
     */
    Aktien save(Aktien aktien);

    /**
     * Get all the aktiens.
     *
     * @return the list of entities.
     */
    List<Aktien> findAll();

    /**
     * Get the "id" aktien.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Aktien> findOne(Long id);

    /**
     * Delete the "id" aktien.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
