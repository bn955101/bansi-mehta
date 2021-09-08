import '../styles/style.scss';
import React, { useState } from 'react';
import {Collapse} from 'react-bootstrap';
import { Drawer } from 'react-bootstrap-drawer';
import '../styles/DashboardSidebar.scss';
import { useLocation } from 'react-router-dom'

export default function Sidebar(props) {
    const location = useLocation();
    const [open, setOpen] = useState(false);
    const handleToggle = () => setOpen(!open);

    return (
        <Drawer { ...props }>
            <Drawer.Toggle onClick={ handleToggle } />

            <Collapse in={ open }>
                <Drawer.Overflow>
                    <Drawer.ToC>
                        

                        <Drawer.Nav>
                            <Drawer.Item href="/home">
                                <span className={location.pathname === "/home" ? 'nav-item-active' : ''}>Create Videos</span>
                            </Drawer.Item>
                            <Drawer.Item  href="/history">
                                <span className={location.pathname === "/history" ? "nav-item-active" : ''}>History</span>
                            </Drawer.Item>
                            <Drawer.Item href="/credits">
                                <span className={location.pathname === "/credits" ? 'nav-item-active' : ''}>Add Credits</span>
                            </Drawer.Item>
                        </Drawer.Nav>
                    </Drawer.ToC>
                </Drawer.Overflow>
            </Collapse>
        </Drawer>
    );
};