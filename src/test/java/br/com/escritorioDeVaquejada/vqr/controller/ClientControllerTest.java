package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.model.Address;
import br.com.escritorioDeVaquejada.vqr.service.ClientService;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientRequestVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientResponseVO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {
    private static ClientResponseVO clientResponse;
    private static ClientRequestVO clientRequest;
    @Mock
    private BindingResult bindingResult;
    @Mock
    ClientService clientService;
    @InjectMocks
    ClientController clientController;

    @BeforeAll
    static void setupForAllTests(){
        clientRequest = new ClientRequestVO();
        clientResponse = new ClientResponseVO();
    }

    @BeforeEach
    void setupForEachTest() {
        clientRequest.setName("NameTest");
        clientRequest.setAddress(new Address("StateTest", "CityTest"));
        clientRequest.setEmail("teste@gmail.com");
        clientRequest.setNumber("99999999999");

        clientResponse.setId(UUID.randomUUID());
        clientResponse.setName(clientRequest.getName());
        clientResponse.setAddress(clientRequest.getAddress());
        clientResponse.setEmail(clientRequest.getEmail());
        clientResponse.setNumber(clientRequest.getNumber());
    }

    @Test
    @DisplayName("Should successfully save a client to the database and return the saved client with HTTP status code 201")
    void saveClientWithAllCorrectData() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(clientService.saveClient(clientRequest)).thenReturn(clientResponse);

        ResponseEntity<ClientResponseVO> result = clientController.saveClient(clientRequest, bindingResult);

        verify(clientService, times(1)).saveClient(clientRequest);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isEqualTo(clientResponse);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the name field is null")
    void saveClientWithOnlyIncorrectNameAttribute() {
        clientRequest.setName(null);

        when(bindingResult.hasErrors()).thenReturn(true);
        assertThatThrownBy(() -> clientController.saveClient(clientRequest, bindingResult))
                .isInstanceOf(BadRequestException.class);
        verify(clientService, Mockito.never()).saveClient(clientRequest);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the address field is null")
    void saveClientWithOnlyIncorrectAddressData() {
        clientRequest.setAddress(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> clientController.saveClient(clientRequest, bindingResult))
                .isInstanceOf(BadRequestException.class);
        verify(clientService, Mockito.never()).saveClient(clientRequest);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the city field of the address attribute is null")
    void saveClientWithOnlyCityAttributeFromIncorrectAddressAttribute() {
        clientRequest.getAddress().setCity(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> clientController.saveClient(clientRequest, bindingResult))
                .isInstanceOf(BadRequestException.class);
        verify(clientService, Mockito.never()).saveClient(clientRequest);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the state field of the address attribute is null")
    void saveClientWithOnlyStateAttributeFromIncorrectAddressAttribute() {
        clientRequest.getAddress().setState(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> clientController.saveClient(clientRequest, bindingResult))
                .isInstanceOf(BadRequestException.class);
        verify(clientService, Mockito.never()).saveClient(clientRequest);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the number field is null")
    void saveClientWithOnlyIncorrectNumberAttribute() {
        clientRequest.setNumber(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> clientController.saveClient(clientRequest, bindingResult))
                .isInstanceOf(BadRequestException.class);
        verify(clientService, Mockito.never()).saveClient(clientRequest);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because all fields with validation are null")
    void saveClientWithAllIncorrectData() {
        clientRequest.setNumber(null);
        clientRequest.setName(null);
        clientRequest.setAddress(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> clientController.saveClient(clientRequest, bindingResult))
                .isInstanceOf(BadRequestException.class);
        verify(clientService, Mockito.never()).saveClient(clientRequest);
    }
}