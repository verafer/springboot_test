package org.fvera.test.springboot.app.controllers;

import static org.springframework.http.HttpStatus.*;

import org.fvera.test.springboot.app.models.Cuenta;
import org.fvera.test.springboot.app.models.TransaccionDto;
import org.fvera.test.springboot.app.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    @ResponseStatus(OK)
    public List<Cuenta> listar() {
        return cuentaService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Cuenta detalle(@PathVariable Long id) {
        return  cuentaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cuenta guardar(@RequestBody Cuenta cuenta) {
        return null;
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferir(@RequestBody TransaccionDto dto){
        cuentaService.transferir(dto.getCuentaOrigenId(),
                dto.getCuentaDestinoId(),
                dto.getMonto(),
                dto.getBancoId());

        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDate.now());
        response.put("status", "OK");
        response.put("mensaje", "Transferencia realizada con éxito!");
        response.put("transaccion", dto);

        return ResponseEntity.ok(response);
    }
}
