/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koona.invoiceweb.api;

import com.koona.invoiceweb.form.InvoiceForm;
import com.koona.invoise.core.entity.Customer;
import com.koona.invoise.core.entity.Invoice;
import com.koona.invoise.core.service.InvoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Steve KOUNA
 */
@RestController
@RequestMapping("/invoice")
public class InvoiceResource {
    
    @Autowired
    private InvoiceServiceInterface invoiceService;

    @PostMapping
    public Invoice postInvoice(
            @RequestBody InvoiceForm invoiceForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return null;
        }
        Invoice invoice = new Invoice();
        Customer customer = new Customer();
        invoice.setNumber(invoiceForm.getNumber());
        invoice.setOrderNumber(invoiceForm.getOrderNumber());
        customer.setName(invoiceForm.getCustomerName());
        invoice.setCustomer(customer);

        return invoiceService.create(invoice);
    }
/*
    public InvoiceServiceInterface getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceServiceInterface invoiceService) {
        this.invoiceService = invoiceService;
    }
 */
    @GetMapping
    public Iterable<Invoice> getInvoices() {
        System.out.println("La methode display home a ete invoquee !");
        return  invoiceService.getInvoiceList();
    }

    @GetMapping("/{id}")
    public Invoice getInvoice(@PathVariable("id") String number) {
        System.out.println("La methode displayInvoice a ete invoquee !");
        return invoiceService.getInvoiceByNumber(number);
    }
/*
    @GetMapping("/create-form")
    public String displayInvoiceCreateForm(@ModelAttribute InvoiceForm invoiceForm) {

        return "invoice-create-form";
    }

 */
}
