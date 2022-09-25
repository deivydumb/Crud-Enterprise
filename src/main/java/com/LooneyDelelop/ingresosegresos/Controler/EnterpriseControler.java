package com.LooneyDelelop.ingresosegresos.Controler;

import com.LooneyDelelop.ingresosegresos.Entities.Enterprise;
import com.LooneyDelelop.ingresosegresos.Service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/")
public class EnterpriseControler {

    @Autowired
    private EnterpriseService enterpriseService;


    //Llamado a todas las empresas
    @GetMapping("enterprises")
    private ResponseEntity<List<Enterprise>> getEnterprises() {
        return ResponseEntity.ok(enterpriseService.getAllEnterprises());
    }

    //Llamar una empresa

    @GetMapping("enterprises/{id}")
    private ResponseEntity<Enterprise> getEnterprise(@PathVariable("id") Long id) {
        try {
            Enterprise enterprise = enterpriseService.getEnterprise(id);
            return ResponseEntity.ok(enterpriseService.getEnterprise(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /*
    private ResponseEntity<Optional<Enterprise>> getEnterprise (@PathVariable ("id") Long id){
        return ResponseEntity.ok(enterpriseService.findById(id));
    }*/


    //Crear una empresa

    @PostMapping("enterprises")
    private ResponseEntity<Enterprise> postEnterprise(@RequestBody Enterprise enterprise) {
        Enterprise enterprise1 = enterpriseService.create(enterprise);
        try {
            return ResponseEntity.created(new URI("/enterprises" + enterprise1.getId())).body(enterprise1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    //DBorrar una empresa

    @DeleteMapping("enterprises/{id}")
    private ResponseEntity<Void> deleteEnterprise(@PathVariable Long id) {
        enterpriseService.delete(id);
        return ResponseEntity.ok().build();
    }


    //Actualizar

    @PatchMapping("enterprises/{id}")
    public ResponseEntity<Enterprise> patch(@RequestBody Enterprise enterprise, @PathVariable Long id) {
        try {
            Optional <Enterprise> enterprise2 = enterpriseService.patchEnterprise(enterprise, id);
            return ResponseEntity.ok(enterprise2.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}



