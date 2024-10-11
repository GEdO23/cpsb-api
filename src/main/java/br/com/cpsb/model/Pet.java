package br.com.cpsb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CPSB_PET")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    private Long id;
    
    @Column(name = "nm_pet")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "id_breed")
    private Breed breed;

    //TODO: Add the rest of pet atributes
    // - specie (Specie CLASS)
    // - weight (Double) optional
    // - observations (ArrayList<String>) optional
    // - photo (String) optional
    // - sex (Sex ENUM)
    // - petShop (PetShop CLASS) with address (Address CLASS)
}
