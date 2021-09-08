import React from 'react';
import { useState } from 'react';
import { useHistory } from 'react-router-dom';
import { Button, Form } from 'react-bootstrap';
import axios from 'axios';

export default function Register(){
    // const bcrypt = require('bcrypt');

    let history = useHistory();

    const checkEmail = /(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    const checkPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/;

    const [registrationInfo, setRegistrationInfo] = useState({
        name: '',
        email: '',
        password: '',
        confirmPassword: ''
    });

    const [registrationErrors, setRegistrationErrors] = useState({
        nameError: '',
        emailError: '',
        passwordError: '',
        confirmPasswordError: ''
    });

    const handleChange = (e) => {
        let newRegistration = {...registrationInfo, [e.target.name]: e.target.value};
        setRegistrationInfo(newRegistration);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        let valid = true;

        setRegistrationErrors({
            nameError: '',
            emailError: '',
            passwordError: '',
            confirmPasswordError: ''
        })

        let newRegError = {...registrationErrors};

        if(registrationInfo.name === ""){
            newRegError.nameError = "Please enter name!!";
            setRegistrationErrors(newRegError);
            valid = false;
        }
        else{
            newRegError.nameError = "";
            setRegistrationErrors(newRegError);
        }

        if(registrationInfo.email === ""){
            newRegError.emailError = "Please enter email!!";
            setRegistrationErrors(newRegError);
            valid = false;
        }
        else{
            if(!checkEmail.test(registrationInfo.email)){
                newRegError.emailError = "Email Invalid!!";
                setRegistrationErrors(newRegError);
                valid = false;
            }
            else{
                newRegError.emailError = "";
                setRegistrationErrors(newRegError);
            }
        }
        

        if(registrationInfo.password === ""){
            newRegError.passwordError = "Please enter password!!";
            setRegistrationErrors(newRegError);
            valid = false;
        }
        else{
            if(!checkPassword.test(registrationInfo.password)){
                newRegError.passwordError = "Password Invalid!!";
                setRegistrationErrors(newRegError);
                valid = false;
            }
            else{
                newRegError.passwordError = "";
                setRegistrationErrors(newRegError);
            }
        }

        if(registrationInfo.confirmPassword === ""){
            newRegError.confirmPasswordError = "Please enter password!!";
            setRegistrationErrors(newRegError);
            valid = false;
        }
        else{
            if((registrationInfo.password) !==  registrationInfo.confirmPassword){
                newRegError.confirmPasswordError = "Password does not match!!";
                setRegistrationErrors(newRegError);
                valid = false;
            }
            else{
                newRegError.confirmPasswordError = "";
                setRegistrationErrors(newRegError);
            }
        }

        
        if(valid === true){
            // const salt = "$2b$10$wWcWzH8u0Kiol1l.1yLjmO";
            // let encryptedPassword = bcrypt.hashSync(registrationInfo.password, salt);
            const URL = "https://c5sg6b51g0.execute-api.us-east-1.amazonaws.com/default/insertuserinfo?username="+ registrationInfo.name +"&password="+registrationInfo.password+"&email="+registrationInfo.email+";";
            axios.post(URL).then(response => {
                if(response.status == 200){
                    alert("Registration Successful..")
                    history.push("/login");
                }
                else{
                    alert("User Exist!")
                }
            })
        }
    }

    return (
        <div>
            <div className="row" > <br /> </div>
            <div className="row" > 
                <div className="col-md-3"></div>
                <div className="col-md-6">
                    <form onSubmit={handleSubmit}>
                        <br />
                        <br />
                        <h3>Register</h3>

                        <div className="form-group">
                            <Form.Label className="required">Name</Form.Label>
                            <input 
                                type="text" 
                                className="form-control" 
                                placeholder="Enter Name"
                                id = "name"
                                name = "name"
                                required="required"
                                onChange={(e) => handleChange(e)} 
                            />
                            <p className="text-danger">{registrationErrors.nameError}</p>
                        </div>

                        <div className="form-group">
                        <Form.Label className="required">Email</Form.Label>
                            <input 
                                type="text" 
                                className="form-control" 
                                placeholder="Enter email"
                                id = "email"
                                name = "email"
                                onChange={(e) => handleChange(e)}
                            />
                            <p className="text-danger">{registrationErrors.emailError}</p>
                        </div>

                        <div className="form-group">
                        <Form.Label className="required">Password</Form.Label>
                            <input 
                                type="password" 
                                className="form-control" 
                                placeholder="Enter password"
                                id = "password"
                                name = "password"
                                onChange={(e) => handleChange(e)}
                            />
                            <p className="text-danger">{registrationErrors.passwordError}</p>
                        </div>

                        <div className="form-group">
                        <Form.Label className="required">Confirm Password</Form.Label>
                            <input 
                                type="password" 
                                className="form-control"
                                name="confirmPassword"
                                id="confirmPassword"
                                placeholder="Confirm your password"
                                onChange={(e) => handleChange(e)}
                            />
                            <p className="text-danger">{registrationErrors.confirmPasswordError}</p>

                        </div>

                        <Button variant="primary" type="submit" className= "btn-block" > Register </Button>
                    </form>
                </div>    
                <div className="col-md-3"></div>
            </div>
        </div>
    )
}