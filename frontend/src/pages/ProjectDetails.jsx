import exImg from "../assets/imgs/exampleimage.avif";
import exAva from "../assets/imgs/exampleavatar.avif";
function ProjectDetails() {
  return (
    <div className="flex mx-[9.5rem]">
      <div className="w-2/3 pr-[3rem] py-[3rem]">
        <div>
          <div className="w-full rounded-2xl h-[400px]">
            <img src={exImg} className="w-full h-full object-cover" />
          </div>

          <div className="flex gap-[20px] py-[30px] px-[20px] rounded-b-2xl bg-green-200">
            <div className="w-[200px] h-[100px]">
              <img src={exImg} className="w-full h-full object-cover" />
            </div>
            <div className="w-[200px] h-[100px]">
              <img src={exImg} className="w-full h-full object-cover" />
            </div>
            <div className="w-[200px] h-[100px]">
              <img src={exImg} className="w-full h-full object-cover" />
            </div>
            <div className="w-[200px] h-[100px]">
              <img src={exImg} className="w-full h-full object-cover" />
            </div>
          </div>
        </div>

        <div className="bg-green-200 w-full mt-[40px] rounded-2xl py-[2rem] px-[30px]">
          <p className="text-[20px] font-bold">
            Dự án khu đô thị thông minh Green Valley
          </p>
          <p className="text-[15px] mt-[10px]">
            Khu đô thị sinh thái hiện đại với công nghệ thông minh, diện tích
            500ha tại Đồng Nai
          </p>

          <div className="flex justify-between my-[20px]">
            <p className="text-[13px] font-bold">45 ngày còn lại</p>
            <p className="text-[13px] font-bold">245 nhà đầu tư</p>
            <p className="text-[13px] font-bold">12 - 15%</p>
          </div>

          <div className="flex justify-between">
            <p className="text-[15px]">Tiến độ gọi vốn</p>
            <p className="text-[15px]">60%</p>
          </div>
          <progress
            value="60"
            max="100"
            className="w-full h-4 my-[10px] [&::-webkit-progress-bar]:bg-gray-200 [&::-webkit-progress-value]:bg-green-700 [&::-moz-progress-bar]:bg-green-700"
          ></progress>
          <div className="flex justify-between">
            <p className="text-[15px]">35.000.000 đ</p>
            <p className="text-[15px]">50.000.000 đ</p>
          </div>

          <div className="text-[17px] my-[2rem]">
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent
              aliquet aliquet arcu, id tempor lectus sagittis eget. Aenean
              ultricies dui a enim lacinia, in pharetra orci lacinia. Vestibulum
              iaculis fermentum venenatis. Praesent consectetur eu quam eu
              scelerisque. Phasellus eget arcu ac metus tempor imperdiet. Fusce
              molestie ac augue at venenatis. Duis ac rhoncus justo. Proin
              egestas eleifend neque vitae consectetur. Integer nec suscipit
              lectus. Proin a velit suscipit, gravida ante quis, placerat
              turpis. Curabitur sollicitudin nibh id libero tincidunt, eu rutrum
              est malesuada. Praesent luctus enim arcu, quis semper urna feugiat
              in. Cras sed nisl varius, tincidunt nibh blandit, accumsan quam.{" "}
            </p>

            <p>
              Vivamus et lorem non felis congue fermentum. Morbi dui libero,
              mattis vel sem sit amet, sagittis semper felis. Nulla mattis purus
              non erat cursus vestibulum. Donec vel aliquam leo. Interdum et
              malesuada fames ac ante ipsum primis in faucibus. Aenean
              tincidunt, diam quis sodales pretium, libero massa facilisis
              risus, vel laoreet urna nisi eu mi. In lorem massa, rutrum vel
              justo non, mattis lacinia enim. Donec ac elit in est bibendum
              ultricies finibus quis turpis. Quisque erat mauris, congue sit
              amet vulputate ac, malesuada id tellus. Donec erat metus, volutpat
              non dictum a, maximus quis orci. Praesent suscipit nisl et
              porttitor dignissim. Donec erat ante, dapibus ultrices sagittis
              non, dapibus eu mauris.
            </p>
          </div>
        </div>
      </div>
      <div className="w-1/3 h-[1000px] py-[3rem]">
        <div className="bg-green-200 px-[1.5rem] py-[1rem] rounded-2xl">
          <p className="text-[20px] font-bold">Đầu tư ngay</p>
          <p className="text-[13px] mt-[10px] mb-[20px]">
            Tham gia đầu tư vào dự án này
          </p>
          <div className="flex justify-between mb-[10px]">
            <p className="text-[13px]">Số tiền tối thiểu</p>
            <p className="text-[13px] font-bold">50.000.000 đ</p>
          </div>
          <div className="flex justify-between mb-[10px]">
            <p className="text-[13px]">Lợi nhuận kì vọng</p>
            <p className="text-[13px] font-bold text-green-400">12 - 15%</p>
          </div>
          <div className="flex justify-between mb-[10px]">
            <p className="text-[13px]">Thời gian còn lại</p>
            <p className="text-[13px] font-bold text-green-400">45 ngày</p>
          </div>

          <div className="bg-green-700 w-full py-[8px] text-center text-white text-[13px] mt-[40px]">
            Đầu tư ngay
          </div>
        </div>

        <div className="bg-green-200 px-[1.5rem] py-[1rem] rounded-2xl mt-[2.7rem]">
          <p className="text-[20px] font-bold">Chủ dự án</p>
          <div className="flex my-[2rem]">
            <div className="h-[40px] w-[40px] rounded-full">
              <img src={exAva} className="w-full h-full object-cover" />
            </div>
            <div className="ml-[2rem]">
              <p className="font-bold text-[15px]">Green Valley Development</p>
              <p>12 dự án</p>
            </div>
          </div>
          <div className="flex justify-between mb-[10px]">
            <p className="text-[13px]">Tổng số vốn huy động</p>
            <p className="text-[13px] font-bold text-green-400">
              10.000.000.000 đ
            </p>
          </div>
          <div className="flex justify-between mb-[10px]">
            <p className="text-[13px]">Dự án hoàn thành</p>
            <p className="text-[13px] font-bold text-green-400">12</p>
          </div>

          <div className="bg-green-100 w-full py-[8px] text-center text-[13px] mt-[40px] mb-[10px]">
            Xem hồ sơ
          </div>
        </div>

        <div className="bg-green-200 px-[1.5rem] py-[1rem] rounded-2xl mt-[40px]">
          <p className="text-[20px] font-bold mb-[20px]">Thống kê nhanh</p>
          <div className="flex justify-between mb-[10px]">
            <p className="text-[13px]">Mục tiêu</p>
            <p className="text-[13px] font-bold">50.000.000 đ</p>
          </div>
          <div className="flex justify-between mb-[10px]">
            <p className="text-[13px]">Đã huy động</p>
            <p className="text-[13px] font-bold text-green-400">35.000.000</p>
          </div>
          <div className="flex justify-between mb-[10px]">
            <p className="text-[13px]">Nhà đầu tư</p>
            <p className="text-[13px] font-bold text-green-400">245</p>
          </div>
          <div className="flex justify-between mb-[10px]">
            <p className="text-[13px]">Còn lại</p>
            <p className="text-[13px] font-bold text-green-400">45 ngày</p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ProjectDetails;

