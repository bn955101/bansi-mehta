import React, { Component, useState } from 'react';
import ImageUploader from 'react-images-upload';
import PageHeader from "../components/PageHeader";
import "../styles/button_style.scss";
import "../styles/Home.scss";
import { withRouter } from "react-router";
import axios from "axios";
import Button from 'react-bootstrap/Button';

export class History extends Component {
    getFromS3= () => {
        const URL = "  https://4u8ftjgyv3.execute-api.us-east-1.amazonaws.com/default/gets3bucketdata?user="+localStorage.getItem('user');
        axios.get(URL)
        .then((response) => {
            this.setState({ videos: response.data });
        })
        .catch((error) => {
            console.log(error);
        });
    };

 

    constructor(props) {
        super(props);
        this.state = { videos: [] };
        this.getFromS3();
    }

 

    render() {
        return (
            <div className="page-container to-do-list-container">
                <div className="page-header-container">
                    <PageHeader title="Videos History" subtitle="" />
                </div>
                <div className="page-content-container">
                    <div className="page-content">
                        <p>Below is the video history:</p>
                        {this.state.videos.length > 0 && this.state.videos.map(function(video,index){
                            return <div><p><b>Video {index+1} </b> <iframe src={video}></iframe></p></div>
                        })}
                    </div>
                </div>
            </div>
        )
    }
}

export default withRouter(History);