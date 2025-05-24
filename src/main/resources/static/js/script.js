document.addEventListener('DOMContentLoaded', () => {
    "use strict";
  
    // Initialize AOS
    AOS.init({
        duration: 800,
        easing: 'ease-in-out',
        once: true,
        mirror: false
    });
  
    // Sticky Navbar Shrink Effect (Optional - if needed)
    // Add logic here if you want the navbar to change style on scroll
  
    // Scroll-to-Top Button Visibility
    const scrollTopButton = document.querySelector('.scroll-to-top');
    if (scrollTopButton) {
        const toggleScrollTop = function() {
            window.scrollY > 100 ? scrollTopButton.classList.add('visible') : scrollTopButton.classList.remove('visible');
        }
        window.addEventListener('load', toggleScrollTop);
        document.addEventListener('scroll', toggleScrollTop);
        scrollTopButton.addEventListener('click', (e) => {
            e.preventDefault();
            window.scrollTo({
                top: 0,
                behavior: 'smooth'
            });
        });
    }
  
    // Navbar Active State on Scroll
    let navbarlinks = document.querySelectorAll('#navbarNav .nav-link');
  
    function navbarlinksActive() {
        navbarlinks.forEach(navbarlink => {
            if (!navbarlink.hash) return;
            let section = document.querySelector(navbarlink.hash);
            if (!section) return;
            let position = window.scrollY + 200; // Offset
            if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
                navbarlink.classList.add('active');
            } else {
                navbarlink.classList.remove('active');
            }
        })
    }
    window.addEventListener('load', navbarlinksActive);
    document.addEventListener('scroll', navbarlinksActive);
  
     // Smooth scroll for links with hashes
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            const hash = this.getAttribute('href');
            // Only prevent default for actual section links, not empty hashes or just "#"
            if (hash && hash !== '#' && document.querySelector(hash)) {
                e.preventDefault();
                const targetElement = document.querySelector(hash);
                if (targetElement) {
                    const header = document.querySelector('#header');
                    const headerOffset = header ? header.offsetHeight : 0;
                    const elementPosition = targetElement.offsetTop;
                    const offsetPosition = elementPosition - headerOffset;
  
                    window.scrollTo({
                        top: offsetPosition,
                        behavior: 'smooth'
                    });
  
                    // Close mobile nav if open after clicking a link
                    const navbarToggler = document.querySelector('.navbar-toggler');
                    const navbarCollapse = document.querySelector('#navbarNav');
                    if (navbarToggler && navbarCollapse.classList.contains('show')) {
                        navbarToggler.click(); // Simulate click to close
                    }
                }
            }
        });
    });
  
    // Gallery Filtering (Isotope)
    let galleryContainer = document.querySelector('.gallery-container');
    if (galleryContainer) {
        let galleryIsotope = new Isotope(galleryContainer, {
            itemSelector: '.gallery-item',
            layoutMode: 'fitRows'
        });
  
        let galleryFilters = document.querySelectorAll('#gallery-filters li');
  
        galleryFilters.forEach(function(el) {
            el.addEventListener('click', function() {
                galleryFilters.forEach(function(filter) {
                    filter.classList.remove('filter-active');
                });
                this.classList.add('filter-active');
  
                galleryIsotope.arrange({
                    filter: this.getAttribute('data-filter')
                });
                // Optional: Re-initialize AOS after filtering if needed
                // AOS.refresh();
            });
        });
    }
  
    // Gallery Lightbox (GLightbox)
    const galleryLightbox = GLightbox({
        selector: '.gallery-lightbox'
    });
  
    // Bootstrap Form Validation
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (() => {
        'use strict'
  
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        const forms = document.querySelectorAll('.needs-validation')
  
        // Loop over them and prevent submission
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }
  
            form.classList.add('was-validated')
            }, false)
        })
    })()
  
    // Placeholder for PHP Email Form Submission Logic
    // This requires a backend script (e.g., PHP) to actually send the email.
    // The .php-email-form class is just a convention here.
    // You would typically use Fetch API or XMLHttpRequest to send data to your backend.
    /*
    const contactForm = document.querySelector('.php-email-form');
    if (contactForm) {
        contactForm.addEventListener('submit', function(e) {
            e.preventDefault(); // Prevent default form submission
  
            // Basic validation check (redundant if using Bootstrap validation, but good practice)
            if (!this.checkValidity()) {
                 this.classList.add('was-validated');
                 return;
            }
  
            let formData = new FormData(this);
            let action = this.getAttribute('action'); // Get the backend script URL
  
            // Display loading message
            this.querySelector('.loading').classList.add('d-block');
            this.querySelector('.error-message').classList.remove('d-block');
            this.querySelector('.sent-message').classList.remove('d-block');
  
            // Replace with your actual Fetch API call to the backend
            // fetch(action, { method: 'POST', body: formData })
            //     .then(response => {
            //         if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
            //         return response.text(); // Or response.json() if your backend returns JSON
            //     })
            //     .then(data => {
            //         this.querySelector('.loading').classList.remove('d-block');
            //         if (data.trim() === 'OK') { // Check backend response
            //             this.querySelector('.sent-message').classList.add('d-block');
            //             this.reset(); // Clear the form
            //             this.classList.remove('was-validated');
            //         } else {
            //             throw new Error(data || 'Form submission failed');
            //         }
            //     })
            //     .catch((error) => {
            //         this.querySelector('.loading').classList.remove('d-block');
            //         this.querySelector('.error-message').innerHTML = error.message || 'An error occurred. Please try again.';
            //         this.querySelector('.error-message').classList.add('d-block');
            //     });
  
            // **Remove this simulation section when implementing actual backend**
            // Simulate sending for demonstration purposes:
            console.log("Form data submitted (simulation):", Object.fromEntries(formData));
            setTimeout(() => {
                 this.querySelector('.loading').classList.remove('d-block');
                 // Simulate success:
                 this.querySelector('.sent-message').classList.add('d-block');
                 this.reset();
                 this.classList.remove('was-validated');
                 // Simulate error:
                 // this.querySelector('.error-message').innerHTML = 'Simulation Error: Could not send.';
                 // this.querySelector('.error-message').classList.add('d-block');
            }, 1500); // Simulate network delay
        });
    }
    */
  
  });