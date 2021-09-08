import React from 'react';
import { useState } from 'react';
import { Redirect, useHistory } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import axios from 'axios';

export default function Login(){

    let history = useHistory();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async e => {
        e.preventDefault();
        const URL = "https://g0q3skkmei.execute-api.us-east-1.amazonaws.com/default/getuserpassword?username="+email;
        axios.post(URL).then(response => {

            if(response.status == 200){
                console.log(response.data.password)
                if(response.data.upassword == password){
                    alert("Login Successful..")
                    localStorage.setItem('user', response.data.username);
                    localStorage.setItem('email', response.data.email)
                    history.push("/home",email);
                }
                else if(response.data == 'error'){
                    alert("User does not exist!")
                }
                else{
                    alert("Invalid Password")
                }
            }
        })
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
                            <label>Email</label>
                            <input 
                                type="email" 
                                className="form-control" 
                                placeholder="Enter email. Eg:'admin@gmail.com' "
                                id = "email"
                                name = "email"
                                value={email}
                                onChange={({ target }) => setEmail(target.value)} 
                            />
                        </div>

                        <div className="form-group">
                            <label>Password</label>
                            <input 
                                type="password" 
                                className="form-control" 
                                placeholder="Enter password Eg:'admin@123"
                                id = "password"
                                name = "password"
                                value={password}
                                onChange={({ target }) => setPassword(target.value)}
                            />
                        </div>

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
