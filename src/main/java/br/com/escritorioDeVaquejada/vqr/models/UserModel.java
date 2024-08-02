package br.com.escritorioDeVaquejada.vqr.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "Users")
public class UserModel implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;
    @Column(unique = true)
    private String login;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<TicketModel> ticketList;

}
