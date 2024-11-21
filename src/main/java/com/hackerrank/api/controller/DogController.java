package com.hackerrank.api.controller;

import com.hackerrank.api.model.Dog;
import com.hackerrank.api.service.DogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/dog")
public class DogController {
  private final DogService dogService;

  @Autowired
  public DogController(DogService dogService) {
    this.dogService = dogService;
  }
  @RequestMapping("/invalid")
  public ResponseEntitiy<Dog> respondToInvalidEndPoints () {
    return new  ResponseEntitiy<>(null, HttpStatus.BAD_REQUEST);
  }
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Dog> getAllDog() {
    return dogService.getAllDog();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
    if (dog.getID() !=null) {
      return new  ResponseEntitiy<>(null, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(dogService.createNewDog(dog), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  //@ResponseStatus(HttpStatus.CREATED)
 public  ResponseEntitiy<Dog> getDogById(@PathVariable Long id) {
    if (id < 1) {
      //return new Dog();
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
   try {
     Dog dog = dogSErvice.getDogById(id);
     return new REsponseEntity<>(dog, HttpStatus.OK);
   }
   catch (Exception e) {
     return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
   }
  }
}
