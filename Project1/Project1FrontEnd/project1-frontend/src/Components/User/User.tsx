import axios from "axios";
import { useEffect, useState } from "react";
import { Button, Container } from "react-bootstrap";
import { UserTable } from "./UserTable";
import { store } from "../../globalData/store";
import { useNavigate } from "react-router-dom";

export const User:React.FC = () => {

    const[users,setUsers] = useState<any[]>([]);

    useEffect(()=>{
        if(store.loggedInUser.role!="admin"){
            navigate("/")
        }
        else{
        getReimbursements()
        }
    }, [])

    const navigate = useNavigate()

    const getReimbursements = async () => {

        const response = await axios.get("http://localhost:7777/users")
        .then(

            (response) => {
                setUsers(response.data)

                console.log(response.data)
            }
        )
        .catch((error)=>{
            alert("No users found.")
        })



    }
    






    return(

        <Container>
            <UserTable users={users} setUsers={setUsers}></UserTable>
        </Container>
    )







}