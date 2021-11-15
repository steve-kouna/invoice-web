/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koona.invoiceweb.controller;

import com.koona.invoise.core.controller.InvoiceControllerInterface;
import com.koona.invoise.core.entity.Invoice;
import com.koona.invoise.core.service.InvoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author Steve KOUNA
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceWebController implements InvoiceControllerInterface {
    
    @Autowired
    private InvoiceServiceInterface invoiceService;

    @PostMapping("")
    public String createInvoice(@ModelAttribute Invoice invoice) {

        invoiceService.create(invoice);

        return "invoice-created";
    }

    public InvoiceServiceInterface getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceServiceInterface invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/home")
    public String  displayHome(Model model) {
        System.out.println("La methode display home a ete invoquee !");
        model.addAttribute("invoices", invoiceService.getInvoiceList());

        return "invoice-home";
    }

    @GetMapping("/{id}")
    public String displayInvoice(@PathVariable("id") String number, Model model) {
        System.out.println("La methode displayInvoice a ete invoquee !");
        model.addAttribute("invoice", invoiceService.getInvoiceByNumber(number));
        return "invoice-details";
    }

    @GetMapping("/create-form")
    public String displayInvoiceCreateForm(@ModelAttribute Invoice invoice) {

        return "invoice-create-form";
    }
}
