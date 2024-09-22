import React from 'react'
import '../Navigationbar/Navigationbar.css'
import Logo from '../../assets/customer_logo.jpg'
import {Link} from 'react-router-dom'
import Employee from '../Employee'

const NavigationBar = () => {
  return (
    <>

<div className="main_container">
                <div className="container">

                        <img src={Logo} alt="" className="logo" />
                                <div className="header">
                                        <a href="#">
                                        <h2>LCMS</h2>
                                        </a>
                                </div>

                        <div className="navcontent">
                                <ul className="nav_list">
                                        <div className="nav_list_items">
                                        <li>
                                                <a href="#">Dashboard</a>
                                        </li>
                        </div>
                        <div className="nav_list_items">
                                <li>
                                        <Link to='/employee'>
                                        <a href="#">Masters</a>
                                        </Link>
                                </li>
                        </div>
                        <div className="nav_list_items">
                                <li>
                                        <a href="#">Transaction</a>
                                </li>
                        </div>
                        <div className="nav_list_items">
                                <li>
                                        <a href="#">ABCD</a>
                                </li>
                        </div>
                        </ul>
                 </div>
        </div>
                <div className="buttoncontainer">
                        <Link to="/employee" className="btn">Employee Master</Link> &nbsp;&nbsp;&nbsp;&nbsp;
                        <Link to="/customer" className="btn">Cell Master</Link> &nbsp;&nbsp;&nbsp;&nbsp;
                        <Link to="/gauge" className="btn">Cell Master</Link> &nbsp;&nbsp;&nbsp;&nbsp;
                        <Link to="/cell" className="btn">Cell Master</Link> &nbsp;&nbsp;&nbsp;&nbsp;
                </div>
</div>

    </>
  )
}

export default NavigationBar