package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.model.Address;
import br.com.escritorioDeVaquejada.vqr.service.ClientService;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;
import org.assertj.core.api.Assertions;
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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {
    private ClientVo newClient;
    @Mock
    private BindingResult bindingResult;
    @Mock
    ClientService clientService;
    @InjectMocks
    ClientController clientController;

    @BeforeEach
    void setup(){
        newClient = new ClientVo();

        newClient.setName("NameTest");
        newClient.setAddress(new Address("StateTest", "CityTest"));
        newClient.setEmail("teste@gmail.com");
        newClient.setNumber("99999999999");
    }

    @Test
    @DisplayName("Should successfully save a client to the database and return the saved client with HTTP status code 201")
    void saveClientWithAllCorrectData(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(clientService.saveClient(newClient)).thenReturn(newClient);

        ResponseEntity<ClientVo> result = clientController.saveClient(newClient, bindingResult);

        Mockito.verify(clientService, times(1)).saveClient(newClient);
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(result.getBody()).isEqualTo(newClient);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the name field is null")
    void saveClientWithOnlyIncorrectNameAttribute() {
        newClient.setName(null);

        when(bindingResult.hasErrors()).thenReturn(true);
        Assertions.assertThatThrownBy(() -> clientController.saveClient(newClient, bindingResult))
                .isInstanceOf(BadRequestException.class);
        Mockito.verify(clientService, Mockito.never()).saveClient(newClient);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the address field is null")
    void saveClientWithOnlyIncorrectAddressData(){
        newClient.setAddress(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        Assertions.assertThatThrownBy(() -> clientController.saveClient(newClient, bindingResult))
                .isInstanceOf(BadRequestException.class);
        Mockito.verify(clientService, Mockito.never()).saveClient(newClient);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the city field of the address attribute is null")
    void saveClientWithOnlyCityAttributeFromIncorrectAddressAttribute(){
        newClient.getAddress().setCity(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        Assertions.assertThatThrownBy(() -> clientController.saveClient(newClient, bindingResult))
                .isInstanceOf(BadRequestException.class);
        Mockito.verify(clientService, Mockito.never()).saveClient(newClient);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the state field of the address attribute is null")
    void saveClientWithOnlyStateAttributeFromIncorrectAddressAttribute(){
        newClient.getAddress().setState(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        Assertions.assertThatThrownBy(() -> clientController.saveClient(newClient, bindingResult))
                .isInstanceOf(BadRequestException.class);
        Mockito.verify(clientService, Mockito.never()).saveClient(newClient);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the number field is null")
    void saveClientWithOnlyIncorrectNumberAttribute(){
        newClient.setNumber(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        Assertions.assertThatThrownBy(() -> clientController.saveClient(newClient, bindingResult))
                .isInstanceOf(BadRequestException.class);
        Mockito.verify(clientService, Mockito.never()).saveClient(newClient);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because all fields with validation are null")
    void saveClientWithAllIncorrectData(){
        newClient.setNumber(null);
        newClient.setName(null);
        newClient.setAddress(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        Assertions.assertThatThrownBy(() -> clientController.saveClient(newClient, bindingResult))
                .isInstanceOf(BadRequestException.class);
        Mockito.verify(clientService, Mockito.never()).saveClient(newClient);
    }
}