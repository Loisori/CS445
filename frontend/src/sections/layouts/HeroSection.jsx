import CardDetailed from "../../components/CardDetailed";
import CardSimple from "../../components/CardSimple";

function HeroSection() {
  return (
    <section className="grid grid-cols-1 sm:grid-cols-2 wrapper  gap-[4rem]">
      <CardDetailed />
      <div>
        <p className="text-small font-bold text-gray leading-[1.8] uppercase">Featured project</p>
        <ul className="grid grid-cols-1 sm:grid-cols-2 gap-x-[1.6rem]">
          <li>
            <CardSimple />
          </li>
          <li>
            <CardSimple />
          </li>
          <li>
            <CardSimple />
          </li>
          <li>
            <CardSimple />
          </li>
        </ul>
      </div>
    </section>
  );
}

export default HeroSection;
