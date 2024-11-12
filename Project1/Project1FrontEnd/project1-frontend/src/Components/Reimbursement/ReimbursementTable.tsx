import { useState } from "react";
import { Button, Container, Nav, Navbar, Table } from "react-bootstrap"
import { store } from "../../globalData/store";
import { ReimbursementForm } from "./ReimbursementForm";
import { useNavigate } from "react-router-dom";
import { EditReimbursement } from "./EditReimbursement";
import './ReimbursementContainer.css'

export const ReimbursementTable:React.FC<{reimbursements:any[],setReimbursements:() => void}> = ({reimbursements, setReimbursements}) => {

    const [showForm, setShowForm] = useState(false)
    const navigate = useNavigate()
    const [pendingToggle, setPendingToggle] = useState(false)
    const [editingReimbursement,setEditingReimbursement] = useState(null);
    
    const filteredData = ():any[] => {

        if(pendingToggle){
            { 
                return reimbursements.filter((reimbursement) => String(reimbursement.status).toLowerCase().includes("pending"))
            }
        }
        return reimbursements;
        
    }

    const buttonStyle = { backgroundColor: pendingToggle ? '#20A4F3' : 'red', color: 'white' };

    return(
        <Container>
            
            <Button
            style={buttonStyle} 
            onClick={() => setPendingToggle(!pendingToggle)}
            >
                {pendingToggle ? 'Show all reimbursements' : 'Filter by pending status'}
            </Button>
            <Button onClick= {() => setShowForm(!showForm)}>Add reimbursement</Button>

            
            <Table variant = "dark" striped bordered hover>
                <thead>
                    <tr>
                        <th>Reimbursement ID</th>
                        <th>Description</th>
                        <th>Amount</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {filteredData().map((reimbursement:any) => (
                        <tr>
                        <td>{reimbursement.reimbursementId}</td>
                        <td>{reimbursement.description}</td>
                        <td>{reimbursement.amount}</td>
                        <td>{reimbursement.status}</td>
                        <td><Button onClick={() => setEditingReimbursement(reimbursement)}>Edit</Button></td>
                        </tr>
                    )
                    )}
                </tbody>
            </Table>
            {showForm && <ReimbursementForm reimbursements={reimbursements} setReimbursements={setReimbursements} setShowForm={setShowForm} />} 
            {editingReimbursement && (
                <EditReimbursement
                    reimbursement={editingReimbursement}
                    reimbursements={reimbursements}
                    setReimbursements={setReimbursements}
                    setEditingReimbursement={setEditingReimbursement}
                />
            )}
        </Container>
        
    )







}