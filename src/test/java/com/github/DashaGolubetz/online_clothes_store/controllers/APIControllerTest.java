package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.dtos.ProductDTO;
import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class APIControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private APIController apiController;

    @Test
    void index() throws Exception {
        final List<ProductDTO> productDTOS = mock(List.class);

        when(productService.findAllAndConvertToDTOS()).thenReturn(productDTOS);

        final List<ProductDTO> actual = apiController.index();

        assertEquals(productDTOS, actual);
    }

    @Test
    void viewProduct() {
        final ProductDTO productDTO = mock(ProductDTO.class);

        when(productService.findByIdAndConvertToDTO(1)).thenReturn(productDTO);

        final ProductDTO actual = apiController.viewProduct(1);

        assertNotNull(actual);
        assertEquals(productDTO, actual);
    }
}