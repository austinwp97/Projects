import axios from "axios"
import { useEffect, useState } from "react"
import { Button, Container, Navbar, Table } from "react-bootstrap"
import { Link, useLocation, useNavigate } from "react-router-dom"

export const UserReimbursements:React.FC<any> = () => {

    const [reimbursements,setReimbursements] = useState<any[]>([])
    const navigate = useNavigate()
    const location = useLocation();
    const {users} = location.state;

    useEffect( () => {
        getReimbursements()
    },[])

    const getReimbursements = async () =>
    {
        const response = await axios.get("http://localhost:7777/reimbursements")
        .then(
            (response) => {
                setReimbursements(response.data)
                console.log(response.data)
            }
        )
        .catch((error) =>{

        })
    }

    const getUserInfo = (userId: number) => {
        const user = users.find((user:any) => user.userId === userId)
        console.log(user)
        return user ? `${user.firstName} ${user.lastName} (${user.username})` : "Unknown User"
    }

    return(
        <Container>
            <Navbar className = "bg-dark navbar-dark">
            <Button onClick={() =>navigate("/users")}>Back</Button>
            <Link to="/users/reimbursements/status/pending" state={{users}}>
            <Button onClick={() => navigate("/users/reimbursements/status/pending")}>Filter by pending</Button> 
            </Link>
            </Navbar>
            <Table variant="dark" striped bordered hover>
                <thead>
                    <tr>
                        <th>Reimbursement ID</th>
                        <th>Description</th>
                        <th>Amount</th>
                        <th>Status</th>
                        <th>Employee</th>
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
                        </tr>
                    ))}
                </tbody>
            </Table>
        </Container>
    )
}