package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.model.Address;
import br.com.escritorioDeVaquejada.vqr.service.EventService;
import br.com.escritorioDeVaquejada.vqr.vo.EventVo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    private EventService eventService;
    @Mock
    private BindingResult errorsInRequest;
    @InjectMocks
    private EventController eventController;
    private static EventVo eventVoMock;
    private static List<EventVo> eventVoListMock;
    private static UUID clientId;

    @BeforeEach
    void setupForEachTest(){
        eventVoMock.setName("Evento Teste");
        eventVoMock.setNumberOfInitialTickets(3);
        eventVoMock.setAddress(new Address("Estado teste","Cidade Teste"));
        eventVoMock.setDefaultTicketPrice(2000);
        eventVoMock.setDateTime(LocalDateTime.of(1998, 12, 12, 10, 50));
        eventVoMock.setPriceOfBoiTVAnticipated(3000);
        eventVoMock.setPriceOfBoiTvPurchasedOnDemand(1500);
    }

    @BeforeAll
    static void setupForAllTests(){
        clientId = UUID.randomUUID();
        eventVoMock = new EventVo();
        eventVoListMock = List.of(eventVoMock);
    }

    @Test
    @DisplayName("Should successfully save an event and return it with HTTP status code 201")
    void shouldSaveEventSuccessfullyAndReturnWith201() {
        when(errorsInRequest.hasErrors()).thenReturn(false);
        when(eventService.saveEvent(eventVoMock, clientId)).thenReturn(eventVoMock);

        ResponseEntity<EventVo> result = eventController.saveEvent(eventVoMock, clientId, errorsInRequest);

        assertThat(result.getBody()).isEqualTo(eventVoMock);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(eventService, times(1)).saveEvent(eventVoMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to non-existent owner, returning ResourceNotFoundException with HTTP status code 404")
    void shouldThrowResourceNotFoundExceptionWhenOwnerDoesNotExistWhenSavingEvent() {
        when(errorsInRequest.hasErrors()).thenReturn(false);
        when(eventService.saveEvent(eventVoMock, clientId)).thenThrow(new
                ResourceNotFoundException("Client not found!"));

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, clientId, errorsInRequest);
        }).isInstanceOf(ResourceNotFoundException.class);
        verify(eventService, times(1)).saveEvent(eventVoMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to validation errors on all validated fields, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenThereAreValidationErrorsInAllValidatedFields() {
        eventVoMock.setName(null);
        eventVoMock.setNumberOfInitialTickets(-3);
        eventVoMock.setAddress(null);
        eventVoMock.setDefaultTicketPrice(-2000);
        eventVoMock.setDateTime(null);
        eventVoMock.setPriceOfBoiTVAnticipated(-3000);
        eventVoMock.setPriceOfBoiTvPurchasedOnDemand(-1500);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVoMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid Name field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenNameFieldIsInvalid() {
        eventVoMock.setName(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVoMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid NumberOfInitialTickets field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenNumberOfInitialTicketsFieldIsInvalid() {
        eventVoMock.setNumberOfInitialTickets(-3);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVoMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid address field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenAddressFieldIsNull() {
        eventVoMock.setAddress(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVoMock, clientId);
    }

    @Test
    @DisplayName("Should not save event due to invalid address city field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenCityFieldOfAddressIsNull() {
        eventVoMock.getAddress().setCity(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVoMock, clientId);
    }

    @Test
    @DisplayName("Should not save event due to invalid address state field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenStateFieldOfAddressIsNull() {
        eventVoMock.getAddress().setState(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVoMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid DefaultTicketPrice field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenDefaultTicketPriceFieldIsInvalid() {
        eventVoMock.setDefaultTicketPrice(-2000);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVoMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid PriceOfBoiTVAnticipated field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenPriceOfBoiTVAnticipatedFieldIsInvalid() {
        eventVoMock.setPriceOfBoiTVAnticipated(-3000);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVoMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid PriceOfBoiTvPurchasedOnDemand field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenPriceOfBoiTvPurchasedOnDemandFieldIsInvalid() {
        eventVoMock.setPriceOfBoiTvPurchasedOnDemand(-1500);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVoMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVoMock, clientId);
    }

    @Test
    @DisplayName("Should successfully retrieve a list of events and return it with HTTP Status code 200")
    void shouldFindEventsByClientIdAndReturnListWith200() {
        when(eventService.findEventsByClientId(clientId)).thenReturn(eventVoListMock);

        ResponseEntity<List<EventVo>> result = eventController.findEventsByClientId(clientId);

        assertThat(result.getBody()).isEqualTo(eventVoListMock);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(eventService, times(1)).findEventsByClientId(clientId);
    }

    @Test
    @DisplayName("Should not retrieve a list of events due to non-existent owner, returning ResourceNotFoundException with HTTP status code 404")
    void shouldThrowResourceNotFoundExceptionWhenOwnerDoesNotExistWhenSearchingForEvents() {
        when(eventService.findEventsByClientId(clientId)).thenThrow(new
                ResourceNotFoundException("Client not found!"));

        assertThatThrownBy(() -> {
            eventController.findEventsByClientId(clientId);
        }).isInstanceOf(ResourceNotFoundException.class);
        verify(eventService, times(1)).findEventsByClientId(clientId);
    }
}