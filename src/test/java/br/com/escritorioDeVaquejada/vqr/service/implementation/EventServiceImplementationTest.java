package br.com.escritorioDeVaquejada.vqr.service.implementation;

import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import br.com.escritorioDeVaquejada.vqr.model.Address;
import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.model.EventModel;
import br.com.escritorioDeVaquejada.vqr.model.TicketModel;
import br.com.escritorioDeVaquejada.vqr.repository.EventRepository;
import br.com.escritorioDeVaquejada.vqr.service.ClientService;
import br.com.escritorioDeVaquejada.vqr.service.TicketService;
import br.com.escritorioDeVaquejada.vqr.vo.event.EventResponseVO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceImplementationTest {
    @Mock
    private Mapper mapper;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private ClientService clientService;
    @Mock
    private TicketService ticketService;
    @InjectMocks
    private EventServiceImplementation eventServicesImplementation;
    
    private static ClientModel clientMock;
    private static UUID clientId;
    private static EventModel eventModelMock;
    private static EventResponseVO eventResponseVOMock;
    private static List<EventModel> eventModelListMock;
    private static List<EventResponseVO> eventResponseVOListMock;
    private static List<TicketModel> ticketsMock;

    @BeforeAll
    static void setup(){
        clientId = UUID.randomUUID();

        eventResponseVOMock = new EventResponseVO();
        eventModelMock = new EventModel();
        clientMock = new ClientModel();

        eventResponseVOMock.setName("Evento Teste");
        eventResponseVOMock.setNumberOfInitialTickets(3);
        eventResponseVOMock.setAddress(new Address("Estado teste","Cidade Teste"));
        eventResponseVOMock.setDefaultTicketPrice(2000);
        eventResponseVOMock.setDateTime(LocalDateTime.of(1998, 12, 12, 10, 50));
        eventResponseVOMock.setPriceOfBoiTVAnticipated(3000);
        eventResponseVOMock.setPriceOfBoiTVPurchasedOnDemand(1500);

        eventModelMock.setName(eventResponseVOMock.getName());
        eventModelMock.setNumberOfInitialTickets(eventResponseVOMock.getNumberOfInitialTickets());
        eventModelMock.setAddress(eventResponseVOMock.getAddress());
        eventModelMock.setDefaultTicketPrice(eventResponseVOMock.getDefaultTicketPrice());
        eventModelMock.setDateTime(eventResponseVOMock.getDateTime());
        eventModelMock.setPriceOfBoiTVAnticipated(eventResponseVOMock.getPriceOfBoiTVAnticipated());
        eventModelMock.setPriceOfBoiTVPurchasedOnDemand(eventResponseVOMock.getPriceOfBoiTVPurchasedOnDemand());

        clientMock.setId(clientId);

        eventModelListMock = List.of(eventModelMock);
        eventResponseVOListMock = List.of(eventResponseVOMock);

        ticketsMock = List.of(
                new TicketModel(eventModelMock),
                new TicketModel(eventModelMock),
                new TicketModel(eventModelMock));
    }
    //todo verificar correção para simulação dos métodos: setOwner() e setDateTime()
    //todo descobrir em como deve ser testada a chamada do método captureCurrentDateAndTime()
    @Test
    @DisplayName("Should save a new event and return the corresponding VO")
    void shouldSaveEventSuccessfullyAndReturnEventVo() {
        when(clientService.findEntityById(clientId)).thenReturn(clientMock);
        when(mapper.parseObject(eventResponseVOMock, EventModel.class)).thenReturn(eventModelMock);
        when(eventRepository.save(eventModelMock)).thenReturn(eventModelMock);
        when(ticketService.saveEmptyTickets(eventModelMock)).thenReturn(ticketsMock);
        when(mapper.parseObject(eventModelMock, EventResponseVO.class)).thenReturn(eventResponseVOMock);

        EventResponseVO result = eventServicesImplementation.saveEvent(eventResponseVOMock, clientId);

        assertThat(result).isEqualTo(eventResponseVOMock);
        verify(eventRepository, times(1)).save(eventModelMock);
        verify(ticketService, times(1)).saveEmptyTickets(eventModelMock);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException if the client does not exist when saving a new event")
    void shouldThrowResourceNotFoundExceptionWhenClientDoesNotExistWhenSavingEvent() {
        when(clientService.findEntityById(clientId)).thenThrow(new ResourceNotFoundException("Client not found!"));

        assertThatThrownBy(() -> {
            eventServicesImplementation.saveEvent(eventResponseVOMock, clientId);
        }).isInstanceOf(ResourceNotFoundException.class);
        verify(eventRepository, never()).save(eventModelMock);
        verify(ticketService, never()).saveEmptyTickets(eventModelMock);
    }

    @Test
    @DisplayName("Should return a list of events as VO for an existing client")
    void shouldFindAllEventsByClientIdAndReturnEventVoList() {
        when(clientService.findEntityById(clientId)).thenReturn(clientMock);
        when(eventRepository.findAllByOwnerOrderByDateTime(clientMock)).thenReturn(eventModelListMock);
        when(mapper.parseListObjects(eventModelListMock, EventResponseVO.class)).thenReturn(eventResponseVOListMock);

        List<EventResponseVO> result = eventServicesImplementation.findEventsByClientId(clientId);

        assertThat(result).isEqualTo(eventResponseVOListMock);
        verify(eventRepository, times(1)).findAllByOwnerOrderByDateTime(clientMock);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException if the client does not exist when retrieving events")
    void shouldThrowResourceNotFoundExceptionWhenClientDoesNotExistWhenSearchingForEvents(){
        when(clientService.findEntityById(clientId)).thenThrow(new ResourceNotFoundException("Client not found!"));
        assertThatThrownBy(() -> eventServicesImplementation.findEventsByClientId(clientId)).isInstanceOf(ResourceNotFoundException.class);
       verify(clientService, times(1)).findEntityById(clientId);
    }

    @Test
    @DisplayName("Should return the event VO successfully when finding by ID")
    void shouldFindEventByIdAndReturnEventVo() {
        UUID eventId = UUID.randomUUID();

        when(eventRepository.findById(eventId)).thenReturn(Optional.ofNullable(eventModelMock));
        when(mapper.parseObject(eventModelMock, EventResponseVO.class)).thenReturn(eventResponseVOMock);

        EventResponseVO event = eventServicesImplementation.findEventByID(eventId);

        assertThat(event).isEqualTo(eventResponseVOMock);
        verify(eventRepository, times(1)).findById(eventId);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException if the event does not exist when finding by ID")
    void shouldThrowResourceNotFoundExceptionWhenEventDoesNotExist(){
        UUID eventId = UUID.randomUUID();

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> eventServicesImplementation.findEventByID(eventId)).isInstanceOf(ResourceNotFoundException.class);
        verify(eventRepository, times(1)).findById(eventId);
    }
}