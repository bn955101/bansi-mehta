import {Container, Nav, Navbar} from "react-bootstrap";
import "../styles/AppNavbar.scss"

export default function AppNavbar(){
    return (
        <Navbar className="app-nav-bar-container">
            <Container>
                <Navbar.Brand className="app-nav-bar-text" href="/">Video Maker</Navbar.Brand>
                <Nav className="ml-auto">
                    <Nav.Link className="app-nav-bar-link" href="login">Login</Nav.Link>
                    <Nav.Link className="app-nav-bar-link" href="register">Register</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    )
}