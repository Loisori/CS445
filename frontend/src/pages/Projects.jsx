import { useEffect, useState } from "react";

function Project() {
  const [projects, setProjects] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [loading, setLoading] = useState(true);

  // Filter states
  const [search, setSearch] = useState("");
  const [category, setCategory] = useState("Tất cả danh mục");
  const [risk, setRisk] = useState("Tất cả mức rủi ro");
  const [date, setDate] = useState("Mới nhất");

  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/posts?_limit=9")
      .then((res) => res.json())
      .then((data) => {
        const mapped = data.map((item, index) => ({
          id: item.id,
          title: item.title,
          description: item.body,
          location: index % 2 === 0 ? "Đồng Nai" : "TP.HCM", // Example
          duration: `${30 + index * 5} ngày`,
          category: index % 2 === 0 ? "Bất động sản" : "Nông nghiệp",
          risk: index % 2 === 0 ? "Thấp" : "Cao",
          date: new Date(Date.now() - index * 86400000), // subtract days
          image: "https://via.placeholder.com/400x250",
        }));
        setProjects(mapped);
        setFiltered(mapped);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching projects:", err);
        setLoading(false);
      });
  }, []);

  // Apply filters when search/filters change
  useEffect(() => {
    let result = [...projects];

    // search
    if (search.trim() !== "") {
      result = result.filter(
        (p) =>
          p.title.toLowerCase().includes(search.toLowerCase()) ||
          p.description.toLowerCase().includes(search.toLowerCase())
      );
    }

    // category filter
    if (category !== "Tất cả danh mục") {
      result = result.filter((p) => p.category === category);
    }

    // risk filter
    if (risk !== "Tất cả mức rủi ro") {
      result = result.filter((p) => p.risk === risk);
    }

    // date filter (Mới nhất = sort descending)
    if (date === "Mới nhất") {
      result.sort((a, b) => b.date - a.date);
    } else if (date === "Cũ nhất") {
      result.sort((a, b) => a.date - b.date);
    }

    setFiltered(result);
  }, [search, category, risk, date, projects]);

  return (
    <section>
      <div className="wrapper">
        <div className="mb-[6rem]">
          <h2>Khám phá Dự án đầu tư</h2>
          <p className="text-h4">
            Tìm kiếm và đầu tư vào các dự án tiềm năng phù hợp với bạn
          </p>
        </div>

        {/* Search & filters */}
        <div className="flex mb-[5rem] w-full gap-[3rem] bg-secondary py-[4rem] px-[3rem] rounded-[2rem] border-[.1rem] border-solid border-[#C0BDBD]">
          <input
            type="text"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="w-1/2 bg-[#E1EFE0] border-[.1rem] border-solid border-[#C0BDBD] px-[2rem] py-[1rem] text-h5 rounded-[8px]"
            placeholder="Tìm kiếm dự án ..."
          />

          <select
            value={category}
            onChange={(e) => setCategory(e.target.value)}
            className="px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.2rem] border-solid border-[#C0BDBD]"
          >
            <option>Tất cả danh mục</option>
            <option>Bất động sản</option>
            <option>Nông nghiệp</option>
          </select>

          <select
            value={risk}
            onChange={(e) => setRisk(e.target.value)}
            className="px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.2rem] border-solid border-[#C0BDBD]"
          >
            <option>Tất cả mức rủi ro</option>
            <option>Thấp</option>
            <option>Cao</option>
          </select>

          <select
            value={date}
            onChange={(e) => setDate(e.target.value)}
            className="px-[2rem] py-[1rem] text-h5 rounded-[8px] border-[.2rem] border-solid border-[#C0BDBD]"
          >
            <option>Mới nhất</option>
            <option>Cũ nhất</option>
          </select>
        </div>

        {/* Projects grid */}
        <div>
          <p className="text-small">
            {loading ? "Đang tải..." : `Tìm thấy ${filtered.length} dự án`}
          </p>
          <div className="grid grid-cols-3 gap-[2rem] mt-[2rem]">
            {!loading &&
              filtered.map((project) => (
                <div
                  key={project.id}
                  className="p-[2rem] border rounded-[1rem] bg-white shadow-md"
                >
                  <picture>
                    <img
                      src={project.image}
                      alt={project.title}
                      className="rounded-[1rem] mb-[1rem]"
                    />
                  </picture>
                  <h4 className="font-bold mb-[1rem]">{project.title}</h4>
                  <p className="mb-[1rem]">{project.description}</p>
                  <div className="flex justify-between mb-[1rem]">
                    <div>{project.location}</div>
                    <div>{project.duration}</div>
                  </div>
                  <button className="button--primary">Xem chi tiết</button>
                </div>
              ))}
          </div>
        </div>
      </div>
    </section>
  );
}

export default Project; 