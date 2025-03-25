/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ega.springwsoap.controllers;


import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import com.ega.springwsoap.services.PersonaServiceImpl;
import io.spring.guides.gs_producing_web_service.AddPersonaRequest;
import io.spring.guides.gs_producing_web_service.AddPersonaResponse;
import io.spring.guides.gs_producing_web_service.DeletePersonaRequest;
import io.spring.guides.gs_producing_web_service.DeletePersonaResponse;
import io.spring.guides.gs_producing_web_service.GetPersonaListByBirthDateRequest;
import io.spring.guides.gs_producing_web_service.GetPersonaListByFirstNameRequest;
import io.spring.guides.gs_producing_web_service.GetPersonaListByLastNameRequest;
import io.spring.guides.gs_producing_web_service.GetPersonaListByPasportRequest;
import io.spring.guides.gs_producing_web_service.GetPersonaListByUnzrRequest;
import io.spring.guides.gs_producing_web_service.GetPersonaListResponse;
import io.spring.guides.gs_producing_web_service.GetPersonaRequest;
import io.spring.guides.gs_producing_web_service.GetPersonaResponse;
import io.spring.guides.gs_producing_web_service.UpdatePersonaRequest;
import io.spring.guides.gs_producing_web_service.UpdatePersonaResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

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
	public GetPersonaListResponse getPersona(@RequestPayload GetPersonaRequest request) {
            System.out.println("WebService: Get persona by RNOKPP "+request.getRnokpp());
            System.out.println(""+request.toString());
            return (GetPersonaListResponse) service.find(request.getRnokpp());
	}

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonaListRequest")
	@ResponsePayload
	public GetPersonaListResponse getPersonaList() {
            System.out.println("WebService: List persons");
            return (GetPersonaListResponse) service.showAllPersons();
	}
        
        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonaListByFirstNameRequest")
	@ResponsePayload
	public GetPersonaListResponse getPersonaListByFirstName(@RequestPayload GetPersonaListByFirstNameRequest request) {
            System.out.println("WebService: getPersonaListByFirstName: "+request.getFirstName());
            return (GetPersonaListResponse) service.findByFirstName(request.getFirstName());
	}

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonaListByLastNameRequest")
	@ResponsePayload
	public GetPersonaListResponse getPersonaListByLastName(@RequestPayload GetPersonaListByLastNameRequest request) {
            System.out.println("WebService: getPersonaListByLastNameRequest: "+request.getLastName());
            return (GetPersonaListResponse) service.findByLastName(request.getLastName());
	}

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonaListByBirthDateRequest")
	@ResponsePayload
	public GetPersonaListResponse getPersonaListByBirthDate(@RequestPayload GetPersonaListByBirthDateRequest request) {
            System.out.println("WebService: GetPersonaListByBirthDateRequest: "+request.getBirthDate());
            return (GetPersonaListResponse) service.findByBirthDate(request.getBirthDate());
	}

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonaListByPasportRequest")
	@ResponsePayload
	public GetPersonaListResponse getPersonaListByPasport(@RequestPayload GetPersonaListByPasportRequest request) {
            System.out.println("WebService: GetPersonaListByPasportRequest: "+request.getPasport());
            return (GetPersonaListResponse) service.findByPasport(request.getPasport());
	}

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonaListByUnzrRequest")
	@ResponsePayload
	public GetPersonaListResponse getPersonaListByUnzr(@RequestPayload GetPersonaListByUnzrRequest request) {
            System.out.println("WebService: GetPersonaListByUnzrRequest: "+request.getUnzr());
            return (GetPersonaListResponse) service.findByUnzr(request.getUnzr());
	}
        
        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPersonaRequest")
	@ResponsePayload
	public AddPersonaResponse addPersona(@RequestPayload AddPersonaRequest request) {
            System.out.println("WebService: Add persona");
            return (AddPersonaResponse) service.addPersona(request);
	}

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonaRequest")
	@ResponsePayload
        @Transactional
	public DeletePersonaResponse deletePersona(@RequestPayload DeletePersonaRequest request) {
            System.out.println("WebService: Delete persona");
            return (DeletePersonaResponse) service.deletePersona(request.getRnokpp());
	}

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonaRequest")
	@ResponsePayload
	public UpdatePersonaResponse updatePersona(@RequestPayload UpdatePersonaRequest request) {
            System.out.println("WebService: Update persona");
            return (UpdatePersonaResponse) service.updatePersona(request);
	}

}