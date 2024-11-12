import axios from "axios";
import { useState } from "react";
import { Button, Container, Nav, Navbar, Table } from "react-bootstrap"
import { Link, useNavigate } from "react-router-dom";
import { store } from "../../globalData/store";


export const UserTable:React.FC<{users: any[],setUsers: (users: any[]) => void}> = ({users,setUsers}) => {

    const navigate = useNavigate()

    const handleDelete = async (userId: number) => 
        { 
        if (window.confirm("Are you sure you want to delete this user?")) { 
            console.log(`User with ID ${userId} deleted.`);
            
            const response = await axios.delete("http://localhost:7777/users/" + userId)
            .then(() => {
                setUsers(users.filter(user => user.userId !== userId))
            })
            .catch()
         } }

    const handlePromote = async (userId:number) =>
    {
        const response = await axios.patch("http://localhost:7777/users/" + userId, {role:"admin"})
        .then((response) =>{
            setUsers(users.map(user => user.userId === userId ? {...user,role:"admin"}:user))
        }
        )
        .catch((error) => {
            console.log(error)
        })
        
    }

         
    return(

        <Container>
            <Navbar expand="lg" bg="dark" className="bg-dark navbar-dark">
            <Navbar.Brand>Admin Dashboard</Navbar.Brand>
            <Navbar.Collapse>
            <Nav>
                <Nav.Link href="/" className="me-3">Home</Nav.Link>
                <Link to="/users/reimbursements" state={{users}} className = "me-3">
                {/*<Button onClick={() => navigate("/users/reimbursements")}>Reimbursement requests</Button> */}
                <Nav.Link href="/users/reimbursments">Reimbursement requests</Nav.Link>
                </Link>
                <Link to="/reimbursements">
                <Nav.Link href="/reimbursments">My reimbursements</Nav.Link>
                </Link>
            </Nav>
            <Nav className = "ms-auto">
            <Navbar.Text>
            Signed in as: <a href="/">{store.loggedInUser.username}</a>
            </Navbar.Text>
            </Nav>
            </Navbar.Collapse>
            
            </Navbar>
            <Table variant = "dark" striped bordered hover>
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {users.map((user:any) => (
                        <tr>
                            <td>{user.userId}</td>
                            <td>{user.username}</td>
                            <td>{user.role}</td>
                            <td>{user.firstName}</td>
                            <td>{user.lastName}</td>
                            <td>
                                {user.role === "user" && (
                                <Button variant="danger"
                            onClick={() => handleDelete(user.userId)} >Delete</Button>
                                )}</td>
                            <td>
                                {user.role === "user" && (
                                <Button onClick={() => handlePromote(user.userId)}>Promote</Button>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </Container>
    )






}