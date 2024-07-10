/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ega.springwsoap.controllers;


import com.ega.springwsoap.models.Answer;
import com.ega.springwsoap.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetPersonaRequest;
import io.spring.guides.gs_producing_web_service.GetPersonaResponse;


import java.util.List;
import com.ega.springwsoap.repository.PersonaLocalRepository;
import com.ega.springwsoap.repository.PersonaRepository;
import com.ega.springwsoap.services.PersonaServiceImpl;
import io.spring.guides.gs_producing_web_service.AddPersonaRequest;
import io.spring.guides.gs_producing_web_service.AddPersonaResponse;
import io.spring.guides.gs_producing_web_service.DeletePersonaRequest;
import io.spring.guides.gs_producing_web_service.DeletePersonaResponse;
import io.spring.guides.gs_producing_web_service.GetPersonaListResponse;
import io.spring.guides.gs_producing_web_service.PersonaXml;
import io.spring.guides.gs_producing_web_service.UpdatePersonaRequest;
import io.spring.guides.gs_producing_web_service.UpdatePersonaResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

//Ця анотація відноситься до компоненту Lombok. Вона допомогає створити усі конструктори класів та перемених яки відносятся до данного класу.
//Тут він потрібен для того, щоб ініціалізувати PersonaService service і таким чином включити його в область видимості фреймворка SPRING
//(дивись в документаціі к фреймворку "впровадження залежностей через конструктор")
@AllArgsConstructor
@Endpoint
public class PersonaController {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

        private PersonaServiceImpl service;

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonaRequest")
	@ResponsePayload
	public GetPersonaResponse getPersona(@RequestPayload GetPersonaRequest request) {
            return service.find(request.getRnokpp());
	}

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonaListRequest")
	@ResponsePayload
	public GetPersonaListResponse getPersonaList() {
            return service.showAllPersons();
	}
        
        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPersonaRequest")
	@ResponsePayload
	public AddPersonaResponse addPersona(@RequestPayload AddPersonaRequest request) {
            return service.addPersona(request);
	}

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonaRequest")
	@ResponsePayload
        @Transactional
	public DeletePersonaResponse deletePersona(@RequestPayload DeletePersonaRequest request) {
            return service.deletePersona(request.getRnokpp());
	}

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonaRequest")
	@ResponsePayload
	public UpdatePersonaResponse updatePersona(@RequestPayload UpdatePersonaRequest request) {
            return service.updatePersona(request);
	}

}