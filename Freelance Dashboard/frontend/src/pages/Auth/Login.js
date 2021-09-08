import React from 'react';
import { useState } from 'react';
import { Redirect, useHistory } from 'react-router-dom';
import { Button, Form } from 'react-bootstrap';
import registerServices from '../../services/registerServices';

export default function Login(){

    let history = useHistory();

    const [loginData, setLoginData] = useState({
        email: "",
        password: "",
    });

    const [loginErrors, setLoginErrors] = useState({
        emailError: "",
        passwordError: ""
    });

    const handleChange = (e) => {
        let newLogin = {...loginData, [e.target.name]: e.target.value};
        setLoginData(newLogin);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        let valid = true;

        setLoginErrors({
            emailError: "",
            passwordError: ""
        })

        let newLoginError = {...loginErrors};

        if(loginData.email === ""){
            newLoginError.emailError = "Please enter email!!";
            setLoginErrors(newLoginError);
            valid = false;
        }
        else{
            newLoginError.emailError = "";
            setLoginErrors(newLoginError);
        }

        if(loginData.password === ""){
            newLoginError.passwordError = "Please enter Password!!";
            setLoginErrors(newLoginError);
            valid = false;
        }
        else{
            newLoginError.passwordError = "";
            setLoginErrors(newLoginError);
        }

        if(valid === true){
            registerServices.fatchUser(loginData).then((response) => {
                if(response){
                    if(loginData.email === response.Email && loginData.password === response.Password){
                        localStorage.setItem('user_id', response._id);
                        alert("Login Successful!!");
                        history.push("/dashboard");
                    }else{
                        alert("Invalid Password!!"); 
                    }
                }
            }).catch((error) => {
                alert("Login Failed!!");
                console.log("Error:",error)
            })
        }
      };

    return (
        <div>
            <div className="row" > <br /> </div>

            <div className="row" > 
                <div className="col-md-3"></div>
                <div className="col-md-6">
                    <form onSubmit={handleSubmit}>
                        <br />
                        <br />
                        <h3>Log in</h3>

                        <div className="form-group">
                        <Form.Label className="required">Email</Form.Label>
                            <input 
                                type="email" 
                                className="form-control" 
                                placeholder="Please enter email "
                                id = "email"
                                name = "email"
                                onChange={(e) => handleChange(e)} 
                            />
                        </div>
                        <p className="text-danger">{loginErrors.emailError}</p>

                        <div className="form-group">
                        <Form.Label className="required">Password</Form.Label>
                            <input 
                                type="password" 
                                className="form-control" 
                                placeholder="Please enter password"
                                id = "password"
                                name = "password"
                                onChange={(e) => handleChange(e)}
                            />
                        </div>
                        <p className="text-danger">{loginErrors.passwordError}</p>

                        <div className="form-group">
                            <div>
                                <input type="checkbox" id="customCheck1" />
                                <label>Remember me</label>
                            </div>
                        </div>

                        <Button variant="primary" type="submit" className="btn-block">Sign in</Button>
                        <p className="forgot-password text-right">
                            Forgot <a href="#">password?</a>
                        </p>
                    </form>
                </div>    
                <div className="col-md-3"></div>
            </div>
        </div>
    )
}
