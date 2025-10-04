<<<<<<< HEAD
import { StrictMode, lazy, Suspense } from "react";

const Hero = lazy(() => import("../sections/layouts/Hero"));
const Why = lazy(() => import("../sections/layouts/Why"));
const Ready = lazy(() => import("../sections/layouts/Ready"));

function ProjectDetails() {
  return (
    <>
      <Hero />
      <Why />
      <Ready />
    </>
  );
}

export default ProjectDetails;
=======
function ProjectDetails() {
  return <div>Project Details Page</div>;
}

export default ProjectDetails;
>>>>>>> parent of eda1b61 (update)
