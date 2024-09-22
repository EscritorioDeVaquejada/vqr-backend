package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.model.Address;
import br.com.escritorioDeVaquejada.vqr.service.EventService;
import br.com.escritorioDeVaquejada.vqr.vo.address.AddressVO;
import br.com.escritorioDeVaquejada.vqr.vo.event.EventRequestVO;
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

    private static EventRequestVO eventRequestVO;
    private static EventResponseVO eventResponseVO;
    private static List<EventResponseVO> eventResponseVOList;
    private static UUID clientId;

    @BeforeAll
    static void setupForAllTests() {
        clientId = UUID.randomUUID();
        eventRequestVO = new EventRequestVO();
        eventResponseVO = new EventResponseVO();
        eventResponseVOList = List.of(eventResponseVO);
    }

    @BeforeEach
    void setupForEachTest() {
        eventRequestVO.setName("Evento Teste");
        eventRequestVO.setNumberOfInitialTickets(3);
        eventRequestVO.setAddress(new AddressVO("Estado teste", "Cidade Teste"));
        eventRequestVO.setDefaultTicketPrice(2000);
        eventRequestVO.setPriceOfBoiTVAnticipated(3000);
        eventRequestVO.setPriceOfBoiTVPurchasedOnDemand(1500);

        eventResponseVO.setId(clientId);
        eventResponseVO.setName(eventRequestVO.getName());
        eventResponseVO.setNumberOfInitialTickets(eventRequestVO.getNumberOfInitialTickets());
        eventResponseVO.setAddress(eventRequestVO.getAddress());
        eventResponseVO.setDefaultTicketPrice(eventRequestVO.getDefaultTicketPrice());
        eventResponseVO.setDateTime(LocalDateTime.now());
        eventResponseVO.setPriceOfBoiTVAnticipated(eventRequestVO.getPriceOfBoiTVAnticipated());
        eventResponseVO.setPriceOfBoiTVPurchasedOnDemand(eventRequestVO
                .getPriceOfBoiTVPurchasedOnDemand());
    }

    @Test
    @DisplayName("Should successfully save an event and return it with HTTP status code 201")
    void shouldSaveEventSuccessfullyAndReturnWith201() {
        when(errorsInRequest.hasErrors()).thenReturn(false);
        when(eventService.saveEvent(eventRequestVO, clientId)).thenReturn(eventResponseVO);

        ResponseEntity<EventResponseVO> result = eventController.saveEvent(eventRequestVO,
                clientId, errorsInRequest);

        assertThat(result.getBody()).isEqualTo(eventResponseVO);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(eventService, times(1)).saveEvent(eventRequestVO, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to non-existent owner, returning " +
            "ResourceNotFoundException with HTTP status code 404")
    void shouldThrowResourceNotFoundExceptionWhenOwnerDoesNotExistWhenSavingEvent() {
        when(errorsInRequest.hasErrors()).thenReturn(false);
        when(eventService.saveEvent(eventRequestVO, clientId)).thenThrow(new
                ResourceNotFoundException("Client not found!"));

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventRequestVO, clientId, errorsInRequest);
        }).isInstanceOf(ResourceNotFoundException.class);
        verify(eventService, times(1)).saveEvent(eventRequestVO, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to validation errors on all validated fields, " +
            "returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenThereAreValidationErrorsInAllValidatedFields() {
        eventRequestVO.setName(null);
        eventRequestVO.setNumberOfInitialTickets(-3);
        eventRequestVO.setAddress(null);
        eventRequestVO.setDefaultTicketPrice(-2000);
        eventRequestVO.setPriceOfBoiTVAnticipated(-3000);
        eventRequestVO.setPriceOfBoiTVPurchasedOnDemand(-1500);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventRequestVO, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventRequestVO, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid Name field, returning " +
            "BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenNameFieldIsInvalid() {
        eventRequestVO.setName(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventRequestVO, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventRequestVO, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid NumberOfInitialTickets field, " +
            "returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenNumberOfInitialTicketsFieldIsInvalid() {
        eventRequestVO.setNumberOfInitialTickets(-3);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventRequestVO, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventRequestVO, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid address field, returning " +
            "BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenAddressFieldIsNull() {
        eventRequestVO.setAddress(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventRequestVO, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventRequestVO, clientId);
    }

    @Test
    @DisplayName("Should not save event due to invalid address city field, " +
            "returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenCityFieldOfAddressIsNull() {
        eventRequestVO.getAddress().setCity(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventRequestVO, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventRequestVO, clientId);
    }

    @Test
    @DisplayName("Should not save event due to invalid address state field, " +
            "returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenStateFieldOfAddressIsNull() {
        eventRequestVO.getAddress().setState(null);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventRequestVO, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventRequestVO, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid DefaultTicketPrice field, " +
            "returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenDefaultTicketPriceFieldIsInvalid() {
        eventRequestVO.setDefaultTicketPrice(-2000);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventRequestVO, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventRequestVO, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid PriceOfBoiTVAnticipated field, " +
            "returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenPriceOfBoiTVAnticipatedFieldIsInvalid() {
        eventRequestVO.setPriceOfBoiTVAnticipated(-3000);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventRequestVO, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventRequestVO, clientId);
    }

    @Test
    @DisplayName("Should not save the event due to invalid PriceOfBoiTVPurchasedOnDemand " +
            "field, returning BadRequestException with HTTP status code 400")
    void shouldThrowBadRequestExceptionWhenPriceOfBoiTVPurchasedOnDemandFieldIsInvalid() {
        eventRequestVO.setPriceOfBoiTVPurchasedOnDemand(-1500);

        when(errorsInRequest.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> {
            eventController.saveEvent(eventRequestVO, clientId, errorsInRequest);
        }).isInstanceOf(BadRequestException.class);
        verify(eventService, never()).saveEvent(eventRequestVO, clientId);
    }

    /*
    @Test
    @DisplayName("Should successfully retrieve a list of events and return it with HTTP Status " +
            "code 200")
    void shouldFindEventsByClientIdAndReturnListWith200() {
        when(eventService.findEventsByClientId(clientId)).thenReturn(eventResponseVOList);

        ResponseEntity<List<EventResponseVO>> result = eventController
                .findEventsByClientId(clientId);

        assertThat(result.getBody()).isEqualTo(eventResponseVOList);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(eventService, times(1)).findEventsByClientId(clientId);
    }

    @Test
    @DisplayName("Should not retrieve a list of events due to non-existent owner, returning " +
            "ResourceNotFoundException with HTTP status code 404")
    void shouldThrowResourceNotFoundExceptionWhenOwnerDoesNotExistWhenSearchingForEvents() {
        when(eventService.findEventsByClientId(clientId)).thenThrow(new
                ResourceNotFoundException("Client not found!"));

        assertThatThrownBy(() -> {
            eventController.findEventsByClientId(clientId);
        }).isInstanceOf(ResourceNotFoundException.class);
        verify(eventService, times(1)).findEventsByClientId(clientId);
    }
    
     */
}