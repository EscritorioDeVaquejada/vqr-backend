package br.com.escritorioDeVaquejada.vqr.vo.eventsVo;

import br.com.escritorioDeVaquejada.vqr.models.Address;
import br.com.escritorioDeVaquejada.vqr.models.Client;
import br.com.escritorioDeVaquejada.vqr.models.Finance;
import br.com.escritorioDeVaquejada.vqr.models.Ticket;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class EventVo {
    private UUID id;
    @NotEmpty
    private String name;
    @NotNull @PositiveOrZero
    private int startPasswords;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime;
    @NotEmpty
    private Address address;
}
