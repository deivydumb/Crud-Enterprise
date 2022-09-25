package com.LooneyDelelop.ingresosegresos.Service;

import com.LooneyDelelop.ingresosegresos.Entities.Enterprise;
import com.LooneyDelelop.ingresosegresos.Repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;
    private Long id;

    public Enterprise create(Enterprise enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    public List<Enterprise> getAllEnterprises() {     //retorna todos los objetos de enterprise
        return enterpriseRepository.findAll();
    }

    public String delete(Long id) {
        enterpriseRepository.deleteById(id);
        return "Persona eliminada con exito";

    }

    public Optional<Enterprise> findById(Long id) {
        return enterpriseRepository.findById(id);

    }

    public Enterprise getEnterprise(Long id) throws Exception {
        Optional<Enterprise> enterpriseOptional = enterpriseRepository.findById(id);
        if (enterpriseOptional.isPresent()) {
            return enterpriseOptional.get();
        } else {
            throw new Exception("No existe Persona");
        }
    }


    public Optional<Enterprise> patchEnterprise(Enterprise enterprise_param, Long id) throws Exception {
        try {
            Enterprise empresa = getEnterprise(id);

            if (enterprise_param.getPhone() != null) {
                empresa.setPhone(enterprise_param.getPhone());
            }
            if (empresa.getUpdatedAt() != null) {
                empresa.setUpdatedAt(enterprise_param.getUpdatedAt());
            }

            return Optional.ofNullable(create(empresa));

        } catch (Exception e) {
            throw new Exception("Persona no se actualizo, porque no existe");
        }
    }
}



