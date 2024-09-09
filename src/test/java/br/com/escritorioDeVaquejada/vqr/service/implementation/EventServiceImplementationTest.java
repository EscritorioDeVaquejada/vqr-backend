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
import br.com.escritorioDeVaquejada.vqr.vo.event.EventRequestVO;
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

    private static ClientModel clientModel;
    private static UUID clientId;
    private static EventModel eventModel;
    private static List<EventModel> eventModelList;
    private static EventResponseVO eventResponseVO;
    private static EventRequestVO eventRequestVO;
    private static List<EventResponseVO> eventResponseVOList;
    private static List<TicketModel> ticketModelList;

    @BeforeAll
    static void setupForAllTests() {
        clientId = UUID.randomUUID();

        eventResponseVO = new EventResponseVO();
        eventRequestVO = new EventRequestVO();
        eventModel = new EventModel();
        clientModel = new ClientModel();

        eventModel.setId(clientId);
        eventModel.setName("Evento Teste");
        eventModel.setNumberOfInitialTickets(3);
        eventModel.setAddress(
                new Address("Estado teste", "Cidade Teste"));
        eventModel.setDefaultTicketPrice(2000);
        eventModel.setDateTime(LocalDateTime.now());
        eventModel.setPriceOfBoiTVAnticipated(3000);
        eventModel.setPriceOfBoiTVPurchasedOnDemand(1500);

        eventResponseVO.setId(eventModel.getId());
        eventResponseVO.setName(eventModel.getName());
        eventResponseVO.setNumberOfInitialTickets(eventModel.getNumberOfInitialTickets());
        eventResponseVO.setAddress(eventModel.getAddress());
        eventResponseVO.setDefaultTicketPrice(eventModel.getDefaultTicketPrice());
        eventResponseVO.setDateTime(eventModel.getDateTime());
        eventResponseVO.setPriceOfBoiTVAnticipated(eventModel.getPriceOfBoiTVAnticipated());
        eventResponseVO.setPriceOfBoiTVPurchasedOnDemand(
                eventModel.getPriceOfBoiTVPurchasedOnDemand());

        eventRequestVO.setName(eventModel.getName());
        eventRequestVO.setNumberOfInitialTickets(eventModel.getNumberOfInitialTickets());
        eventRequestVO.setAddress(eventModel.getAddress());
        eventRequestVO.setDefaultTicketPrice(eventModel.getDefaultTicketPrice());
        eventRequestVO.setPriceOfBoiTVAnticipated(eventModel.getPriceOfBoiTVAnticipated());
        eventRequestVO.setPriceOfBoiTVPurchasedOnDemand(
                eventModel.getPriceOfBoiTVPurchasedOnDemand());

        eventModelList = List.of(eventModel);
        eventResponseVOList = List.of(eventResponseVO);

        ticketModelList = List.of(
                new TicketModel(eventModel),
                new TicketModel(eventModel),
                new TicketModel(eventModel));
    }

    //todo verificar correção para simulação dos métodos: setOwner() e setDateTime()
    //todo descobrir em como deve ser testada a chamada do método captureCurrentDateAndTime()
    @Test
    @DisplayName("Should save a new event and return the corresponding VO")
    void shouldSaveEventSuccessfullyAndReturnEventVo() {
        when(clientService.findEntityById(clientId)).thenReturn(clientModel);
        when(mapper.parseObject(eventRequestVO, EventModel.class)).thenReturn(eventModel);
        when(eventRepository.save(eventModel)).thenReturn(eventModel);
        when(ticketService.saveEmptyTickets(eventModel)).thenReturn(ticketModelList);
        when(mapper.parseObject(eventModel, EventResponseVO.class)).thenReturn(eventResponseVO);

        EventResponseVO result = eventServicesImplementation.saveEvent(eventRequestVO, clientId);

        assertThat(result).isEqualTo(eventResponseVO);
        verify(eventRepository, times(1)).save(eventModel);
        verify(ticketService, times(1)).saveEmptyTickets(eventModel);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException if the clientModel does not " +
            "exist when saving a new event")
    void shouldThrowResourceNotFoundExceptionWhenClientDoesNotExistWhenSavingEvent() {
        when(clientService.findEntityById(clientId))
                .thenThrow(new ResourceNotFoundException("Client not found!"));

        assertThatThrownBy(() -> {
            eventServicesImplementation.saveEvent(eventRequestVO, clientId);
        }).isInstanceOf(ResourceNotFoundException.class);
        verify(eventRepository, never()).save(eventModel);
        verify(ticketService, never()).saveEmptyTickets(eventModel);
    }

    @Test
    @DisplayName("Should return a list of events as VO for an existing clientModel")
    void shouldFindAllEventsByClientIdAndReturnEventVoList() {
        when(clientService.findEntityById(clientId)).thenReturn(clientModel);
        when(eventRepository.findAllByOwnerOrderByDateTime(clientModel))
                .thenReturn(eventModelList);
        when(mapper.parseListObjects(eventModelList, EventResponseVO.class))
                .thenReturn(eventResponseVOList);

        List<EventResponseVO> result = eventServicesImplementation.findEventsByClientId(clientId);

        assertThat(result).isEqualTo(eventResponseVOList);
        verify(eventRepository, times(1))
                .findAllByOwnerOrderByDateTime(clientModel);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException if the clientModel does " +
            "not exist when retrieving events")
    void shouldThrowResourceNotFoundExceptionWhenClientDoesNotExistWhenSearchingForEvents() {
        when(clientService.findEntityById(clientId))
                .thenThrow(new ResourceNotFoundException("Client not found!"));
        assertThatThrownBy(() -> eventServicesImplementation.findEventsByClientId(clientId))
                .isInstanceOf(ResourceNotFoundException.class);
        verify(clientService, times(1)).findEntityById(clientId);
    }

    @Test
    @DisplayName("Should return the event VO successfully when finding by ID")
    void shouldFindEventByIdAndReturnEventVo() {
        UUID eventId = UUID.randomUUID();

        when(eventRepository.findById(eventId)).thenReturn(Optional.ofNullable(eventModel));
        when(mapper.parseObject(eventModel, EventResponseVO.class)).thenReturn(eventResponseVO);

        EventResponseVO event = eventServicesImplementation.findEventByID(eventId);

        assertThat(event).isEqualTo(eventResponseVO);
        verify(eventRepository, times(1)).findById(eventId);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException if the event does not exist when " +
            "finding by ID")
    void shouldThrowResourceNotFoundExceptionWhenEventDoesNotExist() {
        UUID eventId = UUID.randomUUID();

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> eventServicesImplementation.findEventByID(eventId))
                .isInstanceOf(ResourceNotFoundException.class);
        verify(eventRepository, times(1)).findById(eventId);
    }
}