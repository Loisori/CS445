import { useEffect } from "react";

export function useScrollAnimate() {
  useEffect(() => {
    // Define your selector + activeClass pairs here inside the hook
    const configs = [
      { selector: ".animation-fade-up", activeClass: "is-fade-up" },
      { selector: ".animation-fade-right", activeClass: "is-fade-right" },
      { selector: ".animation-fade-left", activeClass: "is-fade-left" },
      { selector: ".animation-pop-up", activeClass: "is-pop-up" },
    ];

    const observers = [];

    configs.forEach(({ selector, activeClass }) => {
      const elements = document.querySelectorAll(selector);
      const observer = new IntersectionObserver(
        (entries) => {
          entries.forEach((entry) => {
            if (entry.isIntersecting) {
              entry.target.classList.add(activeClass);
            } else {
              entry.target.classList.remove(activeClass);
            }
          });
        },
        { threshold: 0.4 }
      );

      elements.forEach((el) => observer.observe(el));
      observers.push(observer);
    });

    return () => {
      observers.forEach((observer) => observer.disconnect());
    };
  }, []);
}
