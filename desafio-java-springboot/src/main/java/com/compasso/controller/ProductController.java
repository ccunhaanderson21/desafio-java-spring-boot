package com.compasso.controller;


import com.compasso.constants.HttpResponseConstants;
import com.compasso.entity.*;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.compasso.interfaces.*;
import com.compasso.error.*;

import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.data.jpa.domain.Specification;

@RestController
@RequestMapping("/products")
public class ProductController {
  
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private List<Product> newList;

    @Autowired
    private ProductRepository _productRepository;
     
	@RequestMapping(value = "/", method = RequestMethod.GET)    
    public  List<Product>  getProducts(){ 
        return _productRepository.findAll(); 
      }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public  ResponseEntity<Object> GetProduct(@PathVariable(value = "id") long id)
    {
        try {
            
        Optional<Product> product = _productRepository.findById(id);
             if(product.isPresent()){
                 
                return ResponseEntity.ok(product);
             }else{
                //log.info(new ReturnErrorMessage(HttpResponseConstants.CODE_ERRO_DADOS_N_LOCALIZADO,HttpResponseConstants.MESSAGE_ERRO));
                //return new ResponseEntity<>( ReturnErrorMessage.this.Returnmessage(),HttpStatus.NOT_FOUND);

                throw new Exception();
                // return ResponseEntity.status(HttpResponseConstants.CODE_ERRO_DADOS_N_LOCALIZADO)
                // .body(new ReturnErrorMessage(HttpResponseConstants.CODE_ERRO_DADOS_N_LOCALIZADO,HttpResponseConstants.MESSAGE_ERRO));
             }
            //return new ResponseEntity<>(HttpResponseConstants.CODE_ERRO_DADOS_N_LOCALIZADO);
            
        } catch (Exception e) {
            
            log.info("Exception "+ e.getMessage());
 
             return ResponseEntity.status(HttpResponseConstants.CODE_ERRO_DADOS_N_LOCALIZADO)
            .body(new ReturnErrorMessage(HttpResponseConstants.CODE_ERRO_DADOS_N_LOCALIZADO,HttpResponseConstants.MESSAGE_ERRO));
      }
    }

    @RequestMapping(value="/search/{q}?{min_price}?{max_price}", method=RequestMethod.GET)
     public List<Product>  search( @RequestParam String q, @RequestParam String min_price, @RequestParam String max_price){
        //return _productRepository.findAll();
        log.info("params: "+min_price + "/"+ max_price);
        Optional<List<Product>> product = Optional.of(_productRepository.findAll());
        return  Product.search(product.get(),min_price,max_price,q);

    }
    @GetMapping("/search2")
    public ResponseEntity<List<Product>> searchForCars(@SearchSpec Specification<Product> specs) {

        log.info(specs.toString());

	    log.info(_productRepository.findAll(Specification.where(specs)).toString());
        return new ResponseEntity<>(_productRepository.findAll(Specification.where(specs)), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public  String  PostProduct( @RequestBody Product newProduct){

        try {
            _productRepository.save(newProduct);
            return HttpStatus.OK.toString();
        } catch (NumberFormatException nfe) {
            return  HttpStatus.NOT_FOUND.toString();
            // return new ResponseEntity<>(ReturnErrorMessage(HttpResponseConstants.CODE_ERRO_VALIDAR_DADOS,HttpResponseConstants.MESSAGE_ERRO_VALIDAR_DADOS),HttpResponseConstants.CODE_ERRO_VALIDAR_DADOS);
        }catch (Exception e) {
            return  HttpStatus.NOT_FOUND.toString();
        // return new ResponseEntity<>(ReturnErrorMessage(HttpResponseConstants.CODE_ERRO_VALIDAR_DADOS,HttpResponseConstants.MESSAGE_ERRO_VALIDAR_DADOS),HttpResponseConstants.CODE_ERRO_VALIDAR_DADOS);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String  PutProduct(@PathVariable(value = "id") long id, @RequestBody Product newProduct)
     {
            Optional<Product> oldProduct = _productRepository.findById(id);
            if(oldProduct.isPresent()){
                oldProduct.map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());            
                  return _productRepository.save(product);
                }) ;

                return  HttpStatus.OK.toString();
            }
            else
                return  HttpStatus.NOT_FOUND.toString();

          //return new ResponseEntity<>(ReturnErrorMessage(HttpResponseConstants.MESSAGE_ERRO,HttpResponseConstants.CODE_ERRO_DADOS_N_LOCALIZADO));
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String>  deleteProduct(@PathVariable Long id) {
      try { 

        Optional<Product> product = _productRepository.findById(id);
        if(product.isPresent()){
            _productRepository.delete(product.get());
            return new ResponseEntity<>("[]",HttpStatus.OK);
        }
        else{
            
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //return new ResponseEntity<>(ReturnErrorMessage(HttpResponseConstants.MESSAGE_ERRO,HttpResponseConstants.CODE_ERRO_DADOS_N_LOCALIZADO).toString());
        }

      }catch (Exception e) {
          
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       // return new ResponseEntity<>(ReturnErrorMessage(HttpResponseConstants.MESSAGE_ERRO,HttpResponseConstants.CODE_ERRO_DADOS_N_LOCALIZADO));
      }
        
    }
}