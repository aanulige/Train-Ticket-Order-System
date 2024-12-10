package com.example.train_ticket_system.controller;

import com.example.train_ticket_system.dto.Ticket;
import com.example.train_ticket_system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // 预订车票
    @PostMapping("/book")
    public String bookTicket(@RequestBody Ticket ticket) {
        return ticketService.bookTicket(ticket);
    }

    // 取消订单
    @PutMapping("/cancel/{id}")
    public String cancelTicket(@PathVariable Integer id) {
        return ticketService.cancelTicket(id);
    }

    // 其他方法保持不变
}

