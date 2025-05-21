package com.hack.InventoryManagementSystem.config;

import com.hack.InventoryManagementSystem.dto.ProductDTO;
import com.hack.InventoryManagementSystem.dto.SupplierDTO;
import com.hack.InventoryManagementSystem.dto.TransactionDTO;
import com.hack.InventoryManagementSystem.dto.UserDTO;
import com.hack.InventoryManagementSystem.entity.Product;
import com.hack.InventoryManagementSystem.entity.Supplier;
import com.hack.InventoryManagementSystem.entity.Transaction;
import com.hack.InventoryManagementSystem.entity.User;
import org.modelmapper.Converter;
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

        // Converter para Product
        Converter<Product, ProductDTO> productConverter = ctx -> modelMapper.map(ctx.getSource(), ProductDTO.class);
        Converter<User, UserDTO> userConverter = ctx -> modelMapper.map(ctx.getSource(), UserDTO.class);
        Converter<Supplier, SupplierDTO> supplierConverter = ctx -> modelMapper.map(ctx.getSource(), SupplierDTO.class);

        modelMapper.addMappings(new PropertyMap<Transaction, TransactionDTO>() {
            @Override
            protected void configure() {
                using(productConverter).map(source.getProduct()).setProduct(null);
                using(userConverter).map(source.getUser()).setUser(null);
                using(supplierConverter).map(source.getSupplier()).setSupplier(null);
            }
        });

        return modelMapper;
    }

}
