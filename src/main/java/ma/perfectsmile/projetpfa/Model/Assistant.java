package ma.perfectsmile.projetpfa.Model;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Assistant")
public class Assistant extends Utilisateur {

}
