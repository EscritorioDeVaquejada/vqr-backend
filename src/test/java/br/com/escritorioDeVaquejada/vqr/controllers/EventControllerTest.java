package br.com.escritorioDeVaquejada.vqr.controllers;

import br.com.escritorioDeVaquejada.vqr.exceptions.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.exceptions.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.models.Address;
import br.com.escritorioDeVaquejada.vqr.services.EventServices;
import br.com.escritorioDeVaquejada.vqr.vo.EventVo;
import org.assertj.core.api.Assertions;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @Mock
    private EventServices eventServices;
    @Mock
    private BindingResult errorsInRequest;
    @InjectMocks
    private EventController eventController;
    private static EventVo eventVoMock;
    private static List<EventVo> eventListMock;
    private static UUID ownerId;

    @BeforeEach
    void setupForEachTest(){
        eventVoMock.setName("Evento Teste");
        eventVoMock.setStartPasswords(3);
        eventVoMock.setAddress(new Address("Estado teste","Cidade Teste"));
        eventVoMock.setDefaultTicketPrice(2000);
        eventVoMock.setDateTime(LocalDateTime.of(1998, 12, 12, 10, 50));
        eventVoMock.setPriceOfBoiTVAnticipated(3000);
        eventVoMock.setPriceOfBoiTvPurchasedOnDemand(1500);
    }

    @BeforeAll
    static void setupForAllTests(){
        ownerId = UUID.randomUUID();
        eventVoMock = new EventVo();
        eventListMock = List.of(eventVoMock);
    }


    @Test
    @DisplayName("Deve com sucesso salvar um evento, retornando-o junto com o HTTP status code 201")
    void saveEventCase01() {
        when(errorsInRequest.hasErrors()).thenReturn(false);
        when(eventServices.saveEvent(eventVoMock, ownerId)).thenReturn(eventVoMock);

        ResponseEntity<EventVo> result = eventController.saveEvent(eventVoMock, ownerId, errorsInRequest);

        assertThat(result.getBody()).isEqualTo(eventVoMock);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(eventServices, times(1)).saveEvent(eventVoMock, ownerId);
    }

    @Test
    @DisplayName("Não deve conseguir salvar o evento, devido ao proprietário não existir, retornando uma ResourceNotFoundException com HTTP status code 404")
    void saveEventCase02() {
        when(errorsInRequest.hasErrors()).thenReturn(false);
        when(eventServices.saveEvent(eventVoMock, ownerId)).thenThrow(new
                ResourceNotFoundException("Cliente não existe!"));

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, ownerId, errorsInRequest);
        }).isInstanceOf(ResourceNotFoundException.class);
        verify(eventServices, times(1)).saveEvent(eventVoMock, ownerId);
    }

    @Test
    @DisplayName("Não deve conseguir salvar o evento, devido a todos os campos validados estarem incorretos, retornando uma BadRequestException com HTTP status code 400")
    void saveEventCase03() {
        eventVoMock.setName(null);
        eventVoMock.setStartPasswords(-3);
        eventVoMock.setAddress(null);
        eventVoMock.setDefaultTicketPrice(-2000);
        eventVoMock.setDateTime(null);
        eventVoMock.setPriceOfBoiTVAnticipated(-3000);
        eventVoMock.setPriceOfBoiTvPurchasedOnDemand(-1500);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, ownerId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventServices, never()).saveEvent(eventVoMock, ownerId);
    }

    @Test
    @DisplayName("Não deve conseguir salvar o evento, devido apenas o campo Name está incorreto, retornando uma BadRequestException com HTTP status code 400")
    void saveEventCase04() {
        eventVoMock.setName(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, ownerId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventServices, never()).saveEvent(eventVoMock, ownerId);
    }

    @Test
    @DisplayName("Não deve conseguir salvar o evento, devido apenas o campo StartPasswords está incorreto, retornando uma BadRequestException com HTTP status code 400")
    void saveEventCase05() {
        eventVoMock.setStartPasswords(-3);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, ownerId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventServices, never()).saveEvent(eventVoMock, ownerId);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the address field is null")
    void saveEventWithOnlyIncorrectAddressData(){
        eventVoMock.setAddress(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, ownerId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventServices, never()).saveEvent(eventVoMock, ownerId);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the city field of the address attribute is null")
    void saveEventWithOnlyCityAttributeFromIncorrectAddressAttribute(){
        eventVoMock.getAddress().setCity(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, ownerId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventServices, never()).saveEvent(eventVoMock, ownerId);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the state field of the address attribute is null")
    void saveEventWithOnlyStateAttributeFromIncorrectAddressAttribute(){
        eventVoMock.getAddress().setState(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, ownerId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventServices, never()).saveEvent(eventVoMock, ownerId);
    }

    @Test
    @DisplayName("Não deve conseguir salvar o evento, devido apenas o campo DefaultTicketPrice está incorreto, retornando uma BadRequestException com HTTP status code 400")
    void saveEventCase09() {
        eventVoMock.setDefaultTicketPrice(-2000);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, ownerId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventServices, never()).saveEvent(eventVoMock, ownerId);
    }

    @Test
    @DisplayName("Não deve conseguir salvar o evento, devido apenas o campo PriceOfBoiTVAnticipated está incorreto, retornando uma BadRequestException com HTTP status code 400")
    void saveEventCase10() {
        eventVoMock.setPriceOfBoiTVAnticipated(-3000);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, ownerId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventServices, never()).saveEvent(eventVoMock, ownerId);
    }

    @Test
    @DisplayName("Não deve conseguir salvar o evento, devido apenas o campo PriceOfBoiTvPurchasedOnDemand está incorreto, retornando uma BadRequestException com HTTP status code 400")
    void saveEventCase11() {
        eventVoMock.setPriceOfBoiTvPurchasedOnDemand(-1500);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, ownerId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventServices, never()).saveEvent(eventVoMock, ownerId);
    }

    @Test
    @DisplayName("Deve com sucesso obter uma lista de eventos e retorná-la junto com o HTTP Status code 200")
    void findEventsByClientIdCase01() {
        when(eventServices.findEventsByClientId(ownerId)).thenReturn(eventListMock);

        ResponseEntity<List<EventVo>> result = eventController.findEventsByClientId(ownerId);

        assertThat(result.getBody()).isEqualTo(eventListMock);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(eventServices, times(1)).findEventsByClientId(ownerId);
    }

    @Test
    @DisplayName("Não deve conseguir obter uma lista de eventos, devido ao proprietário não existir, retornando um ResourceNotFoundException junto com o código HTTP 404")
    void findEventsByClientIdCase02(){
        when(eventServices.findEventsByClientId(ownerId)).thenThrow(new ResourceNotFoundException("Cliente não existe!"));

        assertThatThrownBy(() -> {
            eventController.findEventsByClientId(ownerId);
        }).isInstanceOf(ResourceNotFoundException.class);
        verify(eventServices, times(1)).findEventsByClientId(ownerId);
    }
}