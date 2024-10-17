package br.com.cpsb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.Name;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_CPSB_BREED")
public class Breed {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_breed")
    public Long id;
    
    @Column(name = "nm_breed")
    public String name;

    @Column(name = "ds_breed", length = 1000)
    public String description;

    public Breed(String name) {
        this.name = name;
        this.description = "";
    }
    
    public Breed(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
}
