package org.kj6682.library.store.controller;
import org.kj6682.library.store.bean.Item;
import org.kj6682.library.store.bean.Status;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    @RequestMapping("/book")
    public Item book(@RequestParam(value="id", defaultValue="0") long id) {
        return new Item(id, Status.RESERVED);
    }
    
    @RequestMapping("/extend")
    public Item extend(@RequestParam(value="id", defaultValue="0") long id) {
        return new Item(id, Status.LENT);
    }
    
    @RequestMapping("/grant")
    public Item grqnt(@RequestParam(value="id", defaultValue="0") long id) {
        return new Item(id, Status.LENT);
    }
    
    @RequestMapping("/return")
    public Item returnItem(@RequestParam(value="id", defaultValue="0") long id) {
        return new Item(id, Status.AVAILABLE);
    }
    
}//:)