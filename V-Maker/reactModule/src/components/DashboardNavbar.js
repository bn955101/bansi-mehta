import {Container, Nav, Navbar} from "react-bootstrap";
import "../styles/DashboardNavbar.scss";

export default function DashboardNavbar(){

    const handleSession = () => {
        localStorage.removeItem('user')
        localStorage.removeItem('email')
    }
    return (
        <Navbar className="nav-bar-container">
            <Container>
                <Navbar.Brand className="nav-bar-text" href="/">Video Maker</Navbar.Brand>
                <Nav className="ml-auto">
                    <Nav.Link title="Log Out" className="nav-bar-link" onClick={ handleSession } href="/">
                        <i className="fas fa-sign-out-alt"/>
                    </Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    )
}