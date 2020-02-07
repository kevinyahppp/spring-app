package com.datajpa.springboot.web.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.datajpa.springboot.web.app.model.entity.Bill;
import com.datajpa.springboot.web.app.model.entity.Client;
import com.datajpa.springboot.web.app.model.entity.ItemBill;
import com.datajpa.springboot.web.app.model.entity.Product;
import com.datajpa.springboot.web.app.model.service.IClientService;

@Controller
@RequestMapping("/bill")
@SessionAttributes("bill")
public class BillController {

	@Autowired
	private IClientService clientService;
	private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/view/{id}")
	public String view(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Bill bill = clientService.fetchByIdWithClientWithItemBillWithProduct(id);//clientService.findBillById(id);
		if (bill == null) {
			flash.addFlashAttribute("error", "Bill invalid");
			return "redirect:/client/list";
		}
		model.addAttribute("bill", bill);
		model.addAttribute("title", "Bill: " + bill.getDescription());
		return "bill/view";
	}

	@GetMapping("/form/{clientId}")
	public String create(@PathVariable(value = "clientId") Long clientId, Model model, RedirectAttributes flash) {
		Client client = clientService.findOne(clientId);
		if (client == null) {
			flash.addFlashAttribute("error", "Client not exist");
			return "redirect:/client/list";
		}
		Bill bill = new Bill();
		bill.setClient(client);
		model.addAttribute("bill", bill);
		model.addAttribute("title", "Create Bill");
		return "bill/form";
	}

	@GetMapping(value = "/load-products/{term}", produces = "application/json")
	public @ResponseBody List<Product> loadProducts(@PathVariable(value = "term") String name) {
		return clientService.findByName(name);
	}

	@PostMapping("/form")
	public String save(@Valid Bill bill, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "quantity[]", required = false) Integer[] quantity, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Create bill");
			return "bill/form";
		}
		if (itemId == null || itemId.length == 0) {
			model.addAttribute("title", "Create bill");
			model.addAttribute("error", "Error: Fill the bill");
			return "bill/form";
		}
		for (int i = 0; i < itemId.length; i++) {
			Product product = clientService.findProductById(itemId[i]);
			ItemBill line = new ItemBill();
			line.setQuantity(quantity[i]);
			line.setProduct(product);
			bill.addItemBill(line);
			log.info("ID: " + itemId[i].toString() + ", quantity: " + quantity[i].toString());
		}
		clientService.saveBill(bill);
		status.setComplete();
		flash.addFlashAttribute("success", "Bill successfully created");
		return "redirect:/client/view/" + bill.getClient().getId();
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Bill bill = clientService.findBillById(id);
		if (bill != null) {
			clientService.deleteBill(id);
			flash.addFlashAttribute("success", "Bill successfully deleted");
			return "redirect:/client/view/" + bill.getClient().getId();
		}
		flash.addFlashAttribute("error", "Bill not deleted");

		return "redirect:/client/list";
	}
}
