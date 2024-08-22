package br.com.escritorioDeVaquejada.vqr.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EventRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void findAllByOwnerOrderByDateTime() {
    }
}