/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ega.springwsoap.repository;

import com.ega.springwsoap.models.Answer;
import com.ega.springwsoap.models.Persona;
import io.spring.guides.gs_producing_web_service.PersonaXml;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 *
 * @author sa
 */
@Component
public class PersonaLocalRepository {
	private static final Map<String, Persona> persons = new HashMap<>();

	@PostConstruct
	public void initData() {
		Persona spain = new Persona();
		spain.setFirstName("Hose");
		spain.setLastName("Martin");
		spain.setRnokpp("1234567890");
		spain.setPasport("KK701230");
		spain.setBirthDate(LocalDate.parse("1990-04-02"));
		spain.setUnzr("19900402-00010");

		persons.put(spain.getRnokpp(), spain);

		Persona poland = new Persona();
		poland.setFirstName("Mateush");
		poland.setLastName("Woznitski");
		poland.setRnokpp("1111111111");
		poland.setPasport("KK701231");
		poland.setBirthDate(LocalDate.parse("1991-05-03"));
		poland.setUnzr("19910503-00011");
                
		persons.put(poland.getRnokpp(), poland);

		Persona uk = new Persona();
		uk.setFirstName("John");
		uk.setLastName("Smith");
		uk.setRnokpp("23232323");
		uk.setPasport("KK701232");
		uk.setBirthDate(LocalDate.parse("1992-06-04"));
		uk.setUnzr("19920604-00012");
                
		persons.put(uk.getRnokpp(), uk);

                Persona ua = new Persona();
		ua.setFirstName("Taras");
		ua.setLastName("Kuznets");
		ua.setRnokpp("11111");
		ua.setPasport("KK701243");
		ua.setBirthDate(LocalDate.parse("1995-07-04"));
		ua.setUnzr("19950704-00015");
                
		persons.put(ua.getRnokpp(), ua);
	}

	public PersonaXml findByRnokpp(String rnokpp) {
		Assert.notNull(rnokpp, "The persons's pnokpp must not be null");
		return persons.get(rnokpp).toXML();
	}

	public List<PersonaXml> findAll() {
            List<PersonaXml> personsXml = new ArrayList<>();

            persons.forEach((rnokpp,persona)-> personsXml.add(persona.toXML()));
            
            return personsXml;
	}
        
	public Answer addPersona(PersonaXml personaXml) {
            Answer ans = Answer.builder().status(Boolean.FALSE).descr("Unknown error").build();
            Assert.notNull(personaXml.getRnokpp(), "The persons's pnokpp must not be null");
            try{
                Persona persona = new Persona(personaXml);
                persons.put(personaXml.getRnokpp(), persona);
                ans.setStatus(Boolean.TRUE);
                ans.setDescr("Persona successfully added");
            }
                catch(Exception ex){
                ans.setDescr("Error: "+ex.getMessage());
            }

            return ans;
	}
    
}
