import { useState } from "react"
import { Button, Col, Container, Form, Row, Stack, Toast } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { store } from "../../globalData/store"
import axios from "axios"
import "./Login.css"


export const Login:React.FC = () => {


    const[loginCreds, setLoginCreds] = useState({
        username:"",
        password:""
    })
    
    const navigate = useNavigate()

    const storeValues = (input:any) => {
        
        const name = input.target.name
        const value = input.target.value

        setLoginCreds((loginCreds) => ({...loginCreds, [name]:value}))

    }

    

    const login = async () => {

        const response = await axios.post("http://18.223.168.42:7777/auth",loginCreds)
        .then(

            
            (response) => {

                console.log(response.data)

                store.loggedInUser = response.data

                if(store.loggedInUser.role =="admin"){
                    
                    navigate("/users")
                }
                else{
                    
                    navigate("/reimbursements")
                }
                
            }
        )
        .catch((error) => {
            alert("Login Failed! Please try again.")
        })
    }
    return(
        
        <Container id="homePage">
        
        <h1 id="MainHeader">User Reimbursement System</h1>
            <h3>Please log in:</h3>

            <div className="d-inline-block">
                <Form.Control
                    type="text"
                    placeholder="username"
                    name="username"
                    onChange={storeValues}
                    className="d-inline-block"
                />
            </div>
            <Row></Row>
            <div className="d-inline-block">
                <Form.Control
                    type="password"
                    placeholder="password"
                    name="password"
                    onChange={storeValues}
                />
            </div>
        <Stack direction="horizontal" gap={2}>
        <Button id = "login" className="d-inline-block" onClick={login} >Login</Button>
        <Button id = "register" className="d-inline-block" onClick={()=>navigate("/register")}>Register</Button>
        </Stack>
        </Container>
    )

}