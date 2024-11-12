package com.revature.services;

import com.revature.DAOs.ReimbursementDAO;
import com.revature.DAOs.UserDAO;
import com.revature.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReimbursementService {


    private ReimbursementDAO rDAO;
    private UserDAO uDAO;

    @Autowired
    public ReimbursementService(ReimbursementDAO rDAO, UserDAO uDAO) {
        this.rDAO = rDAO;
        this.uDAO = uDAO;

    }

    public List<OutReimbursement> getAllReimbursements(){

        List<OutReimbursement> rOut = new ArrayList<>();
        List<Reimbursement> rs = rDAO.findAll();

        for(Reimbursement r:rs){
            rOut.add(new OutReimbursement(
                    r.getReimbursementId(),
                    r.getDescription(),
                    r.getAmount(),
                    r.getStatus(),
                    r.getUser().getUserId())
            );
        }
        return rOut;
    }

    public Reimbursement addReimbursement(InReimbursementDTO inR){

        Reimbursement newReimbursement = new Reimbursement(0,inR.getDescription(),inR.getAmount(),"pending",null);

        User r = uDAO.findByUserId(inR.getUserId());



            newReimbursement.setUser(r);

            return rDAO.save(newReimbursement);


    }

    public Reimbursement updateReimbursementStatus(int reimbId, String status){


        Reimbursement r = rDAO.findById(reimbId).orElseThrow( () -> new IllegalArgumentException("No reimbursement found with that ID: "));

        r.setStatus(status);

        rDAO.save(r);

        return r;
    }

    public List<OutReimbursement> getReimbursementsByStatus(String status){

        List<OutReimbursement> outRs = new ArrayList<>();

        List<Reimbursement> reimbursements = rDAO.findByStatus(status);

        if(reimbursements.isEmpty()){
            throw new IllegalArgumentException("No reimbursements found with status: " + status);
        }
        else{
            for(Reimbursement r:reimbursements){
                outRs.add(new OutReimbursement(r.getReimbursementId(),
                        r.getDescription(),
                        r.getAmount(),
                        r.getStatus(),
                        r.getUser().getUserId()));
            }
        }

        return outRs;
    }

    public List<OutReimbursement> getReimbursementsByUser(int userId){

        List<OutReimbursement> outRs = new ArrayList<>();

        List<Reimbursement> userReimbursements = rDAO.findByUserUserId(userId);


            for(Reimbursement r:userReimbursements){
                outRs.add(new OutReimbursement(r.getReimbursementId(),
                        r.getDescription(),
                        r.getAmount(),
                        r.getStatus(),
                        r.getUser().getUserId()));
            }


        return outRs;
    }

    public OutReimbursement updateReimbursementById(InReimbursementUpdateDTO reimb){



        Reimbursement r = rDAO.findByReimbursementId(reimb.getReimbursementId());

        r.setReimbursementId(reimb.getReimbursementId());
        r.setAmount(reimb.getAmount());
        r.setDescription(reimb.getDescription());

        rDAO.save(r);

        return new OutReimbursement(r.getReimbursementId(),
                r.getDescription(),
                r.getAmount(),
                r.getStatus(),
                r.getUser().getUserId());
    }


}
