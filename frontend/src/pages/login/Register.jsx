import { Link } from "react-router-dom";

function Register() {
  return (
    <section className="py-[20rem]!">
      <div className="min-w-[60rem] min-h-[5rem] w-fit m-auto bg-secondary py-[3rem] px-[4rem] rounded-[2rem]">
        <form action="">
          <h2 htmlFor="" className="mb-[3rem] text-center font-bold">
            Đăng ký
          </h2>
          <br />
          <div className="mb-[2rem]">
            <label htmlFor="FullName" className="text-h3" name="fullname">
              Họ và tên
            </label>
            <br />
            <input
              id="Email"
              className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-solid border-[#699272]"
              type="text"
              placeholder="your name"
              required
            />
          </div>
          <br />
          <div className="mb-[2rem]">
            <label htmlFor="Emaill" className="text-h3">
              Email
            </label>
            <br />
            <input
              className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-solid border-[#699272]"
              id="Email"
              type="email"
              placeholder="your@gmail.com"
              required
            />
          </div>
          <div className="mb-[2rem]">
            <label htmlFor="FullName" className="text-h3">
              Số điện thoại
            </label>
            <br />
            <input
              id="Phone"
              className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-solid border-[#699272]"
              type="number"
              placeholder="0123456789"
              required
            />
          </div>
          <div className="mb-[2rem]">
            <label htmlFor="AccType" className="text-h3">
              Loại tài khoản
            </label>
            <br />
            <select
              name="AccType"
              id="AccType"
              className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-solid border-[#699272]"
            >
              <option value="select" disable>
                Chọn loại tài khoản
              </option>
              <option value="investor">Nhà đầu tư</option>
              <option value="projectowner">Chủ dự án</option>
            </select>
          </div>
          <div className="mb-[2rem]">
            <label htmlFor="FullName" className="text-h3">
              Mật khẩu
            </label>
            <br />
            <input
              id="Password"
              className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-solid border-[#699272]"
              type="password"
              name="password"
              placeholder="your password"
              required
            />
          </div>
          <div className="mb-[2rem]">
            <label htmlFor="FullName" className="text-h3">
              Xác nhận mật khẩu
            </label>
            <br />
            <input
              id="Email"
              className="w-full px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.3rem] border-solid border-[#699272]"
              type="password"
              placeholder="nhập lại mật khẩu"
              required
            />
          </div>
          <div>
            <input type="checkbox" id="confirm" />
            <label htmlFor="confirm" className="text-h5 ml-[1rem]">
              Tôi đồng ý với Điều khoản sử dụng và Chính sách bảo mật
            </label>
          </div>
          <div>
            <button
              className="button--primary w-full! mb-[2rem]"
              id="js-login"
              type="submit"
            >
              Đăng ký
            </button>
            <Link to="/login" className="text-center">
              <p className="text-h5">
                Bạn đã có tài khoản?
                <span className="text-tertiary!"> Đăng nhập ngay</span>
              </p>
            </Link>
          </div>
        </form>
      </div>
    </section>
  );
}

export default Register;
