package de.analyser.app.web.rest;

import de.analyser.app.domain.Aktien;
import de.analyser.app.repository.AktienRepository;
import de.analyser.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link de.analyser.app.domain.Aktien}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AktienResource {

    private final Logger log = LoggerFactory.getLogger(AktienResource.class);

    private static final String ENTITY_NAME = "aktien";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AktienRepository aktienRepository;

    public AktienResource(AktienRepository aktienRepository) {
        this.aktienRepository = aktienRepository;
    }

    /**
     * {@code POST  /aktiens} : Create a new aktien.
     *
     * @param aktien the aktien to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new aktien, or with status {@code 400 (Bad Request)} if the aktien has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/aktiens")
    public ResponseEntity<Aktien> createAktien(@RequestBody Aktien aktien) throws URISyntaxException {
        log.debug("REST request to save Aktien : {}", aktien);
        if (aktien.getId() != null) {
            throw new BadRequestAlertException("A new aktien cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Aktien result = aktienRepository.save(aktien);
        return ResponseEntity.created(new URI("/api/aktiens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /aktiens} : Updates an existing aktien.
     *
     * @param aktien the aktien to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aktien,
     * or with status {@code 400 (Bad Request)} if the aktien is not valid,
     * or with status {@code 500 (Internal Server Error)} if the aktien couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/aktiens")
    public ResponseEntity<Aktien> updateAktien(@RequestBody Aktien aktien) throws URISyntaxException {
        log.debug("REST request to update Aktien : {}", aktien);
        if (aktien.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Aktien result = aktienRepository.save(aktien);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, aktien.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /aktiens} : get all the aktiens.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of aktiens in body.
     */
    @GetMapping("/aktiens")
    public ResponseEntity<List<Aktien>> getAllAktiens(Pageable pageable) {
        log.debug("REST request to get a page of Aktiens");
        Page<Aktien> page = aktienRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /aktiens/:id} : get the "id" aktien.
     *
     * @param id the id of the aktien to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the aktien, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/aktiens/{id}")
    public ResponseEntity<Aktien> getAktien(@PathVariable Long id) {
        log.debug("REST request to get Aktien : {}", id);
        Optional<Aktien> aktien = aktienRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(aktien);
    }

    /**
     * {@code DELETE  /aktiens/:id} : delete the "id" aktien.
     *
     * @param id the id of the aktien to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/aktiens/{id}")
    public ResponseEntity<Void> deleteAktien(@PathVariable Long id) {
        log.debug("REST request to delete Aktien : {}", id);
        aktienRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
