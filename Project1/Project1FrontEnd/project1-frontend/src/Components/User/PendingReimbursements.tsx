import axios from "axios"
import { useEffect, useState } from "react"
import { Button, Container, Navbar, Table } from "react-bootstrap"
import { Link, useNavigate, useLocation } from "react-router-dom"

export const PendingReimbursements:React.FC = () => {

    const [reimbursements,setReimbursements] = useState<any[]>([])
    const location = useLocation();
    const {users} = location.state;


    const navigate = useNavigate()

    useEffect( () => {
        getReimbursements()
    },[reimbursements])

    const getReimbursements = async () =>
    {
        const response = await axios.get("http://18.223.168.42:7777/reimbursements/status/" + "pending")
        .then(
            (response) => {
                setReimbursements(response.data)
            }
        )
        .catch((error) =>{

        })
    }

    const approveReimb = async (reimbId: any) => { 

        const response = await axios.patch(`http://18.223.168.42:7777/reimbursements/${reimbId}`, {status:"approved"}) 
        .catch((error) => { 
            console.error("Error approving reimbursement:", error); 
        })
    }
    const denyReimb = async (reimbId:any) => { 
            
            const response = await axios.patch(`http://18.223.168.42:7777/reimbursements/${reimbId}`, {status:"denied"})
            .catch((error) => { 
                console.error("Error denying reimbursement:", error); 
            })
        }

        const getUserInfo = (userId: number) => {
            console.log(userId)
            const user = users.find((user:any) => user.userId === userId)

            return user ? `${user.firstName} ${user.lastName} (${user.username})` : "Unknown User"
        }
    return(
        <Container>
            <Navbar className="bg-dark navbar-dark">
            <Button onClick={() =>navigate("/users")}>Back</Button>
            <Link to="/users/reimbursements" state={{users}}>
            <Button onClick={() => navigate("/users/reimbursements")}>Show All</Button> 
            </Link>
            </Navbar>
            <Table variant = "dark" striped bordered hover>
                <thead>
                    <tr>
                        <th>Reimbursement ID</th>
                        <th>Description</th>
                        <th>Amount</th>
                        <th>Status</th>
                        <th>Employee</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {reimbursements.map( (reimbursement:any) => (
                        <tr>
                            <td>{reimbursement.reimbursementId}</td>
                            <td>{reimbursement.description}</td>
                            <td>{reimbursement.amount}</td>
                            <td>{reimbursement.status}</td>
                            <td>{getUserInfo(reimbursement.userId)}</td>
                            <td><Button variant="success" onClick={() =>approveReimb(reimbursement.reimbursementId)}>Approve</Button></td>
                            <td><Button variant="danger" onClick={() =>denyReimb(reimbursement.reimbursementId)}>Deny</Button></td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </Container>
    )
}