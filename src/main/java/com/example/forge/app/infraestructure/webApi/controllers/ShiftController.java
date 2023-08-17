package com.example.forge.app.infraestructure.webApi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.forge.app.application.services.ShiftService;
import com.example.forge.app.domain.entities.ShiftEntity;

@RestController
@RequestMapping("/api/shift")
public class ShiftController {
	@Autowired
	private ShiftService shiftService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllStudent() {
		List<ShiftEntity> listShifts = shiftService.getAll();
		HttpStatus responseStatus = HttpStatus.OK;
		String responseMessage = "Turnos encontrados";

		if (listShifts.isEmpty()) {
			responseStatus = HttpStatus.NOT_FOUND;
			responseMessage = "No existen turnos";
		}

		Map<String, Object> response = new HashMap<>();
		response.put("success", responseStatus == HttpStatus.OK);
		response.put("msg", responseMessage);
		response.put("data", listShifts);

		return ResponseEntity.status(responseStatus).body(response);
	}

	@PostMapping
	public ResponseEntity<?> createCourse(@RequestBody ShiftEntity shift) {
		ShiftEntity createdShift = shiftService.create(shift);

		if (createdShift != null) {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.TRUE);
			msg.put("data", createdShift);
			msg.put("status", HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(msg);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("Error al crear el turno"));
			msg.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getCourse(@PathVariable Long id) {
		ShiftEntity shift = shiftService.getById(id);

		if (shift != null) {
			return ResponseEntity.ok().body(shift);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("No se encontró el turno con ID %d", id));
			msg.put("status", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateShift(@PathVariable Long id, @RequestBody ShiftEntity shift) {
		ShiftEntity updatedShift = shiftService.updateById(id, shift);

		if (updatedShift != null) {
			return ResponseEntity.ok().body(updatedShift);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("No se pudo actualizar el turno con ID %d", id));
			msg.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteShift(@PathVariable Long id) {
		boolean deleted = shiftService.deleteById(id);

		if (deleted) {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.TRUE);
			msg.put("msg", String.format("Turno con ID %d eliminado exitosamente", id));
			msg.put("status", HttpStatus.OK.value());
			return ResponseEntity.ok().body(msg);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("No se pudo eliminar el turno con ID %d", id));
			msg.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}
}
