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

//login
const Login = lazy(() => import("./pages/login/Login.jsx"));
const Register = lazy(() => import("./pages/login/Register.jsx"));

function App() {
  return (
    <>
      <BrowserRouter>
        <MainLayout>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
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
