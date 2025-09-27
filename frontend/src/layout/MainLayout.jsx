import Header from "../sections/Header";
import Footer from "../sections/Footer";

function MainLayout({ children }) {
  return (
    <div className="main-layout">
      <Header />
      <main>{children}</main>
      <Footer />
    </div>
  );
}

export default MainLayout;
