package uni.isw.sigconbackend.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uni.isw.sigconbackend.model.Persona;
import uni.isw.sigconbackend.service.PersonaService;

@RestController
@RequestMapping(path="api/v1/personas")
public class PersonaController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PersonaService personaService;
      
    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Persona>> getPersonas() {
            logger.info(">listar");

            List<Persona> listaPersonas = null;
            try {
                    listaPersonas = personaService.getPersonas();
            } catch (Exception e) {
                    logger.error("Unexpected Exception caught.", e);
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            logger.info(">listar");
            return new ResponseEntity<>(listaPersonas, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> getPersona(@RequestBody Optional<Persona> persona) {
            logger.info(">search" +  persona.toString());
            try {
                    persona = personaService.getPersona(persona.get().getId_persona());                    
                    
            } catch (Exception e) {
                    logger.error("Unexpected Exception caught.", e);
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            logger.info(">search" +  persona.toString());
            return new ResponseEntity<>(persona.get(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/agregar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	 																		  
    public ResponseEntity<Persona> agregar(@RequestBody Persona persona){

        logger.info(">agregar: " + persona.toString());        
        try{             
             personaService.saveOrUpdate(persona);
        } catch(Exception e){
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info(">agregar: " + persona.toString()); 
        return new ResponseEntity<>(persona, HttpStatus.OK);
    } 
    
    @RequestMapping(value = "/actualizar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	 																		  
    public ResponseEntity<Persona> actualizar(@RequestBody Persona persona){

        logger.info(">actualizar: " + persona.toString());                
        try{             
             personaService.saveOrUpdate(persona);
        } catch(Exception e){
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info(">actualizar: " + persona.toString());                
        return new ResponseEntity<>(persona, HttpStatus.OK);
    } 
    
    @RequestMapping(value = "/eliminar", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)	 																		  
    public ResponseEntity<Persona> delete(@RequestBody Optional<Persona> persona){

        logger.info(">eliminar: " + persona.toString() );                
        try{
            persona = personaService.getPersona(persona.get().getId_persona());
            if (persona.isPresent())            
                personaService.delete(persona.get().getId_persona());
        } catch(Exception e){
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info(">eliminar: " + persona.toString() );                
        return new ResponseEntity<>(persona.get(), HttpStatus.OK);
    } 
}
