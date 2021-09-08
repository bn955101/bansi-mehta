import React from 'react';
import PageHeader from "../components/PageHeader";

export default function Dashboard(){

    return (
        <div className="page-container">
            <div className="page-header-container">
                <PageHeader title="Dashboard" subtitle=""/>
            </div>
            <div className="page-content-container">
                {/* page content goes here */}
            </div>
        </div>
        
    )
}