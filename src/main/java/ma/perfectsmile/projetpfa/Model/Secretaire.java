package ma.perfectsmile.projetpfa.Model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Secrétaire")
public class Secretaire extends Utilisateur {

}