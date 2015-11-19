package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.ArrayList;


@RestController
public class ConcertController {
  private List<Concert> concerte = new ArrayList<Concert>();
	//int i=4;
	//String artist="";
  ConcertController() {
    Concert p1 = new Concert(1, "50Cent");
    Concert p2 = new Concert(2, "Dr.Dre");
    Concert p3 = new Concert(3, "Fuego");

    concerte.add(p1);
    concerte.add(p2);
    concerte.add(p3);
  }

  @RequestMapping(value="/Concert", method = RequestMethod.GET)
  public List<Concert> index() {
    return this.concerte;
  }
  
  @RequestMapping(value="/Concert/{artist}", method = RequestMethod.POST)
  public ResponseEntity create(@PathVariable("artist") String artist) {
		Concert n=new Concert( concerte.size() +1,artist);
		concerte.add(n);
		
        return new ResponseEntity<Concert>(n, new HttpHeaders(), HttpStatus.OK); 
  }

  @RequestMapping(value="/Concert/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id) {
    for(Concert p : this.concerte) {
      if(p.getId() == id) {
        return new ResponseEntity<Concert>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

    

@RequestMapping(value="/Concert/{id}", method = RequestMethod.PUT)
  public ResponseEntity update( @PathVariable("id") int id) {
	  
	  for(Concert p : this.concerte) {
		if(p.getId() == id) {
			p.setName("GrasuXXL");
        return new ResponseEntity<Concert>(p, new HttpHeaders(), HttpStatus.OK); 
		}
	  }
	return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);	
  }


  @RequestMapping(value="/Concert/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Concert p : this.concerte) {
      if(p.getId() == id) {
        this.concerte.remove(p);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
}