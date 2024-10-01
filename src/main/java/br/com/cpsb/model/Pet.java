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
    private String nome;

    //TODO: Adicionar o restante dos atributos:
    // - raca (Raca CLASS)
    // - especie (Especie CLASS)
    // - peso (Double) opcional
    // - observacoes (ArrayList<String>) opcional
    // - foto (String) opcional
    // - genero (Genero ENUM)
    // - endereco (Endereco CLASS) ou
    // - petshop (Petshop CLASS)
}
