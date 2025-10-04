import { useState } from "react";
import { Link } from "react-router-dom";

function Register() {
  const [formData, setFormData] = useState({
    fullname: "",
    email: "",
    phone: "",
    accType: "",
    password: "",
    confirmPassword: "",
    agree: false,
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === "checkbox" ? checked : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      alert("Mật khẩu xác nhận không khớp!");
      return;
    }

    if (!formData.agree) {
      alert("Bạn cần đồng ý với Điều khoản sử dụng và Chính sách bảo mật!");
      return;
    }

    try {
      const res = await fetch("http://localhost:8080/api/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          fullname: formData.fullname,
          email: formData.email,
          phone: formData.phone,
          accType: formData.accType,
          password: formData.password,
        }),
      });

      if (res.ok) {
        const data = await res.json();
        alert("Đăng ký thành công!");
        console.log("Response:", data);
      } else {
        const error = await res.json();
        alert(error.message || "Đăng ký thất bại!");
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
          <h2 className="mb-[3rem] text-center font-bold">Đăng ký</h2>

          <div className="mb-[2rem]">
            <label htmlFor="fullname" className="text-h3">
              Họ và tên
            </label>
            <input
              id="fullname"
              name="fullname"
              value={formData.fullname}
              onChange={handleChange}
              className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-[#699272]"
              type="text"
              placeholder="your name"
              required
            />
          </div>

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
            <label htmlFor="phone" className="text-h3">
              Số điện thoại
            </label>
            <input
              id="phone"
              name="phone"
              value={formData.phone}
              onChange={handleChange}
              className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-[#699272]"
              type="number"
              placeholder="0123456789"
              required
            />
          </div>

          <div className="mb-[2rem]">
            <label htmlFor="accType" className="text-h3">
              Loại tài khoản
            </label>
            <select
              id="accType"
              name="accType"
              value={formData.accType}
              onChange={handleChange}
              className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-[#699272]"
              required
            >
              <option value="">Chọn loại tài khoản</option>
              <option value="investor">Nhà đầu tư</option>
              <option value="projectowner">Chủ dự án</option>
            </select>
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
              placeholder="your password"
              required
            />
          </div>

          <div className="mb-[2rem]">
            <label htmlFor="confirmPassword" className="text-h3">
              Xác nhận mật khẩu
            </label>
            <input
              id="confirmPassword"
              name="confirmPassword"
              value={formData.confirmPassword}
              onChange={handleChange}
              className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-[#699272]"
              type="password"
              placeholder="nhập lại mật khẩu"
              required
            />
          </div>

          <div className="mb-[2rem]">
            <input
              type="checkbox"
              id="agree"
              name="agree"
              checked={formData.agree}
              onChange={handleChange}
            />
            <label htmlFor="agree" className="text-h5 ml-[1rem]">
              Tôi đồng ý với Điều khoản sử dụng và Chính sách bảo mật
            </label>
          </div>

          <button className="button--primary w-full! mb-[2rem]" type="submit">
            Đăng ký
          </button>

          <Link to="/login" className="text-center">
            <p className="text-h5">
              Bạn đã có tài khoản?
              <span className="text-tertiary!"> Đăng nhập ngay</span>
            </p>
          </Link>
        </form>
      </div>
    </section>
  );
}

export default Register;