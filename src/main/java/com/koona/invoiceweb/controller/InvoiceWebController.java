/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koona.invoiceweb.controller;

import com.koona.invoiceweb.form.InvoiceForm;
import com.koona.invoise.core.controller.InvoiceControllerInterface;
import com.koona.invoise.core.entity.Customer;
import com.koona.invoise.core.entity.Invoice;
import com.koona.invoise.core.service.InvoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.annotation.Validated;
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
public class InvoiceWebController {
    
    @Autowired
    private InvoiceServiceInterface invoiceService;

    @PostMapping("/create")
    public String createInvoice(
            @Validated @ModelAttribute InvoiceForm invoiceForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "invoice-create-form";
        }
        Invoice invoice = new Invoice();
        Customer customer = new Customer();
        invoice.setNumber(invoiceForm.getNumber());
        invoice.setOrderNumber(invoiceForm.getOrderNumber());

        customer.setName(invoiceForm.getCustomerName());
        invoice.setCustomer(customer);

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

/*
    @GetMapping("/{id}")
    public String displayInvoice(@PathVariable("id") String number, Model model) {
        System.out.println("La methode displayInvoice a ete invoquee !");
        model.addAttribute("invoice", invoiceService.getInvoiceByNumber(number));
        return "invoice-details";
    }
*/
    @GetMapping("/create-form")
    public String displayInvoiceCreateForm(@ModelAttribute InvoiceForm invoiceForm) {

        return "invoice-create-form";
    }
}
