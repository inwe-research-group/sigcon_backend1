package uni.isw.sigconbackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    //@GetMapping("/all")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> getPersonas(){
        return personaService.getPersonas();        
    }
    
    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Persona>> getPersonas1() {
            logger.info("> getPersonas [Persona]");

            List<Persona> list = null;
            try {
                    list = personaService.getPersonas();

                    if (list == null) {
                            list = new ArrayList<>();
                    }
            } catch (Exception e) {
                    logger.error("Unexpected Exception caught.", e);
                    return new ResponseEntity<>(list, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            logger.info("< getPersonas [Persona]");
            return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> getPersona(@PathVariable("id") Long id) {
            logger.info("> getPersona [Persona]");

            Optional<Persona> persona = null;
            try {
                    persona = personaService.getPersona(id);
                    if (!persona.isPresent())
                        persona.toString();
                            
                    logger.info(persona.get().getNombres());
                    
            } catch (Exception e) {
                    logger.error("Unexpected Exception caught.", e);
                    return new ResponseEntity<>(persona.get(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            logger.info("< getPersona [Persona]");
            return new ResponseEntity<>(persona.get(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/actualizar/{id}/{nombres}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	 																		  
    public void saveOrUpdate(@PathVariable("id") Long id, @PathVariable("nombres") String nombres
            ){

        logger.info("> actualizar: " + id + " " + nombres);
        Integer response = 0;
        Persona persona=null;
        try{
             persona=new Persona(id,nombres);
             personaService.saveOrUpdate(persona);
        } catch(Exception e){
            logger.error("Unexpected Exception caught. "+ e.getMessage());
        }

    } 
    
    @RequestMapping(value = "/agregar/{apellido_paterno}/{apellido_materno}/{nombres}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	 																		  
    public void saveOrUpdate(@PathVariable("apellido_paterno") String apellido_paterno,
            @PathVariable("apellido_materno") String apellido_materno,
            @PathVariable("nombres") String nombres
            ){

        logger.info("> agregar: " + apellido_materno + " "+ apellido_paterno+ " "+nombres);
        Integer response = 0;
        Persona persona=null;
        try{
             persona=new Persona(apellido_paterno,apellido_materno,nombres);
             personaService.saveOrUpdate(persona);
        } catch(Exception e){
            logger.error("Unexpected Exception caught. "+ e.getMessage());
        }

    } 
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)	 																		  
    public void delete(@PathVariable("id") Long id){

        logger.info("> delete: " + id );        
        Optional<Persona> persona = null;
        try{
            persona = personaService.getPersona(id);
            if (persona.isPresent())            
                personaService.delete(id);
        } catch(Exception e){
            logger.error("Unexpected Exception caught. "+ e.getMessage());
        }

    } 
}
