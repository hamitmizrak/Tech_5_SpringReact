// rfce
// React
import React from 'react'

//Router
import { Navigate, Route, Routes } from 'react-router-dom'

// Header Main Footer
import HeaderProject from './components/HeaderProject'
import MainProject from './components/MainProject'
import FooterProject from './components/FooterProject'

// login
import Login from './components/login/Login'

//register
import RegisterCreate from './components/register/RegisterCreate'
import RegisterUpdate from './components/register/RegisterUpdate'
import RegisterView from './components/register/RegisterView'
import RegisterList from './components/register/RegisterList'

// user
import User from './components/user/User'


// FUNCTION COMPONENT
function RouterProject() {

    // RETURN
    return (
        <React.Fragment>
              <HeaderProject logo="fa-solid fa-cloud"></HeaderProject>

                {/* ROUTING */}
                {/* dark mode */}
                {/* dark mode:App-header */}
            <div className="container mt-5 App-header">
                <Routes>

                    {/* ROOT */}
                    <Route path="/" element={<MainProject />} />
                    <Route path="/index" element={<MainProject />} />

                    {/* LOGIN */}
                    <Route path="/login" element={<Login />} />

                    {/* REGISTER */}
                    <Route path="/register/create" element={<RegisterCreate />} />
                    <Route path="/register/list" element={<RegisterList />} />
                    <Route path="/register/view/:id" element={<RegisterView />} />
                    <Route path="/register/update/:id" element={<RegisterUpdate />} />

                    {/* USER PAGE */}
                    <Route path="/user" element={<User />} />

                    {/* Bad request */}
                    <Route path={"*"} element={<Navigate to={"/"} />} />

                </Routes>
            </div>

            <FooterProject copy="&copy; Bütün Haklar saklıdır." />
        </React.Fragment>
    ) //end return
} // end function 


// EXPORT
export default RouterProject