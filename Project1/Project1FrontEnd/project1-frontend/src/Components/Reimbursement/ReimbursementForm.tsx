import axios from "axios"
import { useState } from "react"
import { Button, Container, FloatingLabel, Form } from "react-bootstrap"
import { store } from "../../globalData/store"
import "./ReimbursementContainer.css"

export const ReimbursementForm:React.FC<{reimbursements: any[],setReimbursements: (reimbursements: any[]) => void,
    setShowForm: (show: boolean) => void}> = 
({reimbursements, setReimbursements,setShowForm}) =>{

    const[reimbursement,setReimbursement] = useState({
        description:"",
        amount:0,
        userId:store.loggedInUser.userId

    })

    const storeValues = (input:any) => {
        const name = input.target.name
        const value = input.target.value

        setReimbursement((reimbursement) => ({...reimbursement,[name]:value}))
        console.log(reimbursement)
    }

    const addReimbursementsById = async () => {
        try {
        const response = await axios.post("http://18.223.168.42:7777/reimbursements",reimbursement)
        setReimbursements([...reimbursements,response.data]);
        setShowForm(false);
        
        }catch(error){
            alert("Failed to add reimbursement!")

        }
    }
    









    return(
        <div className="styled-div">
            <h4>New reimbursement:</h4>
            <FloatingLabel controlId="floatingAmount" label="Amount" className="mb-3">
                    <Form.Control type="number" placeholder="Amount" name="amount" onChange={storeValues}></Form.Control>
            </FloatingLabel>
            <FloatingLabel controlId="floatingDescription" label="Description" className="mb-3">
                <Form.Control type = "text" placeholder="Description" name="description" onChange={storeValues}></Form.Control>
            </FloatingLabel>
            <Button onClick={addReimbursementsById}>Confirm</Button>
        </div>
    )
}
