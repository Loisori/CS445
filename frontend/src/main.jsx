import { StrictMode, lazy, Suspense } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

// const Header = lazy(() => import("./sections/Header.jsx"));

function App() {
  return <>{/* <example /> */}</>;
}

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <App />
  </StrictMode>
);
