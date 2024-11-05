package com.revature.DAOs;

import com.revature.models.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReimbursementDAO extends JpaRepository<Integer, Reimbursement> {

}
