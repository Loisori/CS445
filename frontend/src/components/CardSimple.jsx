<<<<<<< HEAD
// import image from "../assets/imgs/exampleimage.avif";
// import imageavatar from "../assets/imgs/exampleavatar.avif";

// function CardSimple() {
//   return (
//     <article className="group p-[1.6rem]">
//       <figure>
//         <picture className="rounded-t-[1rem] overflow-hidden block">
//           <img src={image} alt="" />
//         </picture>
//         <progress
//           id="progress"
//           value="134"
//           max="100"
//           className="rounded-b-[1rem]"
//         ></progress>
//       </figure>
//       <div className="flex flex-row pt-[1.6rem] gap-[.8rem]" id="content">
//         <div className="flex">
//           <picture
//             className="inline-block rounded-full overflow-hidden size-[4rem]"
//             id="avatar"
//           >
//             <img src={imageavatar} alt="" />
//           </picture>
//         </div>
//         <div>
//           <div>
//             <h5 className="" id="titile">
//               ARCADIA: The Cold Open by The Miranda Brothers
//             </h5>
//           </div>
//           <p className="text-smallest" id="creator">
//             Ignition Press
//           </p>
//           <div className="flex text-h4">
//             <time datetime="">19 days left</time>
//             <p className="ml-[1rem]">134% funded</p>
//           </div>
//           <p className="group-hover:block hidden absolute bg-white text-h5 mt-[1.6rem]">
//             Lorem ipsum dolor sit amet consectetur adipisicing elit. Doloremque
//             corporis consequatur iusto inventore rem quis, eligendi aliquid modi
//             mollitia soluta. Temporibus, officiis dicta! Molestias maxime nemo
//             quibusdam magnam non. Quod?
//           </p>
//           <div>
//             <ul>
//               <li></li>
//               <li></li>
//             </ul>
//           </div>
//         </div>
//       </div>
//     </article>
//   );
// }

// export default CardSimple;
=======
import image from "../assets/imgs/exampleimage.avif";
import imageavatar from "../assets/imgs/exampleavatar.avif";

function CardSimple() {
  return (
    <article className="group p-[1.6rem]">
      <figure>
        <picture className="rounded-t-[1rem] overflow-hidden block">
          <img src={image} alt="" />
        </picture>
        <progress
          id="progress"
          value="134"
          max="100"
          className="rounded-b-[1rem]"
        ></progress>
      </figure>
      <div className="flex flex-row pt-[1.6rem] gap-[.8rem]" id="content">
        <div className="flex">
          <picture
            className="inline-block rounded-full overflow-hidden size-[4rem]"
            id="avatar"
          >
            <img src={imageavatar} alt="" />
          </picture>
        </div>
        <div>
          <div>
            <h5 className="" id="titile">
              ARCADIA: The Cold Open by The Miranda Brothers
            </h5>
          </div>
          <p className="text-smallest" id="creator">
            Ignition Press
          </p>
          <div className="flex text-h4">
            <time datetime="">19 days left</time>
            <p className="ml-[1rem]">134% funded</p>
          </div>
          <p className="group-hover:block hidden absolute bg-white text-h5 mt-[1.6rem]">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Doloremque
            corporis consequatur iusto inventore rem quis, eligendi aliquid modi
            mollitia soluta. Temporibus, officiis dicta! Molestias maxime nemo
            quibusdam magnam non. Quod?
          </p>
          <div>
            <ul>
              <li></li>
              <li></li>
            </ul>
          </div>
        </div>
      </div>
    </article>
  );
}

export default CardSimple;
>>>>>>> parent of eda1b61 (update)
