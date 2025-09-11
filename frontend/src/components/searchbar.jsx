function searchbar() {
  return (
    <div className="flex justify-center items-center">
      <input
        type="text"
        placeholder="Search..."
        className="border border-gray-300 rounded-l px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
      />
      <button className="bg-blue-500 text-white rounded-r px-4 py-2 hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500">
        Search
      </button>
    </div>
  );
}

export default searchbar;
