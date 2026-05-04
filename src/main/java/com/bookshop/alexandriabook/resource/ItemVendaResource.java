package com.bookshop.alexandriabook.resource;


import com.bookshop.alexandriabook.service.ItemVendaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/item")
public class ItemVendaResource {
    final ItemVendaService itemVendaService;

}
