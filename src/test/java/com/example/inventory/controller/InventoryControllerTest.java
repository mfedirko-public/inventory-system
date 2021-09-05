package com.example.inventory.controller;

import com.example.inventory.model.InventoryDTO;
import com.example.inventory.service.InventorySearchService;
import com.example.inventory.service.InventoryRepositoryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

@WebFluxTest
@WithMockUser
class InventoryControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private InventoryRepositoryService service;
    @MockBean
    private InventorySearchService searchService;

    private InventoryDTO inv;

    @BeforeEach
    void setup() {
        client = client.mutateWith(csrf());

        inv = new InventoryDTO();
        inv.setName("abc");
        inv.setPrice(BigDecimal.valueOf(123.12));
        inv.setQuantity(1);
        inv.setUuid("abc-123-456");
    }

    @Test
    void findAll() {
        List<InventoryDTO> expected = Arrays.asList(inv, inv, inv, inv);
        Mockito.when(service.findAll())
                .thenReturn(Flux.fromIterable(expected));

        client.get().uri("/inventory")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.length()", expected.size());
    }

    @Test
    void findById() {
        Mockito.when(service.findById("abc-123"))
                .thenReturn(Mono.just(inv));

        client.get().uri("/inventory/abc-123")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(InventoryDTO.class)
                .value(res -> {
                    Assertions.assertThat(inv)
                            .isEqualTo(res);
                });
    }

    @Test
    void findById_noData() {
        Mockito.when(service.findById("abc-123"))
                .thenReturn(Mono.empty());

        client.get().uri("/inventory/abc-123")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody().isEmpty();
    }

    @Test
    void findByName() {
        Mockito.when(searchService.findByNameFuzzy("name abc"))
                .thenReturn(Flux.just(inv));

        client.get().uri("/inventory?name=" + URLEncoder.encode("name abc", StandardCharsets.UTF_8))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(InventoryDTO.class)
                .value(res -> {
                    assertEquals(1, res.size());
                    Assertions.assertThat(inv)
                            .isEqualTo(res.get(0));
                });
    }

    @Test
    void createInventory() {
        Mockito.when(service.createInventory(Mockito.any(InventoryDTO.class)))
                .thenReturn(Mono.just(inv));

        client.post().uri("/inventory")
                .bodyValue(inv)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(InventoryDTO.class)
                .value(res -> {
                    Assertions.assertThat(inv)
                            .isEqualTo(res);
                });
    }

    @Test
    void deleteInventory() {
        Mockito.when(service.deleteInventory("abc-1234"))
                .thenReturn(Mono.just(1));

        client.delete().uri("/inventory/abc-1234")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.status").isEqualTo("SUCCESS");
    }

    @Test
    void deleteInventory_notFound() {
        Mockito.when(service.deleteInventory("abc-1234"))
                .thenReturn(Mono.just(0));

        client.delete().uri("/inventory/abc-1234")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.status").isEqualTo("NO_DATA");
    }

    @Test
    void updateInventory() {
        Mockito.when(service.updateInventory(Mockito.any(InventoryDTO.class)))
                .thenReturn(Mono.just(inv));

        client.put().uri("/inventory")
                .bodyValue(inv)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(InventoryDTO.class)
                .value(res -> {
                    Assertions.assertThat(inv)
                            .isEqualTo(res);
                });
    }
}