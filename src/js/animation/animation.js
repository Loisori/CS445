class ScrollAnimateElement extends HTMLElement {
    constructor() {
        super();
        this.observer = null;
    }

    connectedCallback() {
        this.initAnimations();
    }

    initAnimations() {
        const animationClasses = [
            { selector: ".js-fade-up", activeClass: "is-fade-up" },
            { selector: ".js-fade-right", activeClass: "is-fade-right" },
            { selector: ".js-fade-left", activeClass: "is-fade-left" },
            { selector: ".js-pop-up", activeClass: "is-pop-up" },
        ];

        animationClasses.forEach(({ selector, activeClass }) => {
            this.observeElements(selector, activeClass);
        });
    }

    observeElements(selector, activeClass) {
        const animatedElements = this.querySelectorAll(selector);

        const observer = new IntersectionObserver(
            (entries) => {
                entries.forEach((entry) => {
                    if (entry.isIntersecting) {
                        entry.target.classList.add(activeClass);
                        observer.unobserve(entry.target);
                    }
                });
            },
            { threshold: 0.4 }
        );

        animatedElements.forEach((el) => observer.observe(el));
    }

    disconnectedCallback() {
        if (this.observer) {
            this.observer.disconnect();
        }
    }
}

customElements.define("scroll-animate", ScrollAnimateElement);