package br.com.michaelnascimento.j12015.stream;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Model implements Serializable {
    @Id
    private Integer id;
    private String description;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.description);
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
        final Model other = (Model) obj;
        return Objects.equals(this.description, other.description);
    }

    @Override
    public String toString() {
        return "Model{" + "id=" + id + ", description=" + description + '}';
    }


}
