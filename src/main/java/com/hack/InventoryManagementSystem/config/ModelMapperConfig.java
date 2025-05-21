package com.hack.InventoryManagementSystem.config;

import com.hack.InventoryManagementSystem.dto.ProductDTO;
import com.hack.InventoryManagementSystem.dto.SupplierDTO;
import com.hack.InventoryManagementSystem.dto.TransactionDTO;
import com.hack.InventoryManagementSystem.dto.UserDTO;
import com.hack.InventoryManagementSystem.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        // Mapeo explícito para evitar errores con relaciones anidadas
        modelMapper.addMappings(new PropertyMap<Transaction, TransactionDTO>() {
            @Override
            protected void configure() {
                map().setProduct(modelMapper.map(source.getProduct(), ProductDTO.class));
                map().setUser(modelMapper.map(source.getUser(), UserDTO.class));
                map().setSupplier(modelMapper.map(source.getSupplier(), SupplierDTO.class));
            }
        });


        return modelMapper;
    }
}
