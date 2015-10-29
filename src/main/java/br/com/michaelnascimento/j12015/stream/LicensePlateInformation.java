package br.com.michaelnascimento.j12015.stream;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="LicensePlateInformation.all", query="select lpi from LicensePlateInformation lpi")
public class LicensePlateInformation implements Serializable {
    @Id
    private Integer id;
    private String vin;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private State state;
    @ManyToOne
    private Model model;
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.vin);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LicensePlateInformation other = (LicensePlateInformation) obj;
        return Objects.equals(this.vin, other.vin);
    }

    public LocalDate getDate() {
        return date;
    }

    public State getState() {
        return state;
    }

    public Model getModel() {
        return model;
    }
    
    public boolean ytd(int referenceYear, int referenceMonth) {
        return date.getYear() >= referenceYear
                && date.getMonth().getValue() <= referenceMonth;
    }
}
