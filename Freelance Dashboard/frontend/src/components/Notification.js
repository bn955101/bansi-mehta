import "../styles/DashboardNavbar.scss";
import {Dropdown, Modal} from "react-bootstrap";
import React,  {Component} from 'react';
import NotificationService from "../services/notificationService"
import { withRouter } from "react-router";
import moment from "moment";

class Notification extends Component
{
    constructor(props) {
        super(props);

        this.state = {
            values: [],
            value: {},
            notificationShow: false
        };

        this.userId = localStorage.getItem("user_id");
        this.currentDate = moment().format('YYYY-MM-DD')

    }

    fetchNotifications(){
        // var today = new Date();
        // var dd = String(today.getDate()).padStart(2, '0');
        // var mm = String(today.getMonth() + 1).padStart(2, '0'); 
        // var yyyy = today.getFullYear();
        // today = yyyy + '-' + mm + '-' + dd;
        NotificationService.fetchNotifications({"currentDate": this.currentDate, "userId": this.userId}).then((response) => {
            let notifications = [];
            response.data.forEach(element => {
                let item = {};
                item.eventName = element.eventName;
                item.category = element.category;
                item.viewStatus = element.viewStatus;
                item.className = item.viewStatus ? "" : 'text-primary';
                notifications.push(item)
            });
            this.setState({
                values: notifications
            });
        }).catch((error) => {
            console.log("Error")
        })
    }

    componentDidMount() {
        this.fetchNotifications();
    }

    handleNotificationClose(){
        this.setState({notificationShow:false});
    };
    handleNotificationShow(){
        this.setState({notificationShow:true});
    };

    openNotification = (value) => {
        this.setState({value:value});
        NotificationService.setStatus({"currentDate": this.currentDate, "value": value}).then((response) => {
            this.fetchNotifications();
            this.handleNotificationShow();
        }).catch((error) => {
            console.log("Error")
        });
        
        

    };

    render() {
        return (
            <div>
                <Dropdown>
                    <Dropdown.Toggle title="Notification" id="bellIcon">
                        <i className="fas fa-bell"/>
                    </Dropdown.Toggle>

                    <Dropdown.Menu id="notificationPanel" >
                        {this.state.values.map((value,index) => {
                            return <Dropdown.Item className="border-bottom" onClick = { () => this.openNotification(value)}>
                                <span className={value.className} >{value.eventName}</span>
                            </Dropdown.Item>
                        })}
                    </Dropdown.Menu>
                </Dropdown>
                <Modal show={this.state.notificationShow} onHide={() => this.handleNotificationClose()}>
                    <Modal.Header closeButton>
                        <Modal.Title>{this.state.value.category}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        {this.state.value.eventName}
                    </Modal.Body>
                </Modal>
            </div>
        )
    }
}

export default withRouter(Notification)
