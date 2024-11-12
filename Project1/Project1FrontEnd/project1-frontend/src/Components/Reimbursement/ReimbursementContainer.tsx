import axios from "axios"
import { useEffect, useState } from "react"
import { Button, Col, Container, Nav, Navbar, Row, Toast } from "react-bootstrap"
import { ReimbursementTable } from "./ReimbursementTable"
import { store } from "../../globalData/store"
import { ReimbursementForm } from "./ReimbursementForm"
import { useNavigate } from "react-router-dom"

export const ReimbursementContainer:React.FC = () => {

    
    

    
    const[reimbursements, setReimbursements] = useState<any[]>([])
    const [showSuccess,setShowSuccess] = useState(false);
    const toggleShowSuccess = () => setShowSuccess(!showSuccess);
    const navigate = useNavigate()
    
    useEffect(()=>{
        if(store.loggedInUser.role!="user" && store.loggedInUser.role!="admin"){
            navigate("/")
        }
        else{
        getReimbursementsByUserId()
        }
    }, [reimbursements]) 


    useEffect(()=> {
        toggleShowSuccess()
    },[])

    const getReimbursementsByUserId = async () => {

       
        const response = await axios.get("http://localhost:7777/reimbursements/" + store.loggedInUser.userId)
        .then(

            (response) => {
                setReimbursements(response.data) //data holds the data send in the response body

                console.log(response.data)

            }
        )
        .catch((error)=>{
            
        })
       

        
        
    }

    

    


    return(
        /* TODO: navbar thing? for navigation options etc */
        <Container>
            
            <Col md={6} className="mb-2">
            <Toast show={showSuccess} 
            onClose={toggleShowSuccess}
            delay={5000}
            autohide
            style={{
                position: "absolute",
                top:10,
                right:100,
                zIndex: 9999,
                width:'200px'
            }}>
            <Toast.Header>
                <img src="holder.js/20x20?text=%20" className="rounded me-2" alt="" />
                <strong className = "me-auto">Login successful!</strong>
            </Toast.Header>
            <Toast.Body>Welcome, {store.loggedInUser.firstName}</Toast.Body>
            </Toast>
            </Col>
            <Navbar className="custom-navbar bg-body-tertiary justify-content-between" data-bs-theme="dark">
            <Navbar.Brand>Reimbursements</Navbar.Brand>
            <Navbar.Collapse>
            <Nav className="me-auto">
            <Nav.Link href="/">Home</Nav.Link>
            {store.loggedInUser.role === "admin" && (
                            <Button variant="outline-light" onClick={() => navigate("/users")}>Admin Panel</Button>
                        )}
            </Nav>
            </Navbar.Collapse>
            <Navbar.Text>
            Signed in as: <a href="#login">{store.loggedInUser.username}</a>
            </Navbar.Text>
            </Navbar>

            
            
            <ReimbursementTable reimbursements={reimbursements} setReimbursements ={()=>setReimbursements}></ReimbursementTable>
             

        </Container>
    )

}