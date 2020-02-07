package com.datajpa.springboot.web.app.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.datajpa.springboot.web.app.model.entity.Client;
import com.datajpa.springboot.web.app.model.service.IClientService;
import com.datajpa.springboot.web.app.model.service.IUploadFileService;
import com.datajpa.springboot.web.app.util.paginator.PageRender;

@Controller
@RequestMapping("/client")
@SessionAttributes("client")
public class ClientController {

	@Autowired
	private IClientService clientService;
	@Autowired
	private IUploadFileService uploadFileService;
	private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> viewPic(@PathVariable String filename) {

		Resource resource = null;

		try {
			resource = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Client client = clientService.findOne(id);
		if (client == null) {
			flash.addFlashAttribute("error", "Client no exist");
			return "redirect:/client/list";
		}
		model.addAttribute("client", client);
		model.addAttribute("title", "Client detail: " + client.getName() + " " + client.getLastName());
		return "view";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Client> clients = clientService.findAll(pageRequest);
		PageRender<Client> pageRender = new PageRender<>("/client/list", clients);
		model.addAttribute("title", "Client list");
		// model.addAttribute("clients", clientService.findAll());
		model.addAttribute("clients", clients);
		model.addAttribute("page", pageRender);
		return "list";
	}

	@GetMapping(value = "/form")
	public String create(Map<String, Object> model) {
		Client client = new Client();
		model.put("title", "Form Client");
		model.put("client", client);
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String save(@Valid Client client, BindingResult bindingResult, Model model,
			@RequestParam("file") MultipartFile pic, RedirectAttributes flash, SessionStatus status) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Form Client");
			return "form";
		}

		if (!pic.isEmpty()) {
			if (client.getId() != null && client.getId() > 0 && client.getPic() != null
					&& client.getPic().length() > 0) {
				uploadFileService.delete(client.getPic());
			}

			String uniqueFileName = null;

			try {
				uniqueFileName = uploadFileService.copy(pic);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Upload successfully " + uniqueFileName);
			client.setPic(uniqueFileName);
		}

		String messageFlash = (client.getId() != null) ? "Client edit success" : "Client save success";

		clientService.save(client);
		status.setComplete();
		flash.addFlashAttribute("success", messageFlash);
		return "redirect:list";
	}

	@RequestMapping(value = "/form/{id}")
	public String edit(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Client client = null;
		if (id > 0) {
			client = clientService.findOne(id);
			if (client == null) {
				flash.addFlashAttribute("error", "Client ID not exist");
				return "redirect:/client/list";
			}
		} else {
			flash.addFlashAttribute("error", "Client ID cannot be 0");
			return "redirect:/client/list";
		}
		model.addAttribute("client", client);
		model.addAttribute("title", "Edit Client");

		return "form";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Client client = clientService.findOne(id);

			clientService.delete(id);

			if (uploadFileService.delete(client.getPic())) {
				flash.addFlashAttribute("info", "Pic " + client.getPic() + " successfully deleted");
			}
		}
		return "redirect:/client/list";
	}
}
