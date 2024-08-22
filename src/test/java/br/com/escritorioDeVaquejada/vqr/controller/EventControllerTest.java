package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.model.Address;
import br.com.escritorioDeVaquejada.vqr.service.EventService;
import br.com.escritorioDeVaquejada.vqr.vo.EventVO;
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
    private static EventVO eventVOMock;
    private static List<EventVO> eventVOListMock;
    private static UUID clientId;

    @BeforeEach
    void setupForEachTest(){
        eventVOMock.setName("Evento Teste");
        eventVOMock.setNumberOfInitialTickets(3);
        eventVOMock.setAddress(new Address("Estado teste","Cidade Teste"));
        eventVOMock.setDefaultTicketPrice(2000);
        eventVOMock.setDateTime(LocalDateTime.of(1998, 12, 12, 10, 50));
        eventVOMock.setPriceOfBoiTVAnticipated(3000);
        eventVOMock.setPriceOfBoiTVPurchasedOnDemand(1500);
    }

    @BeforeAll
    static void setupForAllTests(){
        clientId = UUID.randomUUID();
        eventVOMock = new EventVO();
        eventVOListMock = List.of(eventVOMock);
    }

    @Test
    @DisplayName("Should successfully save an event and return it with HTTP status code 201")
    void shouldSaveEventSuccessfullyAndReturnWith201() {
        when(errorsInRequest.hasErrors()).thenReturn(false);
        when(eventService.saveEvent(eventVOMock, clientId)).thenReturn(eventVOMock);

        ResponseEntity<EventVO> result = eventController.saveEvent(eventVOMock, clientId, errorsInRequest);

        assertThat(result.getBody()).isEqualTo(eventVOMock);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(eventService, times(1)).saveEvent(eventVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to non-existent owner, returning ResourceNotFoundException with HTTP status code 404")
    void shouldThrowResourceNotFoundExceptionWhenOwnerDoesNotExistWhenSavingEvent() {
        when(errorsInRequest.hasErrors()).thenReturn(false);
        when(eventService.saveEvent(eventVOMock, clientId)).thenThrow(new
                ResourceNotFoundException("Client not found!"));

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVOMock, clientId, errorsInRequest);
        }).isInstanceOf(ResourceNotFoundException.class);
        verify(eventService, times(1)).saveEvent(eventVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to validation errors on all validated fields, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenThereAreValidationErrorsInAllValidatedFields() {
        eventVOMock.setName(null);
        eventVOMock.setNumberOfInitialTickets(-3);
        eventVOMock.setAddress(null);
        eventVOMock.setDefaultTicketPrice(-2000);
        eventVOMock.setDateTime(null);
        eventVOMock.setPriceOfBoiTVAnticipated(-3000);
        eventVOMock.setPriceOfBoiTVPurchasedOnDemand(-1500);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid Name field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenNameFieldIsInvalid() {
        eventVOMock.setName(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid NumberOfInitialTickets field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenNumberOfInitialTicketsFieldIsInvalid() {
        eventVOMock.setNumberOfInitialTickets(-3);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid address field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenAddressFieldIsNull() {
        eventVOMock.setAddress(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save event due to invalid address city field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenCityFieldOfAddressIsNull() {
        eventVOMock.getAddress().setCity(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save event due to invalid address state field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenStateFieldOfAddressIsNull() {
        eventVOMock.getAddress().setState(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid DefaultTicketPrice field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenDefaultTicketPriceFieldIsInvalid() {
        eventVOMock.setDefaultTicketPrice(-2000);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid PriceOfBoiTVAnticipated field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenPriceOfBoiTVAnticipatedFieldIsInvalid() {
        eventVOMock.setPriceOfBoiTVAnticipated(-3000);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid PriceOfBoiTVPurchasedOnDemand field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenPriceOfBoiTVPurchasedOnDemandFieldIsInvalid() {
        eventVOMock.setPriceOfBoiTVPurchasedOnDemand(-1500);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventVOMock, clientId);
    }

    @Test
    @DisplayName("Should successfully retrieve a list of events and return it with HTTP Status code 200")
    void shouldFindEventsByClientIdAndReturnListWith200() {
        when(eventService.findEventsByClientId(clientId)).thenReturn(eventVOListMock);

        ResponseEntity<List<EventVO>> result = eventController.findEventsByClientId(clientId);

        assertThat(result.getBody()).isEqualTo(eventVOListMock);
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