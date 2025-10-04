import { StrictMode, lazy, Suspense } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import MainLayout from "./layout/mainlayout.jsx";
//
const Header = lazy(() => import("./sections/Header.jsx"));
const Footer = lazy(() => import("./sections/Footer.jsx"));

//pages
const Home = lazy(() => import("./pages/Home.jsx"));
<<<<<<< HEAD
const Project = lazy(() => import("./pages/Projects.jsx"));

//login
const Login = lazy(() => import("./pages/login/Login.jsx"));
const Register = lazy(() => import("./pages/login/Register.jsx"));
=======

//login
const POLogin = lazy(() => import("./pages/login/POLogin.jsx"));
const InvestorLogin = lazy(() => import("./pages/login/InvestorLogin.jsx"));
>>>>>>> parent of eda1b61 (update)

function App() {
  return (
    <>
      <BrowserRouter>
        <MainLayout>
          <Routes>
            <Route path="/" element={<Home />} />
<<<<<<< HEAD
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/project" element={<Project />} />
=======
            <Route path="/investorlogin" element={<InvestorLogin />} />
            <Route path="/POlogin" element={<POLogin />} />
>>>>>>> parent of eda1b61 (update)
          </Routes>
        </MainLayout>
      </BrowserRouter>
    </>
  );
}

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <App />
  </StrictMode>
);
