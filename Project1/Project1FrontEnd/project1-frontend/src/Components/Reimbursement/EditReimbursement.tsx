import axios from "axios";
import { useState, useEffect } from "react";
import { Button, Form } from "react-bootstrap";

export const EditReimbursement: React.FC<{
    reimbursement?: any,
    reimbursements: any[],
    setReimbursements: (reimbursements: any[]) => void,
    setEditingReimbursement?: (reimbursement: any) => void
}> = ({ reimbursement, reimbursements, setReimbursements, setEditingReimbursement }) => {
    const [formData, setFormData] = useState({
        reimbursementId: reimbursement?.reimbursementId || '',
        description: reimbursement?.description || '',
        amount: reimbursement?.amount || '',
        status: reimbursement?.status || '',
        userId: reimbursement?.userId || ''
    });

    useEffect(() => {
        if (reimbursement) {
            setFormData({
                reimbursementId: reimbursement.reimbursementId,
                description: reimbursement.description,
                amount: reimbursement.amount,
                status: reimbursement.status,
                userId: reimbursement.userId
            });
            
        }
    }, [reimbursement]);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (reimbursement) {
            try {
                // Send PUT request to update the reimbursement
                const response = await axios.put(`http://18.223.168.42:7777/reimbursements`, formData);
                console.log(response.data);

                // Update the local state with the updated reimbursement
                const updatedReimbursements = reimbursements.map(r => 
                    r.reimbursementId === reimbursement.reimbursementId ? response.data : r
                );
                setReimbursements(updatedReimbursements);
                if (setEditingReimbursement) setEditingReimbursement(null);
            } catch (error) {
                console.error("Error updating reimbursement:", error);
                alert("Failed to update reimbursement. Please try again.");
            }
        } else {
            // Add new reimbursement
            setReimbursements([...reimbursements, formData]);
        }
    };

    const formContainerStyle = {
        backgroundColor: '#FFCF9D', // Light grey background
        padding: '20px',
        borderRadius: '8px',
        boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)'
    };

    return (
        <div style={formContainerStyle}>
            <h3>Edit</h3>
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="reimbursementId">
                    <Form.Label>Reimbursement ID</Form.Label>
                    <Form.Control
                        type="text"
                        name="reimbursementId"
                        value={formData.reimbursementId}
                        onChange={handleChange}
                        readOnly={!!reimbursement}
                    />
                </Form.Group>
                <Form.Group controlId="description">
                    <Form.Label>Description</Form.Label>
                    <Form.Control
                        type="text"
                        name="description"
                        value={formData.description}
                        onChange={handleChange}
                    />
                </Form.Group>
                <Form.Group controlId="amount">
                    <Form.Label>Amount</Form.Label>
                    <Form.Control
                        type="number"
                        name="amount"
                        value={formData.amount}
                        onChange={handleChange}
                    />
                </Form.Group>
                <Button variant="primary" type="submit">
                    {reimbursement ? 'Save Changes' : 'Add Reimbursement'}
                </Button>
            </Form>
        </div>
    );
};