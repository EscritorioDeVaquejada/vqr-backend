package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.model.Address;
import br.com.escritorioDeVaquejada.vqr.service.EventService;
import br.com.escritorioDeVaquejada.vqr.vo.event.EventResponseVO;
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
    private static EventResponseVO eventResponseVOMock;
    private static List<EventResponseVO> eventResponseVOListMock;
    private static UUID clientId;

    @BeforeEach
    void setupForEachTest(){
        eventResponseVOMock.setName("Evento Teste");
        eventResponseVOMock.setNumberOfInitialTickets(3);
        eventResponseVOMock.setAddress(new Address("Estado teste","Cidade Teste"));
        eventResponseVOMock.setDefaultTicketPrice(2000);
        eventResponseVOMock.setDateTime(LocalDateTime.of(1998, 12, 12, 10, 50));
        eventResponseVOMock.setPriceOfBoiTVAnticipated(3000);
        eventResponseVOMock.setPriceOfBoiTVPurchasedOnDemand(1500);
    }

    @BeforeAll
    static void setupForAllTests(){
        clientId = UUID.randomUUID();
        eventResponseVOMock = new EventResponseVO();
        eventResponseVOListMock = List.of(eventResponseVOMock);
    }

    @Test
    @DisplayName("Should successfully save an event and return it with HTTP status code 201")
    void shouldSaveEventSuccessfullyAndReturnWith201() {
        when(errorsInRequest.hasErrors()).thenReturn(false);
        when(eventService.saveEvent(eventResponseVOMock, clientId)).thenReturn(eventResponseVOMock);

        ResponseEntity<EventResponseVO> result = eventController.saveEvent(eventResponseVOMock, clientId, errorsInRequest);

        assertThat(result.getBody()).isEqualTo(eventResponseVOMock);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(eventService, times(1)).saveEvent(eventResponseVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to non-existent owner, returning ResourceNotFoundException with HTTP status code 404")
    void shouldThrowResourceNotFoundExceptionWhenOwnerDoesNotExistWhenSavingEvent() {
        when(errorsInRequest.hasErrors()).thenReturn(false);
        when(eventService.saveEvent(eventResponseVOMock, clientId)).thenThrow(new
                ResourceNotFoundException("Client not found!"));

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventResponseVOMock, clientId, errorsInRequest);
        }).isInstanceOf(ResourceNotFoundException.class);
        verify(eventService, times(1)).saveEvent(eventResponseVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to validation errors on all validated fields, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenThereAreValidationErrorsInAllValidatedFields() {
        eventResponseVOMock.setName(null);
        eventResponseVOMock.setNumberOfInitialTickets(-3);
        eventResponseVOMock.setAddress(null);
        eventResponseVOMock.setDefaultTicketPrice(-2000);
        eventResponseVOMock.setDateTime(null);
        eventResponseVOMock.setPriceOfBoiTVAnticipated(-3000);
        eventResponseVOMock.setPriceOfBoiTVPurchasedOnDemand(-1500);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventResponseVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventResponseVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid Name field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenNameFieldIsInvalid() {
        eventResponseVOMock.setName(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventResponseVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventResponseVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid NumberOfInitialTickets field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenNumberOfInitialTicketsFieldIsInvalid() {
        eventResponseVOMock.setNumberOfInitialTickets(-3);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventResponseVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventResponseVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid address field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenAddressFieldIsNull() {
        eventResponseVOMock.setAddress(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventResponseVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventResponseVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save event due to invalid address city field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenCityFieldOfAddressIsNull() {
        eventResponseVOMock.getAddress().setCity(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventResponseVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventResponseVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save event due to invalid address state field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenStateFieldOfAddressIsNull() {
        eventResponseVOMock.getAddress().setState(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventResponseVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventResponseVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid DefaultTicketPrice field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenDefaultTicketPriceFieldIsInvalid() {
        eventResponseVOMock.setDefaultTicketPrice(-2000);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventResponseVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventResponseVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid PriceOfBoiTVAnticipated field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenPriceOfBoiTVAnticipatedFieldIsInvalid() {
        eventResponseVOMock.setPriceOfBoiTVAnticipated(-3000);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventResponseVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventResponseVOMock, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid PriceOfBoiTVPurchasedOnDemand field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenPriceOfBoiTVPurchasedOnDemandFieldIsInvalid() {
        eventResponseVOMock.setPriceOfBoiTVPurchasedOnDemand(-1500);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventResponseVOMock, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventResponseVOMock, clientId);
    }

    @Test
    @DisplayName("Should successfully retrieve a list of events and return it with HTTP Status code 200")
    void shouldFindEventsByClientIdAndReturnListWith200() {
        when(eventService.findEventsByClientId(clientId)).thenReturn(eventResponseVOListMock);

        ResponseEntity<List<EventResponseVO>> result = eventController.findEventsByClientId(clientId);

        assertThat(result.getBody()).isEqualTo(eventResponseVOListMock);
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