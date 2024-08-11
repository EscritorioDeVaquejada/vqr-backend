package br.com.escritorioDeVaquejada.vqr.services.implementations;

import br.com.escritorioDeVaquejada.vqr.exceptions.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mappers.Mapper;
import br.com.escritorioDeVaquejada.vqr.models.Address;
import br.com.escritorioDeVaquejada.vqr.models.ClientModel;
import br.com.escritorioDeVaquejada.vqr.models.EventModel;
import br.com.escritorioDeVaquejada.vqr.models.TicketModel;
import br.com.escritorioDeVaquejada.vqr.repositories.EventRepository;
import br.com.escritorioDeVaquejada.vqr.services.ClientServices;
import br.com.escritorioDeVaquejada.vqr.services.TicketServices;
import br.com.escritorioDeVaquejada.vqr.vo.EventVo;
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
class EventServicesImplementationTest {
    @Mock
    private Mapper mapper;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private ClientServices clientServices;
    @Mock
    private TicketServices ticketServices;
    @InjectMocks
    private EventServicesImplementation eventServicesImplementation;
    
    private static ClientModel ownerMock;
    private static UUID ownerId;
    private static EventModel eventMock;
    private static EventVo eventVoMock;
    private static List<EventModel> eventListMock;
    private static List<EventVo> eventListVoMock;
    private static List<TicketModel> ticketList;

    @BeforeAll
    static void setup(){
        ownerId = UUID.randomUUID();

        eventVoMock = new EventVo();
        eventMock = new EventModel();
        ownerMock = new ClientModel();

        eventVoMock.setName("Evento Teste");
        eventVoMock.setStartPasswords(3);
        eventVoMock.setAddress(new Address("Estado teste","Cidade Teste"));
        eventVoMock.setDefaultTicketPrice(2000);
        eventVoMock.setDateTime(LocalDateTime.of(1998, 12, 12, 10, 50));
        eventVoMock.setPriceOfBoiTVAnticipated(3000);
        eventVoMock.setPriceOfBoiTvPurchasedOnDemand(1500);

        eventMock.setName(eventVoMock.getName());
        eventMock.setStartPasswords(eventVoMock.getStartPasswords());
        eventMock.setAddress(eventVoMock.getAddress());
        eventMock.setDefaultTicketPrice(eventVoMock.getDefaultTicketPrice());
        eventMock.setDateTime(eventVoMock.getDateTime());
        eventMock.setPriceOfBoiTVAnticipated(eventVoMock.getPriceOfBoiTVAnticipated());
        eventMock.setPriceOfBoiTvPurchasedOnDemand(eventVoMock.getPriceOfBoiTvPurchasedOnDemand());

        ownerMock.setId(ownerId);

        eventListMock = List.of(eventMock);
        eventListVoMock = List.of(eventVoMock);

        ticketList = List.of(
                new TicketModel(eventMock),
                new TicketModel(eventMock),
                new TicketModel(eventMock));
    }
    //todo verificar correção para simulação do método .setOwner() e .setDateTime()
    //todo resolver como deve ser testado a chamada do método captureCurrentDateAndTime()
    @Test
    @DisplayName("Deve conseguir com sucesso salvar um novo evento, retornando-o como VO")
    void saveEventCase01() {
        when(clientServices.findEntityById(ownerId)).thenReturn(ownerMock);
        when(mapper.parseObject(eventVoMock, EventModel.class)).thenReturn(eventMock);
        when(eventRepository.save(eventMock)).thenReturn(eventMock);
        when(ticketServices.saveEmptyTickets(eventMock)).thenReturn(ticketList);
        when(mapper.parseObject(eventMock, EventVo.class)).thenReturn(eventVoMock);

        EventVo result = eventServicesImplementation.saveEvent(eventVoMock, ownerId);

        assertThat(result).isEqualTo(eventVoMock);
        verify(eventRepository, times(1)).save(eventMock);
        verify(ticketServices, times(1)).saveEmptyTickets(eventMock);
    }

    @Test
    @DisplayName("Não deve conseguir salvar um novo evento, devido ao seu proprietário não existir, lançando uma ResourceNotFoundException")
    void saveEventCase02(){
        /*
        ClientModel owner = clientServices.findEntityById(clientId);
        EventModel eventToBeSaved = mapper.parseObject(newEvent, EventModel.class);
        eventToBeSaved.setOwner(owner);
        eventToBeSaved.setDateTime(captureCurrentDateAndTime());
        EventModel eventCreated = eventRepository.save(eventToBeSaved);
        ticketServices.saveEmptyTickets(eventCreated);
        return mapper.parseObject(eventCreated, EventVo.class);
        */
        when(clientServices.findEntityById(ownerId)).thenThrow(new ResourceNotFoundException("Cliente não encontrado!"));

        assertThatThrownBy(() -> {
            eventServicesImplementation.saveEvent(eventVoMock, ownerId);
        }).isInstanceOf(ResourceNotFoundException.class);
        verify(eventRepository, never()).save(eventMock);
        verify(ticketServices, never()).saveEmptyTickets(eventMock);
    }

    @Test
    @DisplayName("Deve conseguir com sucesso uma lista de todos os eventos de um proprietário, retornando-a como VO")
    void findEventsByClientIdCase01() {
        when(clientServices.findEntityById(ownerId)).thenReturn(ownerMock);
        when(eventRepository.findAllByOwnerOrderByDateTime(ownerMock)).thenReturn(eventListMock);
        when(mapper.parseListObjects(eventListMock, EventVo.class)).thenReturn(eventListVoMock);

        List<EventVo> result = eventServicesImplementation.findEventsByClientId(ownerId);

        assertThat(result).isEqualTo(eventListVoMock);
        verify(eventRepository, times(1)).findAllByOwnerOrderByDateTime(ownerMock);
    }

    @Test
    @DisplayName("Não deve conseguir obter uma lista de eventos, devido ao proprietário não existir, lançando uma exceção ResorceNotFoundException")
    void findEventsByClientIdCase02(){
        when(clientServices.findEntityById(ownerId)).thenThrow(new ResourceNotFoundException("Cliente não encontrado!"));
        assertThatThrownBy(() -> eventServicesImplementation.findEventsByClientId(ownerId)).isInstanceOf(ResourceNotFoundException.class);
       verify(clientServices, times(1)).findEntityById(ownerId);
    }

    @Test
    @DisplayName("Deve conseguir com sucesso obter um evento, retornando-o como VO")
    void findEventByIDCase01() {
        UUID eventId = UUID.randomUUID();

        when(eventRepository.findById(eventId)).thenReturn(Optional.ofNullable(eventMock));
        when(mapper.parseObject(eventMock, EventVo.class)).thenReturn(eventVoMock);

        EventVo event = eventServicesImplementation.findEventByID(eventId);

        assertThat(event).isEqualTo(eventVoMock);
        verify(eventRepository, times(1)).findById(eventId);
    }

    @Test
    @DisplayName("Não deve conseguir obter um evento, lançando uma exceção ResourceNotFoundException")
    void findEventByIDCase02(){
        UUID eventId = UUID.randomUUID();

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> eventServicesImplementation.findEventByID(eventId)).isInstanceOf(ResourceNotFoundException.class);
        verify(eventRepository, times(1)).findById(eventId);
    }
}