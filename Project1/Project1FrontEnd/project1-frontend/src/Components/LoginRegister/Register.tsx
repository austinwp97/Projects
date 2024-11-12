import axios from "axios"
import { useEffect, useState } from "react"
import { Button, Container, Form } from "react-bootstrap"
import { useNavigate } from "react-router-dom"

export const Register:React.FC = () =>{

    const[user,setUser] = useState({
        username:"",
        password:"",
        firstName:"",
        lastName:""
    })

    const storeValues = (input:any) => {
        const name = input.target.name
        const value = input.target.value

        setUser((user) =>({...user,[name]:value}))

        console.log(user)
    }

    const onNavigate = useNavigate()

    const register = async () => {


         const response = await axios.post('http://localhost:7777/users', user)
        .then(()=>{
            onNavigate("/")
        })
        .catch((error)=>{alert("Failed! " + error.message)})
    }

    return(

        <Container className="my-5 mx-auto">
            <div>
                <h1>New here? Create an Account for free!</h1>
                <div>
                    <Form.Control
                        type="text"
                        placeholder="First Name"
                        name="firstName"
                        onChange={storeValues}
                    />
                </div>
                <div>
                    <Form.Control
                        type="text"
                        placeholder="Last Name"
                        name="lastName"
                        onChange={storeValues}
                    />
                </div>
                <div>
                    <Form.Control
                        type="text"
                        placeholder="username"
                        name="username"
                        onChange={storeValues}
                    />
                </div>
                <div>
                    <Form.Control
                        type="password"
                        placeholder="password"
                        name="password"
                        onChange={storeValues}
                    />
                </div>
    
                <div>
                <Button className="btn-success m-1" onClick={register}>Register</Button>
                <Button  className="btn-dark" onClick={() => onNavigate("/")}>Back</Button>
                </div>
            </div>
        </Container>
    )
}