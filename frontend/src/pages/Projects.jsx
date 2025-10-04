import { StrictMode, lazy, Suspense } from "react";

const Hero = lazy(() => import("../sections/layouts/Hero"));
const Why = lazy(() => import("../sections/layouts/Why"));
const Ready = lazy(() => import("../sections/layouts/Ready"));

function Project() {
  return (
    <>
      <section>
        <div className="wrapper">
          <div className="mb-[6rem]">
            <h2>Khám phá Dự án đầu tư</h2>
            <p className="text-h4">
              Tìm kiếm và đầu tư vào các dự án tiềm năng phù hợp với bạn
            </p>
          </div>
          <div className="flex mb-[5rem] w-full gap-[3rem] bg-secondary py-[4rem] px-[3rem] rounded-[2rem] border-[.1rem] border-solid border-[#C0BDBD]">
            <input
              type="text"
              name=""
              id=""
              className="w-1/2 bg-[#E1EFE0] border-[.1rem] border-solid border-[#C0BDBD] px-[2rem] py-[1rem] text-h5 rounded-[8px]"
              placeholder="Tìm kiếm dự án ..."
            />
            <select
              name="type"
              id="type"
              className="px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.2rem] border-solid border-[#C0BDBD]"
            >
              <option value="Tất cả danh mục">Tất cả danh mục</option>
            </select>
            <select
              name="risk"
              id="risk"
              className="px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.2rem] border-solid border-[#C0BDBD]"
            >
              <option value="Tất cả mức rủi ro">Tất cả mức rủi ro</option>
            </select>
            <select
              name="date"
              id="date"
              className="px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.2rem] border-solid border-[#C0BDBD]"
            >
              <option value="Mới nhất">Mới nhất</option>
            </select>
          </div>
          <div>
            <div>
              <p className="text-small">Tìm thấy 6 dự án</p>
            </div>
            <div className="grid grid-cols-3">
              <div>
                <picture>
                  <img src="" alt="" />
                </picture>
                <div>
                  <h4>Dự án Khu đô thị thông minh Green Valley</h4>
                  <p>
                    Khu đô thị sinh thái hiện đại với công nghệ thông minh, diện
                    tích 500ha tại Đồng Nai
                  </p>
                  <div className="flex justify-between">
                    <div>Đồng Nai</div>
                    <div>45 ngày</div>
                  </div>
                  <button className="button--primary">Xem chi tiết</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default Project;
