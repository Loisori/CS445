import { Link } from "react-router-dom";

function Register() {
  return (
    <section>
      Register
      <div className="bg-secondary py-[3rem] px-[4rem]">
        <form action="">
          <label htmlFor=""></label>
          <label htmlFor="Email"></label>
          <input type="text" placeholder="your@gmail.com" required />
          <label htmlFor="Password"></label>
          <input type="text" placeholder="Nhập mật khẩu" required />
          <button>Quên mật khẩu?</button>
          <button>Đăng nhập</button>
          <button>Bạn chưa có tài khoản? Đăng ký ngay</button>
        </form>
      </div>
    </section>
  );
}

export default Register;
