package com.systempayments.sistema_pagos_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.systempayments.sistema_pagos_back.dto.GeneryDto;
import com.systempayments.sistema_pagos_back.dto.NewPagoDto;
import com.systempayments.sistema_pagos_back.entities.Pago;
import com.systempayments.sistema_pagos_back.enums.PagoStatus;
import com.systempayments.sistema_pagos_back.enums.TypePagos;
import com.systempayments.sistema_pagos_back.repository.PagoReposity;
import com.systempayments.sistema_pagos_back.services.PagoService;
import com.systempayments.sistema_pagos_back.utils.ResponseUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@CrossOrigin("*")
@RequestMapping("/api") // para agrupar rutas opcionalmente
public class PagoController {

    @Autowired
    private PagoReposity pagoReposity;

    @Autowired
    private PagoService pagoService;

    @GetMapping("/pagos")
    public GeneryDto<List<Pago>> listarPagos() {
        return ResponseUtil.success(pagoReposity.findAll(), "Pagos obtenidos exitosamente");
    }

    @GetMapping("/pagos/{id}")
    public GeneryDto<Pago> listarPagoPorId(@PathVariable Long id) {
        return ResponseUtil.success(pagoReposity.findById(id).get(), "Pago obtenido exitosamente");
    }

    @GetMapping("/Estudiante/{codigo}/pagos")
    public GeneryDto<List<Pago>> listarPagosPorCodigoEstudiante(@PathVariable String codigo) {
        return ResponseUtil.success(pagoReposity.findByEstudianteCodigo(codigo), "Pagos obtenidos exitosamente");
    }

    @GetMapping("/pagosPorStatus")
    public GeneryDto<List<Pago>> listarPagosPorStatus(@RequestParam PagoStatus status) {
        return ResponseUtil.success(pagoReposity.findByStatus(status), "Pagos obtenidos exitosamente");
    }

    public GeneryDto<List<Pago>> listarPagosPorType(@RequestParam TypePagos type) {
        return ResponseUtil.success(pagoReposity.findByType(type), "Pagos obtenidos exitosamente");
    }

    @PutMapping("/pagos/{pagoId}/actualizarStatus")
    public GeneryDto<Pago> actualizarPagoPorStutus(@RequestParam PagoStatus status, @PathVariable Long pagoId) {
        return ResponseUtil.success(pagoReposity.findById(pagoId).get(), "Pago obtenido exitosamente");
    }

    @PostMapping(path = "/pagos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GeneryDto<Pago> savePago(@RequestParam(value = "file") MultipartFile file, NewPagoDto newPagoDto)
            throws Exception {
        GeneryDto<Pago> innerResponse = pagoService.savePago(file, newPagoDto);
        return ResponseUtil.success(innerResponse.getData(), "Pago creado exitosamente");
    }

    @GetMapping(value = "/pagos/{pagoId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public GeneryDto<byte[]> listarArhivoPorId(@PathVariable Long pagoId) throws Exception {
        return ResponseUtil.success(pagoService.getArhivoPorId(pagoId), "Archivo obtenido exitosamente");
    }

}
