package br.com.citrus.ticket.infraestructure.web.ticket.controller;

import java.util.List;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.services.OpenTicketWebForm;
import br.com.citrus.ticket.domain.tickets.vo.webForm.WebFormTicketDTO;
import br.com.citrus.ticket.domain.tickets.vo.webForm.WebFormTicketResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.citrus.ticket.domain.tickets.models.TicketFacebookPost;
import br.com.citrus.ticket.domain.tickets.models.TicketInstagramPost;
import br.com.citrus.ticket.domain.tickets.services.OpenTicketFacebookPost;
import br.com.citrus.ticket.domain.tickets.services.OpenTicketInstagramPost;
import br.com.citrus.ticket.domain.tickets.services.OpenTicketReclameAqui;
import br.com.citrus.ticket.domain.tickets.vo.reclameAqui.ReclameAquiPayload;
import br.com.citrus.ticket.infraestructure.web.ticket.dto.facebook.FbActionsRegisterDto;
import br.com.citrus.ticket.infraestructure.web.ticket.dto.instagram.IgActionsRegisterDto;
import br.com.citrus.ticket.shared.constants.RestConstants;

@RestController
@RequestMapping(value = RestConstants.API_BASE_URI + RestConstants.TICKET_MODULE)
public class TicketController {

	@Autowired
	private OpenTicketFacebookPost openTicketFacebookPost;

	@Autowired
	private OpenTicketReclameAqui openTicketReclameAqui;

	@Autowired
	private OpenTicketInstagramPost openTicketInstagramPost;

	@Autowired
	private OpenTicketWebForm openTicketWebForm;

	@PostMapping(value = "/facebook-post")
	public ResponseEntity<?> openTicketByFacebookPost(@RequestBody FbActionsRegisterDto fbPostDto) {
		try {
			List<TicketFacebookPost> result = openTicketFacebookPost.execute(fbPostDto.getPost().toValueObject(),
					fbPostDto.getClient().toValueObject(), fbPostDto.getComment().toValueObject());

			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping(value = "/instagram-post")
	public ResponseEntity<?> openTicketByInstagramPost(@RequestBody IgActionsRegisterDto igPostDto) {


		try {
			List<TicketInstagramPost> result = openTicketInstagramPost.execute(igPostDto.getPost().toValueObject(),
					igPostDto.getClient().toValueObject(), igPostDto.getComment().toValueObject(), igPostDto.getAction());

			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping(value = "/reclame-aqui")
	public ResponseEntity<?> openTicketByReclameAqui(@RequestBody ReclameAquiPayload payload) {
		try {
			openTicketReclameAqui.execute(payload);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping(value = "/reclame-aqui/update-status")
	public ResponseEntity<?> updateTicketByReclameAqui(@RequestBody ReclameAquiPayload payload) {
		try {
			openTicketReclameAqui.update(payload);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping(value = "/web-form/{webFormId}")
	public ResponseEntity<WebFormTicketResponseDTO> openTicketGeneric(@PathVariable UUID webFormId, @RequestBody WebFormTicketDTO payload) {
		WebFormTicketResponseDTO response = new WebFormTicketResponseDTO();
		return ResponseEntity.status(HttpStatus.CREATED).body(response.mapTicketToResponse(openTicketWebForm.execute(webFormId, payload)));
	}
}
