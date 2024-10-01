package br.com.cpsb.controller;

import br.com.cpsb.model.Pet;
import br.com.cpsb.model.Raca;
import br.com.cpsb.repository.PetRepository;
import br.com.cpsb.repository.RacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private RacaRepository racaRepository;

    //Lista de pets
    @GetMapping("/lista_pets")
    public ModelAndView listaPets() {
        ModelAndView mv = new ModelAndView("lista_pets");

        List<Pet> pets = petRepository.findAll();
        mv.addObject("pets", pets);

        return mv;
    }

    //Cadastro de pet
    @PostMapping("/cadastrar_pet")
    public ModelAndView cadastrarPet(Pet newPet) {
        ModelAndView mv = new ModelAndView("redirect:/lista_pets");
        
        Optional<Raca> racaOptional = racaRepository.findAll().stream().filter(raca ->
                raca.getNome().equals(newPet.getRaca().getNome())
        ).findFirst();
        
        if (racaOptional.isPresent()) {
            newPet.setRaca(racaOptional.get());
        } else {
            Raca raca = new Raca();
            raca.setNome(newPet.getRaca().getNome());
            racaRepository.save(raca);
            newPet.setRaca(raca);
        }

        petRepository.save(newPet);
        mv.addObject("pet", newPet);

        return mv;
    }

    @GetMapping("/formulario_cadastrar_pet")
    public ModelAndView formularioCadastrarPet() {
        ModelAndView mv = new ModelAndView("formulario_cadastrar_pet");
        mv.addObject("pet", new Pet());

        List<String> racas = racaRepository.findAll().stream().map(Raca::getNome).toList();
        mv.addObject("lista_racas", racas);

        return mv;
    }

    //Edição de pet
    @PostMapping("/atualizar_pet/{id}")
    public ModelAndView atualizarPet(@PathVariable Long id, Pet newPet, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("formulario_atualizar_pet");
            mv.addObject("pet", newPet);

            List<String> racas = racaRepository.findAll().stream().map(Raca::getNome).toList();
            mv.addObject("lista_racas", racas);

            return mv;
        }

        Optional<Pet> petOptional = petRepository.findById(id);

        if (petOptional.isPresent()) {
            Pet pet = petOptional.get();
            pet.setNome(newPet.getNome());

            Optional<Raca> racaOptional = racaRepository.findAll().stream().filter(raca ->
                    raca.getNome().equals(newPet.getRaca().getNome())
            ).findFirst();

            if (racaOptional.isPresent()) {
                pet.setRaca(racaOptional.get());
            } else {
                Raca raca = new Raca();
                raca.setNome(newPet.getRaca().getNome());
                racaRepository.save(raca);
                pet.setRaca(raca);
            }

            petRepository.save(pet);
        }

        return new ModelAndView("redirect:/lista_pets");
    }

    @GetMapping("/formulario_atualizar_pet/{id}")
    public ModelAndView formularioAtualizarPet(@PathVariable Long id) {
        Optional<Pet> petOptional = petRepository.findById(id);

        if (petOptional.isPresent()) {
            Pet pet = petOptional.get();
            ModelAndView mv = new ModelAndView("formulario_atualizar_pet");
            mv.addObject("pet", pet);

            List<String> racas = racaRepository.findAll().stream().map(Raca::getNome).toList();
            mv.addObject("lista_racas", racas);

            return mv;
        } else {
            return new ModelAndView("redirect:/lista_pets");
        }
    }

    //Detalhes de pet
    @GetMapping("/detalhes_pet/{id}")
    public ModelAndView detalhesPet(@PathVariable Long id) {
        Optional<Pet> petOptional = petRepository.findById(id);

        if (petOptional.isPresent()) {
            Pet pet = petOptional.get();

            ModelAndView mv = new ModelAndView("detalhes_pet");
            mv.addObject("pet", pet);

            return mv;
        } else {
            return new ModelAndView("redirect:/lista_pets");
        }
    }


    //Remover pet
    @GetMapping("/remover_pet/{id}")
    public String removerPet(@PathVariable Long id) {
        Optional<Pet> petOptional = petRepository.findById(id);

        if (petOptional.isPresent()) {
            petRepository.deleteById(id);
        }
        
        return "redirect:/lista_pets";
    }

}
