import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function Login() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await fetch("http://localhost:8080/api/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (res.ok) {
        const data = await res.json();

        // Save token or user info in localStorage/sessionStorage
        localStorage.setItem("token", data.token);

        alert("Đăng nhập thành công!");
        navigate("/"); // redirect to homepage (or dashboard)
      } else {
        const error = await res.json();
        alert(error.message || "Đăng nhập thất bại!");
      }
    } catch (err) {
      console.error("Error:", err);
      alert("Lỗi kết nối server!");
    }
  };

  return (
    <section className="py-[20rem]!">
      <div className="min-w-[60rem] min-h-[5rem] w-fit m-auto bg-secondary py-[3rem] px-[4rem] rounded-[2rem]">
        <form onSubmit={handleSubmit}>
          <h2 className="mb-[3rem] text-center font-bold">Đăng nhập</h2>

          <div className="mb-[2rem]">
            <label htmlFor="email" className="text-h3">
              Email
            </label>
            <input
              id="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-[#699272]"
              type="email"
              placeholder="your@gmail.com"
              required
            />
          </div>

          <div className="mb-[2rem]">
            <label htmlFor="password" className="text-h3">
              Mật khẩu
            </label>
            <input
              id="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-[#699272]"
              type="password"
              placeholder="Nhập mật khẩu"
              required
            />
          </div>

          <div>
            <button
              type="button"
              className="text-tertiary text-h4"
              id="js-forget-password"
              onClick={() => alert("Chức năng quên mật khẩu chưa được hỗ trợ")}
            >
              Quên mật khẩu?
            </button>
            <br />
            <button
              className="button--primary w-full! mb-[2rem]"
              id="js-login"
              type="submit"
            >
              Đăng nhập
            </button>
            <Link to="/register" className="text-center">
              <p className="text-h5">
                Bạn chưa có tài khoản?
                <span className="text-tertiary!"> Đăng ký ngay</span>
              </p>
            </Link>
          </div>
        </form>
      </div>
    </section>
  );
}

export default Login;