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
  );
}

export default Home;
