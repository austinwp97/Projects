package com.revature.controllers;


import com.revature.aspects.AdminOnly;
import com.revature.models.InReimbursementDTO;
import com.revature.models.InReimbursementUpdateDTO;
import com.revature.models.OutReimbursement;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin
public class ReimbursementController {

    private ReimbursementService rService;

    public ReimbursementController(ReimbursementService rService) {
        this.rService = rService;
    }

    @AdminOnly
    @GetMapping
    public ResponseEntity<List<OutReimbursement>> getAllReimb(){


        return ResponseEntity.ok(rService.getAllReimbursements());


    }

    @PostMapping()
    public ResponseEntity<Reimbursement> addReimb(@RequestBody InReimbursementDTO inReimbDTO){

        Reimbursement r = rService.addReimbursement(inReimbDTO);

        return ResponseEntity.ok(r);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){

        return ResponseEntity.status(400).body(e.getMessage());
    }

    @AdminOnly
    @PatchMapping("/{reimbId}")
    public ResponseEntity<Reimbursement> updateReimbursementStatus(@PathVariable int reimbId, @RequestBody Map<String,String> statusMap){
        String status = statusMap.get("status");
        return ResponseEntity.status(202).body(rService.updateReimbursementStatus(reimbId,status));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OutReimbursement>> getReimbursementsByStatus(@PathVariable String status){

        return ResponseEntity.ok(rService.getReimbursementsByStatus(status));

    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OutReimbursement>> getReimbursementsByUserId(@PathVariable int userId){

        return ResponseEntity.ok(rService.getReimbursementsByUser(userId));
    }

    @PutMapping()
    public ResponseEntity<OutReimbursement> updateReimbursementById(@RequestBody InReimbursementUpdateDTO inReimb){

        return ResponseEntity.ok(rService.updateReimbursementById(inReimb));
    }


}
