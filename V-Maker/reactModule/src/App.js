/* Author: Team */

import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AppLayout from "./layouts/AppLayout";
import DashboardLayout from "./layouts/DashboardLayout";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Credits from "./pages/Credits";
import History from "./pages/History";

export default function App() {
    return (
        <Router>
            <Switch>
                <Route exact path="/login">
                    <AppLayout>
                        <Login />
                    </AppLayout>
                </Route>
                <Route exact path="/register">
                    <AppLayout>
                        <Register />
                    </AppLayout>
                </Route>
                <Route exact path="/home">
                    <DashboardLayout>
                        <Home />
                    </DashboardLayout>
                </Route>
                <Route exact path="/credits">
                    <DashboardLayout>
                        <Credits />
                    </DashboardLayout>
                </Route>
                <Route exact path="/history">
                    <DashboardLayout>
                        <History />
                    </DashboardLayout>
                </Route>
                <Route exact path="/">
                    <AppLayout>
                        <Login />
                    </AppLayout>
                </Route>
                <Route exact path="/">
                    <div>404 Page not found.</div>
                </Route>
            </Switch>
        </Router >
    );
}