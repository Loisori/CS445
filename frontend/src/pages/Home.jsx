<<<<<<< HEAD
import { StrictMode, lazy, Suspense } from "react";

const Hero = lazy(() => import("../sections/layouts/Hero"));
const Why = lazy(() => import("../sections/layouts/Why"));
const Ready = lazy(() => import("../sections/layouts/Ready"));

function Home() {
  return (
    <>
      <Hero />
      <Why />
      <Ready />
    </>
=======
import HeroSection from "../sections/layouts/HeroSection";



function Home() {
  return (
    <div>
      <h1 className="my-[3rem] text-center">Bring a creative project to life.</h1>
      <HeroSection />
    </div>
>>>>>>> parent of eda1b61 (update)
  );
}

export default Home;
