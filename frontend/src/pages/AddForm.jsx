import { useState } from "react";

function AddForm() {
  const [formData, setFormData] = useState({
    name: "",
    category: "",
    description: "",
    target: 0,
    endDate: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Form data submitted:", formData);

    // fetch("http://localhost:5000/api/submit", {
    //   method: "POST",
    //   headers: { "Content-Type": "application/json" },
    //   body: JSON.stringify(formData),
    // })
    //   .then((res) => res.json())
    //   .then((data) => {
    //     alert("Submitted successfully: " + JSON.stringify(data));
    //   })
    //   .catch((err) => console.error("Error:", err));
  };
  return (
    <div className="px-[9.5rem] mb-[3rem]">
      <h3 className="py-[3rem] text-green-300 font-bold">Tạo dự án</h3>
      <p className="text-[20px] pb-[3rem]">
        Tạo dự án mới để thu hút nhà đầu tư tiềm năng
      </p>

      <form
        onSubmit={handleSubmit}
        className="rounded-2xl border border-gray-500 px-[5rem] py-[2rem]"
      >
        <p className="text-[25px] font-bold mb-[2rem]">
          Thông tin cơ bản dự án
        </p>
        <div className="mb-[2rem]">
          <p className="text-[20px]">Tên dự án</p>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
            placeholder="Nhập tên dự án ..."
            className="text-[20px] border border-gray-400 rounded-xl w-full py-[7px] px-[5px] mt-[1rem]"
          />
        </div>

        <div className="mb-[2rem]">
          <p className="text-[20px]">Danh mục</p>
          <select
            name="category"
            value={formData.category}
            onChange={handleChange}
            required
            placeholder="Nhập tên dự án ..."
            className="text-[20px] border border-gray-400 rounded-xl w-[20em] py-[7px] px-[5px] mt-[1rem]"
          >
            <option value="">Chọn danh mục dự án</option>
            <option value="phim">Phim</option>
            <option value="game">Game</option>
            <option value="tranh">Tranh</option>
          </select>
        </div>

        <div className="mb-[2rem]">
          <p className="text-[20px]">Mô tả dự án</p>
          <textarea
            name="description"
            value={formData.description}
            onChange={handleChange}
            rows="5"
            required
            placeholder="Mô tả chi tiết về dự án, mục tiêu, kế hoạch thực hiên..."
            className="text-[20px] border border-gray-400 rounded-xl w-full py-[7px] px-[5px] mt-[1rem]"
          />
        </div>

        <div className="mb-[2rem]">
          <p className="text-[20px]">Vốn mục tiêu</p>
          <input
            type="number"
            name="target"
            value={formData.target}
            onChange={handleChange}
            required
            placeholder="0"
            className="text-[20px] border border-gray-400 rounded-xl w-full py-[7px] px-[5px] mt-[1rem]"
          />
        </div>

        <div className="mb-[2rem]">
          <p className="text-[20px]">Ngày kết thúc</p>
          <input
            type="date"
            name="endDate"
            value={formData.endDate}
            onChange={handleChange}
            required
            className="text-[20px] border border-gray-400 rounded-xl w-[250px] py-[7px] px-[5px] mt-[1rem]"
          />
        </div>

        <div className="flex justify-end">
          <button
            type="submit"
            className="w-[150px] rounded bg-green-700 text-white text-[20px] h-[50px] ml-auto hover:bg-green-900"
          >
            Tạo dự án
          </button>
        </div>
      </form>
    </div>
  );
}

export default AddForm;
