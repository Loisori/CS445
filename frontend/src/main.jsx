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
const POLogin = lazy(() => import("./pages/login/POLogin.jsx"));
const InvestorLogin = lazy(() => import("./pages/login/InvestorLogin.jsx"));

function App() {
  return (
    <>
      <Router>
        <MainLayout>
          <Routes>
            <Route path="/" />
            <Route path="" />
            <Route path="" />
            <Route path="" />
            <Route path="" />
          </Routes>
        </MainLayout>
      </Router>
    </>
  );
}

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <App />
  </StrictMode>
);
