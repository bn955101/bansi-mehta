/* Author: Vishal Sancheti */

import React from 'react';
import AppNavbar from "../components/AppNavbar";
import {Container} from "react-bootstrap";

// Page Wrapper for Application Pages
const AppLayout =({children}) =>{

    return(
        <>
            <header>
                <AppNavbar/>
            </header>
            <main>
                {children}
            </main>
            <hr/>
            <footer className="container">
                <p>All rights reserved.</p>
            </footer>
        </>
    )
}

export default AppLayout;