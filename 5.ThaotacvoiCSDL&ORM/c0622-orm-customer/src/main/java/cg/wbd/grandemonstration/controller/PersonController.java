package cg.wbd.grandemonstration.controller;


import cg.wbd.grandemonstration.model.Person;
import cg.wbd.grandemonstration.model.dto.PersonCreateDTO;
import cg.wbd.grandemonstration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/create")
    public String showCreatePage(Model model) {

        model.addAttribute("person", new PersonCreateDTO());
        return "person/create";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("person") PersonCreateDTO personDTO) {
        model.addAttribute("person", new PersonCreateDTO());

        Person person = new Person();
        person.setFullName(personDTO.getFullName());
        person.setYob(personDTO.getYob());
        person.setVehicleNumber(personDTO.getVehicleNumber());

        Date departmentDay = new Date();
        departmentDay.setDate(personDTO.getDepartureDay());
        departmentDay.setMonth(personDTO.getDepartureMonth());
        departmentDay.setYear(personDTO.getDepartureYear());

        person.setDepartureDay(departmentDay);

        personService.save(person);


        return "person/create";
    }
}
