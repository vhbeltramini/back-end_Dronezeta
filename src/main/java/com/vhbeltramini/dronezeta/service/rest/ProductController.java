package com.vhbeltramini.dronezeta.service.rest;

import com.vhbeltramini.dronezeta.model.Product;
import com.vhbeltramini.dronezeta.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        super();
        this.repository = repository;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product){
        Product sevedProduct = repository.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id)")
                .buildAndExpand(sevedProduct.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path= "/products/{id}")
    public Product get(@PathVariable Long id) throws Exception {
        return repository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new Exception("Product not found for this id :: " + id));
    }

    @GetMapping("/products")
    public List<Product> getAll(){
        return repository.findAll();
    }

    @DeleteMapping(path= "/products/{id}")
    public Map<String, Boolean> delete(@PathVariable Long id) throws Exception {
        Product product = repository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new Exception("Product not found for this id :: " + id));

        repository.delete(product);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateEmployee(@PathVariable(value = "id") Long id, @Valid @RequestBody Product product) throws Exception {

        product.setId(Math.toIntExact(id));

        final Product updatedProduct = repository.save(product);

        return ResponseEntity.ok(updatedProduct);
    }

}