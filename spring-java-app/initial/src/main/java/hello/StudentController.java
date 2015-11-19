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
public class StudentController {
  private List<Student> studenti = new ArrayList<Student>();
	//int i=4;
	//String nume="";
  StudentController() {
    Student p1 = new Student(1, "Ana");
    Student p2 = new Student(2, "Inocentiu");
    Student p3 = new Student(3, "Indra");

    studenti.add(p1);
    studenti.add(p2);
    studenti.add(p3);
  }

  @RequestMapping(value="/Student", method = RequestMethod.GET)
  public List<Student> index() {
    return this.studenti;
  }
  
  @RequestMapping(value="/Student/{nume}", method = RequestMethod.POST)
  public ResponseEntity create(@PathVariable("nume") String nume) {
		Student n=new Student( studenti.size() +1,nume);
		studenti.add(n);
		
        return new ResponseEntity<Student>(n, new HttpHeaders(), HttpStatus.OK); 
  }

  @RequestMapping(value="/Student/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id) {
    for(Student p : this.studenti) {
      if(p.getId() == id) {
        return new ResponseEntity<Student>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

    

@RequestMapping(value="/Student/{id}", method = RequestMethod.PUT)
  public ResponseEntity update( @PathVariable("id") int id) {
	  
	  for(Student p : this.studenti) {
		if(p.getId() == id) {
			p.setName("Nicusor");
        return new ResponseEntity<Student>(p, new HttpHeaders(), HttpStatus.OK); 
		}
	  }
	return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);	
  }


  @RequestMapping(value="/Student/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Student p : this.studenti) {
      if(p.getId() == id) {
        this.studenti.remove(p);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
}